package ng.bayue.other.learn.designmodel.observer;

import java.util.Observable;
import java.util.Observer;

public class MyObserver implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		MyObservable o1 = (MyObservable) o;
//		System.out.println("has changed:"+o1.isFlag());
		System.out.print("file has changed");
	}

}
