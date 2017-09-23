package ng.bayue.factory;

import java.net.InetSocketAddress;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TrackerServerFactory extends BasePooledObjectFactory<TrackerServer> {

	private final Logger logger = LoggerFactory.getLogger(TrackerServerFactory.class);

	private final String UTF8 = "UTF-8";

	private TrackerClient trackerClient;

	public TrackerServerFactory(String[] addres) {
		int i = 0;
		InetSocketAddress[] isa = null;
		if(null != addres || "".equals(addres)){
			isa = new InetSocketAddress[addres.length];
			for (String ip : addres) {
				String[] kv = ip.split(":");
				isa[i++] = new InetSocketAddress(kv[0], Integer.parseInt(kv[1]));
			}
		}
		if(null != isa){
			ClientGlobal.setG_tracker_group(new TrackerGroup(isa));
		}
		ClientGlobal.setG_charset(UTF8);
		trackerClient = new TrackerClient();
	}

	@Override
	public TrackerServer create() throws Exception {
		try {
			TrackerServer trackerServer = trackerClient.getConnection();
			return trackerServer;
		} catch (Exception e) {
			logger.info("fastdfs get trackerServer connection exception:{}", e);
		}
		return null;
	}

	@Override
	public PooledObject<TrackerServer> wrap(TrackerServer trackerServer) {
		return new DefaultPooledObject<TrackerServer>(trackerServer);
	}

}
