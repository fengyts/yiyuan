package ng.bayue.service;

public interface RedisCacheService {
	
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
	boolean setRedisCacheString(String key,String value,Integer expires);
	
	String getRedisCacheString(String key);
	
	boolean setRedisCache(String key, Object o, Integer expires);
	
	Object getRedisCache(String key);
	
	/**
	 * <pre>
	 * 删除缓存的key
	 * </pre>
	 *
	 * @param key
	 */
	void deleteRedisCache(String key);
	
	/**
	 * 取得有效时间内的锁
	 *
	 * @param key
	 * @param expireSeconds
	 *            有效时间 单位 秒
	 * @return
	 */
	boolean lock(String key, Integer expires);
	
	/**
	 * 获取锁,默认锁定五分钟
	 *
	 * @param key
	 * @return
	 */
	boolean lock(String key);
	
	/**
	 * 释放锁
	 *
	 * @param key
	 * @return
	 */
	boolean unLock(String key);
	
	/**
	 * <pre>
	 * 判断此key是否存在
	 * </pre>
	 *
	 * @param key
	 * @return
	 */
	boolean keyExists(String key);

}
