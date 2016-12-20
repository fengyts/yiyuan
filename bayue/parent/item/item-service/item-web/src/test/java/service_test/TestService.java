package service_test;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.service.DetailSpecService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-beans.xml"})
public class TestService {
	
	@Autowired
	private DetailSpecService specService;
	
	@Test
	public void test(){
		List<DetailSpecDO> list = new ArrayList<DetailSpecDO>();
		DetailSpecDO specDO = new DetailSpecDO();
		specDO.setDetailId(1L);
		specDO.setSort(1);
		specDO.setSpecGroupId(1L);
		specDO.setCreateTime(new Date());
		specDO.setModifyTime(new Date());
		list.add(specDO);
		DetailSpecDO specDO1 = new DetailSpecDO();
		specDO1.setDetailId(2L);
		specDO1.setSort(2);
		specDO1.setSpecGroupId(2L);
		specDO1.setCreateTime(new Date());
		specDO1.setModifyTime(new Date());
		list.add(specDO1);
		int res = specService.insertBatch(list);
		System.out.println(res);
	}

}
