package test;

import ng.bayue.service.RedisCacheService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml",
//		"classpath:spring/spring-web.xml" })
public class RedisTest extends CommonTest{
	
	@Autowired
	private RedisCacheService redisCacheService;
	
	@Test
	public void test(){}
	
	@Test
	public void testRedis(){
		String str = redisCacheService.getRedisCacheString("foo");
		System.out.println(str);
	}

}
