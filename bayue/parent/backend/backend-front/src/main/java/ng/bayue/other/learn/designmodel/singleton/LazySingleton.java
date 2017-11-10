package ng.bayue.other.learn.designmodel.singleton;

/**
 * <pre>
 * 
 * </pre>
 *
 * @author lenovopc
 * @version $Id: LazySingleton.java, v 0.1 2017年11月1日 下午4:34:27 lenovopc Exp $
 */
public class LazySingleton {

	private LazySingleton() {
	}

	public static LazySingleton singleton = null;

	public static LazySingleton getInstance() {
		singleton = new LazySingleton();
		return singleton;
	}

}
