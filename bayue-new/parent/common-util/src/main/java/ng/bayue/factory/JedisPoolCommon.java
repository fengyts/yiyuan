package ng.bayue.factory;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;

public class JedisPoolCommon extends GenericObjectPool<Jedis> {

	public JedisPoolCommon(PooledObjectFactory<Jedis> factory,
			GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
