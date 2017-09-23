package ng.bayue.other.learn.designmodel.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

import ng.bayue.other.learn.designmodel.proxy.dynamicproxy.DynamicProxy;

public class ProxyTest {

	public static void test() {
		ProxySubject proxy = new ProxySubject();
		proxy.request();
	}

	public static void testDynamicProxy() {
		RealSubject rSubject = new RealSubject();
		InvocationHandler handler = new DynamicProxy(rSubject);
		Class<?> cl = rSubject.getClass();
		Subject subject = null;
		 //分解步骤
//		try {
//			Class<?> c = Proxy.getProxyClass(cl.getClassLoader(), cl.getInterfaces());
//			Constructor<?> ct = c.getConstructor(new Class[] { InvocationHandler.class });
//			subject = (Subject) ct.newInstance(new Object[] { handler });
//		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
//				| IllegalArgumentException | InvocationTargetException e) {
//			e.printStackTrace();
//		}
		subject = (Subject) Proxy.newProxyInstance(cl.getClassLoader(), cl.getInterfaces(), handler);
		subject.request();

	}

	public static void main(String[] args) {
		// test();
		testDynamicProxy();
	}

}
