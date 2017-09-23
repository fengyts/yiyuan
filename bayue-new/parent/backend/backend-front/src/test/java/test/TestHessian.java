package test;


import java.net.MalformedURLException;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.service.SysRoleService;
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
	String url2 = "http://localhost:8089/backend-web/sysRoleService.hessian";
	
	public void testHessian(){
		try {
//			CategoryService categoryService = (CategoryService) factory.create(CategoryService.class, url);
//			CategoryDO categoryDO = categoryService.selectById(2L);
//			System.out.println(categoryDO.getName());
//			System.out.println(categoryDO.getCode());
			
			SysRoleService sysRoleService = (SysRoleService) factory.create(SysRoleService.class,url2);
			SysRoleDO sysRoleDO = sysRoleService.selectById(1L);
			System.out.println(sysRoleDO.getName());
			
		} catch (MalformedURLException e) {
			logger.error("", e);
		}
	}
	
	public static void main(String[] args) {
		TestHessian hessian = new TestHessian();
		hessian.testHessian();
	}
	
}
