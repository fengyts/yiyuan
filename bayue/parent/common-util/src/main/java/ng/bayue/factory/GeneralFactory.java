package ng.bayue.factory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

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
			return classType.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error("", e);
		}
		return null;
	}

	public boolean hasDefaultConstructor() {
		Constructor<?>[] constructors = classType.getConstructors();
		for (Constructor<?> con : constructors) {
			Type[] types = con.getGenericParameterTypes();
			return 0 == types.length ? true : false;
		}
		return false;
	}


}
