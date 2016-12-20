package ng.bayue.base.junit.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.CategoryDAO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.ForbiddenWordsService;
import ng.bayue.base.service.SpecGroupService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-beans.xml"})
public class TestCategoryService {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private SpecGroupService groupSerivce;
	
	@Test
	public void testBatch(){
		List<Long> groupIds = new ArrayList<Long>();
		groupIds.add(1L);
		groupIds.add(2L);
		List<SpecGroupDO> l = groupSerivce.selectByIds(groupIds);
		System.out.println(l.size());
	}
	
	@Test
	public void testDAO() throws DAOException{
	}
	
	@Test
	public void testCategoryService(){
		CategoryDO categoryDO = new CategoryDO();
		categoryDO.setParentId(0L);
		List<CategoryDO> list = categoryService.selectDynamic(categoryDO);
//		JSONObject o = JSONObject.parseObject(JSONObject.toJSONString(list));
		JSONArray arr = (JSONArray) JSONArray.toJSON(list);
		System.out.println(arr);
	}
	
	@Test
	public void strJsonDemo(){
		String str= "{\"people\":"
				+ "[{\"firstName\":\"Brett\",\"lastName\":\"McLaughlin\",\"email\":\"aaaa\"},"
				+ "{\"firstName\":\"Jason\",\"lastName\":\"Hunter\",\"email\":\"bbbb\"},"
				+ "{\"firstName\":\"Elliotte\",\"lastName\":\"Harold\",\"email\":\"cccc\"}]}";
		JSONObject o = JSONObject.parseObject(str);
		System.out.println(o);
		
	}

}
