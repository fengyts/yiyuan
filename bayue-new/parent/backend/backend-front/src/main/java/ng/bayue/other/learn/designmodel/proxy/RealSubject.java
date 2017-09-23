package ng.bayue.other.learn.designmodel.proxy;

public class RealSubject implements Subject {

	@Override
	public void request() {
		System.out.println("real subject request");
	}

}
