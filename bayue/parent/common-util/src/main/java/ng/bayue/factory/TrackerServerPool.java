package ng.bayue.factory;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.csource.fastdfs.TrackerServer;

public class TrackerServerPool extends GenericObjectPool<TrackerServer> {

	public TrackerServerPool(PooledObjectFactory<TrackerServer> factory,
			GenericObjectPoolConfig config) {
		super(factory, config);
	}

}
