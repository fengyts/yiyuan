package ng.bayue.learn.thread;

public class ThreadB extends Thread {

	@Override
	public void run() {
		super.run();
		MyThread.test("b", "bb");
	}

}
