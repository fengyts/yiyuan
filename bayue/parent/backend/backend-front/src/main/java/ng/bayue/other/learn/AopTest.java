package ng.bayue.other.learn;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AopTest {
	
	public static void main(String[] args) {
		ApplicationContext act = new ClassPathXmlApplicationContext("spring/test-applicationContext.xml");
		
		Workable workProxy = (Workable) act.getBean("work");
		workProxy.work();
		
//		Sleepable sleeper = (Sleepable)act.getBean("human");
//        sleeper.sleep();
		
		
	}

}
