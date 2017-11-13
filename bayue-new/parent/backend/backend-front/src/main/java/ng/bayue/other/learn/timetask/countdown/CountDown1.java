package ng.bayue.other.learn.timetask.countdown;

import java.util.concurrent.TimeUnit;

public class CountDown1 {

	private int counter; // 计数器

	public void countDown(int counter) throws InterruptedException {
		this.counter = counter;
		System.out.println(counter);
		while (counter > 0) {
			System.out.println("remians " + --counter + " s");
			TimeUnit.SECONDS.sleep(1);
		}
		System.out.println("time is out");
	}

	public static void main(String[] args) {
		CountDown1 cd = new CountDown1();
		try {
			cd.countDown(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
