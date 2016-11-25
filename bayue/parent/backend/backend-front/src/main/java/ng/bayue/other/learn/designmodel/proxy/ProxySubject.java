package ng.bayue.other.learn.designmodel.proxy;

public class ProxySubject implements Subject {

	RealSubject subject = null;

	@Override
	public void request() {
		subject = new RealSubject();
		System.out.println("real request");
		doOther();
	}

	public static void doOther() {
		System.out.println("do something");
	}

}
