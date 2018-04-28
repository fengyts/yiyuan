package ng.bayue.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GeneralFactory<T> extends AbstratFactory<T> {

	private final static Logger logger = LoggerFactory.getLogger(GeneralFactory.class);

	private Class<T> classType;

	public GeneralFactory(Class<T> classType) {
		this.classType = classType;
	}

	@Override
	public T getInstance() {
		try {
			if (!hasDefaultConstructor()) {
				logger.info(classType.getName()
						+ " has not default constructor! The generalFactory instantaition failure!!!");
				return null;
			}
			T t = classType.newInstance();
			return t;
		} catch (InstantiationException | IllegalAccessException e) {
			try {
				Constructor<T> c = classType.getDeclaredConstructor();
				c.setAccessible(true);
				return c.newInstance();
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e1) {
				logger.error("实例化对象异常:{}", classType, e);
			}
		}
		return null;
	}

	@Override
	public T getInstance(Object... initargs) {
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (Object o : initargs) {
			Class<?> c = o.getClass();
			list.add(c);
		}
		@SuppressWarnings("rawtypes")
		Class[] parameterTypes = list.toArray(new Class[0]);
		/*
		 * int len = params.length; Class[] parameterTypes = new Class[len];
		 * for(int i=0;i<len;i++){ Class<?> c = params[i].getClass();
		 * parameterTypes[i] = c; }
		 */

		Constructor<T> constructor;
		try {
			constructor = classType.getDeclaredConstructor(parameterTypes);
			return constructor.newInstance(initargs);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			logger.error("实例化对象异常:{}", classType, e);
		}
		return null;
	}

	public boolean hasDefaultConstructor() {
		Constructor<?>[] constructors = classType.getDeclaredConstructors();
		for (Constructor<?> con : constructors) {
			Type[] types = con.getGenericParameterTypes();
			return 0 == types.length ? true : false;
		}
		return false;
	}

}
