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
	
	void deleteRedisCache(String key);

}
