package ng.bayue.other.learn.timetask.countdown;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class CountDown2 {

	private int counter; // 计时器
	private int currentSec; // 记录当下时间

	public CountDown2(int counter) throws InterruptedException {
		this.counter = counter;
		this.currentSec = counter;
		System.out.println("count down form " + counter);

		ScheduledExecutorService exec = Executors.newScheduledThreadPool(1);
		exec.scheduleAtFixedRate(new Task(), 0, 1, TimeUnit.SECONDS);
		TimeUnit.SECONDS.sleep(counter); // 暂停本线程
		exec.shutdownNow();
		System.out.println("Time out！");
	}

	private class Task implements Runnable {
		public void run() {
			System.out.println("Time remains " + --currentSec + " s");
		}
	}
	
	public static void main(String[] args) {
		try {
			CountDown2 cd = new CountDown2(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
