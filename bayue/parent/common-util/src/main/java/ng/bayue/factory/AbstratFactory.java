package ng.bayue.factory;

public abstract class AbstratFactory<T> {

	/**
	 * <pre>
	 * 根据默认构造方法实例化对象
	 * </pre>
	 *
	 * @return
	 */
	public abstract T getInstance();
	
	/**
	 * <pre>
	 * 根据带参数的构造方法实例化对象
	 * </pre>
	 *
	 * @param initargs
	 * @return
	 */
	public abstract T getInstance(Object...initargs);
	
}
