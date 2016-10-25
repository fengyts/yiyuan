package ng.bayue.backend.shiro_old;

import java.util.Calendar;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.AbstractCacheManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;

/**
 * <p>User: 喻文杰
 * <p>Date: 2014年12月4日
 * <p>Version: Meitun-Shiro Demo 1.0
 */

/**
 * @author warlock
 * 基于redisCache重做
 * 增加重试次数，重试锁定时间
 *
 */
public class RetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Cache<String, AtomicLong> passwordRetryCache;
    private static final String PREFIX_RETRYCOUNT = "shiro_redis_retryCount:";
    private static final String PREFIX_TIME_STAMP = "shiro_redis_timestamp:";
    private static final int retry_times = 5;
    private static final int retry_minutes = 1;
    
    public RetryLimitHashedCredentialsMatcher(){
    	passwordRetryCache = null;
    }

    public RetryLimitHashedCredentialsMatcher(CacheManager cacheManager) {
        passwordRetryCache = cacheManager.getCache("passwordRetryCache");
    }
    
    public static void main(String[] args) {
    	AtomicInteger retryCount = new AtomicInteger(0);
    	System.out.println(retryCount.getAndIncrement());
    	System.out.println(retryCount.get());
    	System.out.println(retryCount.get());
	}

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
//    	RedisCache<String, AtomicLong> cache = (RedisCache<String, AtomicLong>) passwordRetryCache;
    	ShiroUPToken t = (ShiroUPToken) token;
        String username = (String)t.getUsername();
//        AtomicLong retryCount = cache.get(PREFIX_RETRYCOUNT+username);
        AtomicLong retryCount = null;
        if(retryCount == null) {
            retryCount = new AtomicLong(0);
//            cache.put(PREFIX_RETRYCOUNT+username, retryCount);
        }
        
        if(retryCount.incrementAndGet() > retry_times )  {
//        	if(!marginTime(Calendar.getInstance().getTimeInMillis(), cache.get(PREFIX_TIME_STAMP+username).get())){
//        		throw new ExcessiveAttemptsException("登录次数失败超过"+retry_times+"次,请在"+retry_minutes+"分钟后重试");	
//        	}else {
//        		cache.put(PREFIX_RETRYCOUNT+username, new AtomicLong(1));
//        	}
        }

        boolean matches = super.doCredentialsMatch(token, info);
        if(matches){
//        	cache.remove(PREFIX_RETRYCOUNT+username);	
//        	cache.remove(PREFIX_TIME_STAMP+username);
        }else {
//        	cache.put(PREFIX_RETRYCOUNT+username, retryCount);
//        	cache.put(PREFIX_TIME_STAMP+username,new AtomicLong(Calendar.getInstance().getTimeInMillis()));
        }
        return matches;
    }
    
    public boolean marginTime(long time1,long time2){
    	return (time1-time2)/1000 >= 60 * retry_minutes;
    }
}
