package ng.bayue.learn.thread;

public class TestRun {

	public static void main(String[] args) {
		ThreadA ta = new ThreadA();
		ta.start();
		ThreadB tb = new ThreadB();
		tb.start();
	}

}
