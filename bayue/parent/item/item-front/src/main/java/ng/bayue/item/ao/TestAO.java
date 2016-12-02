package ng.bayue.item.ao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.service.CategoryService;

@Service
public class TestAO {
	
	@Autowired
	private CategoryService categoryService;
	
	public void test(){
		categoryService.selectById(1L);
	}

}
