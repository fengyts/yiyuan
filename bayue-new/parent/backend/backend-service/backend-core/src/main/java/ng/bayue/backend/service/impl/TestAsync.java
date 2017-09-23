package ng.bayue.backend.service.impl;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service(value="testAsync")
public class TestAsync {
	
	@Async
	public void testAsync(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("hahaha");
	}

}
