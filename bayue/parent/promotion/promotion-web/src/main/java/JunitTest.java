import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import ng.bayue.promotion.domain.TopicItemDO;
import ng.bayue.promotion.service.TopicItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath*:/spring/spring-beans.xml"})
public class JunitTest {

	@Autowired
	TopicItemService itemService;
	
	@Test
	public void test(){
		List<Long> detailIds = new ArrayList<Long>();
		detailIds.add(1L);
		detailIds.add(2L);
		List<TopicItemDO> list = itemService.validItemStatus(detailIds);
		System.out.println(list.size());
	}
	
}
