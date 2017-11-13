package ng.bayue.other.learn.timetask.countdown;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class CountDown3 {

	private int limitSec;
	private int curSec;

	public CountDown3(int limitSec) throws InterruptedException {
		this.limitSec = limitSec;
		this.curSec = limitSec;
		System.out.println("count down from " + limitSec + " s ");
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				System.out.println("Time remians " + --curSec + " s");
			}
		}, 0, 1000);
		TimeUnit.SECONDS.sleep(limitSec);
		timer.cancel();
		System.out.println("Time is out!");
	}

	public static void main(String[] args) throws InterruptedException {
		new CountDown3(5);
	}

}
