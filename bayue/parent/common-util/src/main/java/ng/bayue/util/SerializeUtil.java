package ng.bayue.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * 序列化和反序列化工具类
 * </pre>
 *
 * @author fengyts
 * @version $Id: SerializeUtil.java, v 0.1 2016年8月7日 下午1:52:30 fengyts Exp $
 */
public class SerializeUtil {

	private static Logger logger = LoggerFactory.getLogger(SerializeUtil.class);

	public static byte[] serialize(Object o) {
		if (null == o) {
			return null;
		}
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(o);
			byte[] bytes = baos.toByteArray();
			return bytes;
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}

	public static Object unSerialize(byte[] bytes) {
		if (null == bytes) {
			return null;
		}
		try {
			ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
			ObjectInputStream ois = new ObjectInputStream(bais);
			Object o = ois.readObject();
			return o;
		} catch (IOException e) {
			logger.error("", e);
		} catch (ClassNotFoundException e) {
			logger.error("", e);
		}

		return null;
	}

}
