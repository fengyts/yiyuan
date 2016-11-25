package ng.bayue.other.learn.designmodel.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {

	Object obj;

	public DynamicProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("before call method:" + method);
		method.invoke(obj, args);
		System.out.println("after call method:" + method);
		return null;
	}

}
