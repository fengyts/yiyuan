package ng.bayue.other.learn.designmodel.observer;

import java.io.File;

public class Test {
	
	public static void test(){
		MyObservable o = new MyObservable();
		o.addObserver(new MyObserver());
		o.advisor(true);
		o.advisor(false);
	}
	
	public static void testFileChange(){
		File file = new File("E:/test/test.txt");
		MyObservable o = new MyObservable();
		o.addObserver(new MyObserver());
		o.monitorFile(file);
		
	}
	
	public static void main(String[] args) {
		test();
	}
	
}
