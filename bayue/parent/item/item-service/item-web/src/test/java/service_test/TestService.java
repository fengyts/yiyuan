package service_test;

import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.service.ItemInfoService;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-beans.xml"})
public class TestService {
	
	@Autowired
	private ItemInfoService itemInfoService;
	
	@Test
	public void test(){
		String spu = "010101";
		ItemInfoDO infoDO = itemInfoService.selectBySPU(spu);
		System.out.println(ToStringBuilder.reflectionToString(infoDO));
	}

}
