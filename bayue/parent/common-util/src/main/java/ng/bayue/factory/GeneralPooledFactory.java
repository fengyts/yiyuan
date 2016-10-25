package ng.bayue.factory;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;

public class GeneralPooledFactory<T> extends BasePooledObjectFactory<T> {
	
	private Class<T> classType;
	
	public GeneralPooledFactory(Class<T> classType){
		this.classType = classType;
	}

	@Override
	public T create() throws Exception {
		return null;
	}

	@Override
	public PooledObject<T> wrap(T obj) {
		return null;
	}

}
