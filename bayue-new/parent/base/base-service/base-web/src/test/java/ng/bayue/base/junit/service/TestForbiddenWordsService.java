package ng.bayue.base.junit.service;

import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.service.ForbiddenWordsService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml" })
public class TestForbiddenWordsService {
	
	@Autowired
	private ForbiddenWordsService forbiddenWordsService;
	
	@Test
	public void test(){
		ForbiddenWordsDO wordsDO = forbiddenWordsService.selectById(1L);
		System.out.println(wordsDO.getWords());
	}

}
