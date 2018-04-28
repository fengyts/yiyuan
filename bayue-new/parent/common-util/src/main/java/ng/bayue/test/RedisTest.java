package ng.bayue.test;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.util.Pool;

public class RedisTest {
	
	private static String host = "47.94.199.26";
	private static int port = 6379;
	private static int timeout = 3000;
	private static String password = "redm808sqc";
	
	
	private int maxIdle = 8;
	private int maxTotal = 8;
	private int minIdle = 0;
	private long maxWaitMillis = 1;
	private static GenericObjectPoolConfig poolConfig;
	
	private static Pool<?> pool;
	
	
	public void initPoolConfig(){
		poolConfig = new GenericObjectPoolConfig();
		poolConfig.setMaxIdle(maxIdle);
		poolConfig.setMaxTotal(maxTotal);
		poolConfig.setMinIdle(minIdle);
		poolConfig.setMaxWaitMillis(maxWaitMillis);
	}
	
	@Before
	public void initPool(){
		initPoolConfig();
		pool = new JedisPool(poolConfig, host, port, timeout, password);
	}
	
	public void shardJedis(){
		JedisShardInfo shardInfo = new JedisShardInfo(host);
		shardInfo.setPassword(password);
	}
	
	@Test
	public void test(){
		Jedis jedis = (Jedis) pool.getResource();
		jedis.auth(password);
		jedis.set("test", "test123value");
	}

}
