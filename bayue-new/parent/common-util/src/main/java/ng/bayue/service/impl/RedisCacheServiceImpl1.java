package ng.bayue.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.redis.CacheShardedJedisPool;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.SerializeUtil;
import ng.bayue.util.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;

public class RedisCacheServiceImpl1 implements RedisCacheService{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl1.class);
	
	/** redis 的key最大值：50k */
	private final static int MAX_KEY = 1024 * 50;
	/** redis 的value最大值：1M */
	private final static int MAX_VALUE = 1024 * 1024;
	
	/** 默认锁的时间, 5分钟 */
	private static final int LOCK_EXPIRE_SECONDS = 5 * 60;
	
	/** redis缓存操作的返回值 */
	private final static String SET_CACHE_RESULT = "OK";
	
//	private ShardedJedisPool shardedJedisPool = null;
//	
//	public ShardedJedisPool getShardedJedisPool(){
//		return this.shardedJedisPool;
//	}
//	
//	public void setShardedJedisPool(ShardedJedisPool shardedJedisPool){
//		this.shardedJedisPool = shardedJedisPool;
//	}
	
	private CacheShardedJedisPool shardedJedisPool = null;
	
	private JedisPool jedisPool = null;
	
	public CacheShardedJedisPool getShardedJedisPool() {
		return shardedJedisPool;
	}

	public void setShardedJedisPool(CacheShardedJedisPool shardedJedisPool) {
		this.shardedJedisPool = shardedJedisPool;
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	private static void validKeyAndValue(String key, byte[] values) throws Exception {
		if (MAX_KEY < key.getBytes().length) { // key最大50k
			logger.info("redis key is to long:{}", key);
			throw new Exception("redis key is to long:" + key);
		}
		
		 if(MAX_VALUE < values.length){ // value最大值1M
		 	logger.info("redis data is to long:{}",key); 
		 	throw new Exception("redis data is to long:" + key); 
		 }
	}
	
	/**
	 * <pre>
	 * 判断key是否为空
	 * </pre>
	 *
	 * @param key
	 * @return key is null or blank, return true, else return false;
	 */
	private boolean keyIsBlank(String key){
		if(null == key || "".equals(key.trim())){
			logger.info("operated redis exception: the redis key is not allowed null or blank !");
			return true;
		}
		return false;
	}
	
	private void closeJedis(ShardedJedis jedis, boolean isSuccess) {
		if(null != jedis && isSuccess){
			shardedJedisPool.returnResource(jedis);
		}else{
			shardedJedisPool.returnBrokenResource(jedis);
		}
	}
	
	private void closeJedis(Jedis jedis, boolean isSuccess) {
		if (null != jedis && isSuccess) {
			jedisPool.returnResource(jedis);
		} else {
			jedisPool.returnBrokenResource(jedis);
		}
	}
	
	@Override
	public boolean setRedisCacheString(String key, String value, Integer expires) {
		if(null == key || "".equals(key.trim())){
			logger.info("set redis data error:the paramter key is not allowed null");
			return false;
		}
		if(null == value || "".equals(value.trim())){
			logger.info("set redis data error:the paramter value is not allowed null");
			return false;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = false;
		try {
			validKeyAndValue(key, value.getBytes());
			
			jedis = shardedJedisPool.getResource();
			String result = jedis.set(key, value);
			if(SET_CACHE_RESULT.equals(result)){
				isSuccess = true;
				if(null != expires){
					jedis.expire(key, expires);
				}
			}
		} catch (Exception e) {
			logger.info("set redis string data exception:{}", e);
		}finally{
			closeJedis(jedis, isSuccess);
		}
		return isSuccess;
	}

	@Override
	public String getRedisCacheString(String key) {
		if(null == key || "".equals(key.trim())){
			logger.info("get redis data error:the paramter key is not allowed null");
			return null;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			String result = jedis.get(key);
			return result;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("get redis string data exception:{}", e);
		}finally{
			closeJedis(jedis, isSuccess);
		}
		return null;
	}

	@Override
	public boolean setRedisCache(String key, Object o, Integer expires) {
		if (null == key || "".equals(key.trim())) {
			try {
				throw new Exception("redis key is not alowed null");
			} catch (Exception e) {
				logger.error("", e);
			}
		}
		/*if(o instanceof String){
			return setRedisCacheString(key,(String)o,expires);
		}*/
		byte[] values = SerializeUtil.serialize(o);
		byte[] bytesKey = key.getBytes();
		ShardedJedis jedis = null;
		boolean isSuccess = false;
		try {
			validKeyAndValue(key, values);
			 
			jedis = shardedJedisPool.getResource();
			String res = "";
			if(o instanceof String){
				res = jedis.set(key, (String) o);
			}else{
				res = jedis.set(bytesKey, values);
			}
			if (SET_CACHE_RESULT.equalsIgnoreCase(res)) {
				logger.info("key:{} redis cache is success!", key);
				isSuccess = true;
			}
			if (null != expires) {
				Long time = jedis.expire(bytesKey, expires);
				logger.info("key:{} redis cache time is {}", key, time);
				isSuccess = true;
			}
//			return isSuccess;
		} catch (Exception e) {
			logger.error("", e);
		}finally{
			closeJedis(jedis, isSuccess);
		}
		return isSuccess;
	}

	@Override
	public Object getRedisCache(String key) {
		if(keyIsBlank(key)){
			return null;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			byte[] bytes = jedis.get(key.getBytes());
			Object o = SerializeUtil.unSerialize(bytes);
			return o;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("redis get data exception:{}",e);
		}finally{
			closeJedis(jedis, isSuccess);
		}
		return null;
	}

	@Override
	public void deleteRedisCache(String key) {
		if(null == key || "".equals(key)){return ;}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis =  shardedJedisPool.getResource();
			Long res = jedis.del(key);
			isSuccess = 1 == res.intValue() ? true : false;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}",e);
			throw e;
		}finally{
			closeJedis(jedis, isSuccess);
		}
	}

	/**
	 * 取得有效时间内的锁
	 *
	 * @param key
	 * @param expireSeconds
	 *            有效时间 单位 秒
	 * @return
	 */
	@Override
	public boolean lock(String key, Integer expires) throws Exception {
		if(keyIsBlank(key)){
			return false;
		}
		if(0 == expires || null == expires){
			expires = LOCK_EXPIRE_SECONDS;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			ShardedJedisPipeline pipelined = jedis.pipelined();
			pipelined.setnx(key, System.currentTimeMillis() + "");
			pipelined.expire(key, expires);
			List<Object> syncAndReturnAll = pipelined.syncAndReturnAll();
			if (!syncAndReturnAll.isEmpty() && null != syncAndReturnAll.get(0)) {
				return "1".equalsIgnoreCase(syncAndReturnAll.get(0).toString());
			}
		} catch (Exception e) {
			isSuccess = false;
			logger.error(e.getMessage(), e);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}


	/**
	 * 获取锁,默认锁定五分钟
	 *
	 * @param key
	 * @return
	 */
	public boolean lock(String key){
		try {
			return this.lock(key, LOCK_EXPIRE_SECONDS);
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 释放锁
	 *
	 * @param key
	 * @return
	 */
	public boolean unLock(String key) {
		if(keyIsBlank(key)){
			return false;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			Long i = jedis.del(key);
			if (i.intValue() == 1) {
				return true;
			}
		} catch (Exception e) {
			isSuccess = false;
			logger.error(e.getMessage(), e);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}

	@Override
	public boolean keyExists(String key) {
		if(keyIsBlank(key)){
			return false;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			return jedis.exists(key);
		} catch (Exception e) {
			isSuccess = false;
			logger.error(e.getMessage(), e);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}
	
	/**
	 * expireSeconds时间内，key自增的值超过maxNum将返回false,否则返回true
	 *
	 * @param key
	 * @param expireSeconds
	 * @param maxNum
	 * @return
	 */
	public boolean watchMethodCall(String key, int expireSeconds, int maxNum) {
		return this.incrWatchKeyNew(key, expireSeconds, maxNum);
	}

	private boolean incrWatchKey(String key, int expireSeconds, int maxNum) {
		long count = 0;
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			long ttl = jedis.ttl(key);
			if (ttl > 0 && ttl <= expireSeconds) {
				String str = jedis.get(key);
				if (null != str && Long.valueOf(str) < maxNum) {
					count = jedis.incr(key);
				}
			} else {
				ShardedJedisPipeline pipelined = jedis.pipelined();
				pipelined.set(key, String.valueOf("0"));
				pipelined.expire(key, expireSeconds);
				pipelined.incr(key);
				List<Object> syncAndReturnAll = pipelined.syncAndReturnAll();
				if (!syncAndReturnAll.isEmpty() && null != syncAndReturnAll.get(2)) {
					count = Long.valueOf(syncAndReturnAll.get(2).toString());
				}
			}
		} catch (Exception ex) {
			isSuccess = false;
			logger.error(ex.getMessage(), ex);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		if (count != 0 && count <= maxNum) {
			return true;
		}
		return false;
	}

	private boolean incrWatchKeyNew(String key, int expireSeconds, long maxNum) {
		if (maxNum > 50000) {// 队列允许的最大数据条数
			return false;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			Long llen = jedis.llen(key);
			if (llen >= maxNum) {
				return false;
			} else {
				if (!jedis.exists(key)) {
					ShardedJedisPipeline pipelined = jedis.pipelined();
					pipelined.rpush(key, key);
					pipelined.expire(key, expireSeconds);
					pipelined.syncAndReturnAll();
				} else {
					jedis.rpushx(key, key);
				}
				return true;
			}
		} catch (Exception ex) {
			isSuccess = false;
			logger.error(ex.getMessage(), ex);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}
	
	@Override
	public boolean updateExpire(String key, int expire){
		if(StringUtils.isBlank(key)){
			return false;
		}
		ShardedJedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = shardedJedisPool.getResource();
			jedis.expire(key, expire);
		} catch (Exception ex) {
			isSuccess = false;
			logger.error(ex.getMessage(), ex);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}

	@Override
	public Set<String> keys(String keyPattern) {
		if (null == keyPattern || "".equals(keyPattern)) {
			return Collections.emptySet();
		}
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
			Set<String> keys = jedis.keys(keyPattern);
			return keys;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}", e);
			throw e;
		} finally {
			closeJedis(jedis, isSuccess);
		}
	}

	@Override
	public void deleteKeys(String... keys) {
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
			Long res = jedis.del(keys);
			isSuccess = 1 == res.intValue() ? true : false;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}", e);
			throw e;
		} finally {
			closeJedis(jedis, isSuccess);
		}
	}

	@Override
	public void deleteKeyPattern(String keyPattern) {
		if (null == keyPattern || "".equals(keyPattern)) {
			return;
		}
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
			Set<String> keySet = jedis.keys(keyPattern);
			String[] keys = keySet.toArray(new String[0]);
			jedis.del(keys);
			return;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}", e);
			throw e;
		} finally {
			closeJedis(jedis, isSuccess);
		}
	}
	
	

	

}
