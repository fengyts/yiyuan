package ng.bayue.service.impl;

import java.util.Collections;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ng.bayue.service.RedisCacheService;
import ng.bayue.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * <pre>
 * 请使用RedisCacheServiceImpl1,该接口实现使用JedisPool,而RedisCacheServiceImpl1使用的是ShardedJedisPool
 * </pre>
 *
 * @author lenovopc
 * @version $Id: RedisCacheServiceImpl.java, v 0.1 2017年3月29日 上午11:35:58 lenovopc Exp $
 */
public class RedisCacheServiceImpl implements RedisCacheService{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	
	/** redis 的key最大值：50k */
	private final static int MAX_KEY = 1024 * 50;
	/** redis 的value最大值：1M */
	private final static int MAX_VALUE = 1024 * 1024;
	
	/** 默认锁的时间, 5分钟 */
	private static final int LOCK_EXPIRE_SECONDS = 5 * 60;
	
	/** redis缓存操作的返回值 */
	private final static String SET_CACHE_RESULT = "OK";
	
	private JedisPool jedisPool = null;
	
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
	
	private void closeJedis(Jedis jedis, boolean isSuccess) {
		if(null != jedis && isSuccess){
			jedisPool.returnResource(jedis);
		}else{
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
		Jedis jedis = null;
		boolean isSuccess = false;
		try {
			validKeyAndValue(key, value.getBytes());
			
			jedis = jedisPool.getResource();
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
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
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
		Jedis jedis = null;
		boolean isSuccess = false;
		try {
			validKeyAndValue(key, values);
			 
			jedis = jedisPool.getResource();
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
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
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
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis =  jedisPool.getResource();
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

	@Override
	public boolean lock(String key, Integer expires) {
		if(keyIsBlank(key)){
			return false;
		}
		if(0 == expires || null == expires){
			expires = LOCK_EXPIRE_SECONDS;
		}
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
			jedis.setnx(key, System.currentTimeMillis() + "");
			Long res = jedis.expire(key, expires);
			isSuccess = 1L == res ? true : false;
		} catch (Exception e) {
			isSuccess = false;
			logger.error(e.getMessage(), e);
		} finally {
			closeJedis(jedis, isSuccess);
		}
		return false;
	}

	@Override
	public boolean lock(String key) {
		return this.lock(key, LOCK_EXPIRE_SECONDS);
	}

	@Override
	public boolean unLock(String key) {
		return false;
	}

	@Override
	public boolean keyExists(String key) {
		return false;
	}

	@Override
	public boolean watchMethodCall(String key, int expireSeconds, int maxNum) {
		return false;
	}

	@Override
	public boolean updateExpire(String key, int expireSeconds) {
		if(null == key || "".equals(key)){return false;}
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis =  jedisPool.getResource();
			Long res = jedis.expire(key, expireSeconds);
			isSuccess = 1 == res.intValue() ? true : false;
			return true;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}", e);
			throw e;
		}finally{
			closeJedis(jedis, isSuccess);
		}
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
