package ng.bayue.learn.thread;

public class ThreadA extends Thread {

	@Override
	public void run() {
		MyThread.test("a", "aa");
	}

}
