package ng.bayue.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import ng.bayue.service.RedisCacheService;
import ng.bayue.util.SerializeUtil;

public class RedisCacheServiceImpl implements RedisCacheService{
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCacheServiceImpl.class);
	
	/** redis 的key最大值：50k */
	private final static int MAX_KEY = 1024 * 50;
	/** redis 的value最大值：1M */
	private final static int MAX_VALUE = 1024 * 1024;
	
	/** redis缓存操作的返回值 */
	private final static String SET_CACHE_RESULT = "OK";
	
	private static JedisPool jedisPool = null;

	public static JedisPool getJedisPool() {
		return jedisPool;
	}

	public static void setJedisPool(JedisPool jedisPool) {
		RedisCacheServiceImpl.jedisPool = jedisPool;
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
			if (MAX_KEY < key.getBytes().length) {// key最大50k
				logger.info("redis key is to long:{}", key);
				throw new Exception("redis key is to long:" + key);
			}
			if (MAX_VALUE < value.getBytes().length) {// key最大50k
				logger.info("redis key is to long:{}", key);
				throw new Exception("redis key is to long:" + key);
			}
			jedis = jedisPool.getResource();
			String result = jedis.set(key, value);
			if(SET_CACHE_RESULT.equals(result)){
				isSuccess = true;
				if(null != expires){
					jedis.expire(key, expires);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			logger.info("set redis string data exception:{}", e);
		}finally{
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
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
			// TODO: handle exception
			isSuccess = false;
			logger.info("get redis string data exception:{}", e);
		}finally{
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
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
			if (MAX_KEY < bytesKey.length) {// key最大50k
				logger.info("redis key is to long:{}", key);
				throw new Exception("redis key is to long:" + key);
			}
			
			 if(MAX_VALUE < values.length){// value最大值1M
			 	logger.info("redis data is to long:{}",key); 
			 	throw new Exception("redis data is to long:" + key); 
			 }
			 
			jedis = jedisPool.getResource();
			String res = jedis.set(bytesKey, values);
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
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return isSuccess;
	}

	@Override
	public Object getRedisCache(String key) {
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
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
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
			isSuccess = 1 == res ? true : false;
		} catch (Exception e) {
			isSuccess = false;
			logger.info("delete redis cache failure: {}",e);
			throw e;
			// TODO: handle exception
		}finally{
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}

}
