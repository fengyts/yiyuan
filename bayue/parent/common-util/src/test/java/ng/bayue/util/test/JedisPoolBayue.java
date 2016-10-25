package ng.bayue.util.test;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class JedisPoolBayue {

	private final String HOST = "192.168.18.128";
	private final int DEFAULT_PORT = 6379;

	public Jedis getJedisByPool() {
		Jedis jedis = null;
		JedisPoolConfig jpConf = new JedisPoolConfig();
		JedisPool pool = new JedisPool(jpConf, HOST);
		jedis = pool.getResource();
		String str = jedis.get("foo");
		System.out.println(str);
		pool.returnResource(jedis);
		return jedis;
	}
	
	public void getSharedJedisPool(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		List<JedisShardInfo> shards = new ArrayList<JedisShardInfo>();
		JedisShardInfo shardInfo = new JedisShardInfo(HOST);
		shards.add(shardInfo);
		ShardedJedisPool pool = new ShardedJedisPool(poolConfig, shards);
		ShardedJedis sj = pool.getResource();
		String str = sj.get("test");
		pool.returnResource(sj);
		System.out.println(str);
	}
	
	public static void main(String[] args) {
		JedisPoolBayue jpb = new JedisPoolBayue();
		jpb.getSharedJedisPool();
	}

}
