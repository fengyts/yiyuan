package testservice;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.domain.dto.SysUserVO;
import ng.bayue.backend.persist.dao.SysUserDAO;
import ng.bayue.backend.service.SysMenuRoleService;
import ng.bayue.backend.service.SysMenuService;
import ng.bayue.backend.service.SysUserRoleService;
import ng.bayue.backend.service.SysUserService;
import ng.bayue.backend.service.impl.TestAsync;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-beans.xml" })
public class TestCategoryService {
	
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private SysUserService sysUserService;
	
	@Autowired
	private TestAsync testAsync;
	
	@Autowired
	private SysMenuRoleService sysMenuRoleService;
	
	@Autowired
	private SysUserRoleService sysUserRoleService;
	
	@Autowired
	private SysUserDAO sysUserDAO;
	
	
	@Test
	public void test(){
		SysMenuDO smDO = sysMenuService.selectById(1L);
		System.out.println(smDO.getName());
		
		testAsync.testAsync();
		
		SysUserDO sysUserDO = sysUserService.selectById(1L);
		System.out.println(sysUserDO.getLoginName());
		
		System.out.println("testSync");
		
	}
	
	@Test
	public void test1(){
		SysUserDO sysUser = sysUserService.findByLoginNameOrEmailOrMobile("superadmin");
		System.out.println(sysUser.getEmail());
	}

}
