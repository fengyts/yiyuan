package test;


import java.net.MalformedURLException;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.service.CategoryService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.caucho.hessian.client.HessianProxyFactory;


public class TestHessian {
	
	private Logger logger = LoggerFactory.getLogger(TestHessian.class);
	
	HessianProxyFactory factory = new HessianProxyFactory();
	
	String url = "http://localhost:8082/base-web/categoryService.hessian";
	String url1 = "http://localhost:8080/backend-web/sysMenuService.hessian";
	
	public void testHessian(){
		try {
			CategoryService categoryService = (CategoryService) factory.create(CategoryService.class, url);
			CategoryDO categoryDO = categoryService.selectById(2L);
			System.out.println(categoryDO.getName());
			System.out.println(categoryDO.getCode());
			
//			CategoryDO categoryDO = new CategoryDO();
////			categoryDO.setId(1L);
//			categoryDO.setCode("01");
//			categoryDO.setCreateTime(new Date());
//			categoryDO.setLevel(1);
//			categoryDO.setName("test");
//			categoryDO.setParentId(0L);
//			categoryDO.setStatus(false);
//			categoryDO.setCreateUserId(1L);
//			
//			Long res = categoryService.insert(categoryDO);
//			System.out.println(res);
			
		} catch (MalformedURLException e) {
			logger.error("", e);
		}
	}
	
	public static void main(String[] args) {
		TestHessian hessian = new TestHessian();
		hessian.testHessian();
	}
	
}
