package ng.bayue.redis;

import ng.bayue.util.SerializeUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedisPipeline;

@Deprecated
public class RedisCacheUtil {

	private static Logger logger = LoggerFactory.getLogger(RedisCacheUtil.class);

	private final static String SET_CACHE_RESULT = "OK";
	
//	private final static String ADDRESS = "192.168.235.128";
	private final static String ADDRESS = "192.168.58.129";
	
	private static final int DEFAULT_PORT = 6379;
	
	/** redis 的key最大值：50k */
	private final static int MAX_KEY = 1024 * 50;
	/** redis 的value最大值：1M */
	private final static int MAX_VALUE = 1024 * 1024;

//	static Jedis jedis = null;
	static JedisPool jedisPool = null;
	static CacheShardedJedisPool shardedJedisPool = null;

	static {
//		jedis = new Jedis("192.168.18.128", 6379);
		jedisPool = new JedisPool(new JedisPoolConfig(),ADDRESS);
	}
	
	public static String getRedisCacheString(String key){
		if(null == key || "".equals(key.trim())){
			logger.info("get redis data error:the paramter key is not allowed null");
			return null;
		}
		Jedis jedis = null;
		boolean isSuccess = true;
		try {
			jedis = jedisPool.getResource();
			jedis.select(1);
			String result = jedis.get(key);
			return result;
		} catch (Exception e) {
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
	
	/**
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param key
	 * @param value
	 * @param expires 缓存时间,单位：秒 
	 * @return
	 */
	public static boolean setRedisCacheString(String key, String value, Integer expires){
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
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
		}
		return isSuccess;
	}

	public static Object getRedisCache(String key) {
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

	public static boolean setRedisCache(String key, Object o, Integer expires) {
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
			jedis.select(1);
			String res = "";
			if(o instanceof String){
				jedis.set(key, (String) o);
			}else{
				res = jedis.set(bytesKey, values);
			}
			if (SET_CACHE_RESULT.equalsIgnoreCase(res)) {
				logger.info("key:{}, redis cache is success!", key);
				isSuccess = true;
			}
			if (null != expires) {
				Long time = jedis.expire(bytesKey, expires);
				logger.info("key:{}, redis cache time is {} second", key, expires);
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
	
	public static void deleteRedisCache(String key){
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
			if(null != jedis && isSuccess){
				jedisPool.returnResource(jedis);
			}else{
				jedisPool.returnBrokenResource(jedis);
			}
		}
	}
	
	public static void testRedis() throws UnsupportedEncodingException{
		RedisCacheUtil.setRedisCache("test", "haha", 30);
		String str = RedisCacheUtil.getRedisCacheString("test");
		System.out.println(str);
		Thread thread = new Thread();
		try {
			thread.sleep(5000);
			deleteRedisCache("test");
		} catch (InterruptedException e) {
			logger.error("", e);
		}
	}

	public static void main(String[] args) throws Exception {
		testRedis();
		
		// Jedis jedis = new Jedis("192.168.18.128",6379);

//		RedisCacheUtil cacheUtil = new RedisCacheUtil();
//		String str = (String) cacheUtil.getRedisCacheString("test");
//		System.out.println(str);
		
//		cacheUtil.setRedisCache("aa", "haha", 10);
//		cacheUtil.setRedisCache("bb", "test_jedis_cache_time", RedisCacheTimeConstant.TEN_MINUTES);

		// UserDO user = new UserDO();
		// user.setId(1L);
		// user.setName("张三");
		// user.setEmail("test@163.com");
		// user.setPasswd("123456");
		// user.setMobile("12345678901");
		// user.setSalt("salt");
		// cacheUtil.setRedisCache("user1", user,null);
//		 UserDO u = (UserDO) cacheUtil.getRedisCache("user1");
//		 System.out.println(u.getName()+" "+u.getMobile());

		// HashMap map = (HashMap) cacheUtil.getRedisCache("user");

		// Map<String,String> map = new HashMap<String,String>();
		// map.put("mapKey1", "mapValue1");
		// map.put("mapKey2","mapValue2");
		// cacheUtil.setRedisCache("testMap", map,null);
		//
		// Map<String,String> mapTest = (Map<String, String>)
		// cacheUtil.getRedisCache("testMap");
		// System.out.println(mapTest.get("mapKey1"));

	}

}
