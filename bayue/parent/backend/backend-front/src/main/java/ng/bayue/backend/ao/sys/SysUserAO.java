package ng.bayue.backend.ao.sys;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.domain.dto.SysUserDTO;
import ng.bayue.backend.service.SysRoleService;
import ng.bayue.backend.service.SysUserRoleService;
import ng.bayue.backend.service.SysUserService;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.util.Page;

@Service
public class SysUserAO {

	private static final Logger logger = LoggerFactory.getLogger(SysUserAO.class);

	@Autowired
	private SysUserService sysUserService;
	@Autowired
	private SysUserRoleService sysUserRoleService;
	@Autowired
	private SysRoleService sysRoleService;

	public Page<SysUserDTO> queryPage(SysUserDO sysUserDO, Integer pageNo, Integer pageSize) {
		Page<SysUserDTO> result = new Page<SysUserDTO>();
		Page<SysUserDO> page = sysUserService.queryPageListBySysUserDOAndStartPageSize(sysUserDO, pageNo, pageSize);

		result.setPageNo(page.getPageNo());
		result.setPageSize(page.getPageSize());
		result.setTotalCount(page.getTotalCount());

		List<SysUserDO> listUsers = page.getList();
		if (CollectionUtils.isEmpty(listUsers)) {
			return result;
		}
		List<Long> userIds = new ArrayList<Long>();
		for (SysUserDO su : listUsers) {
			userIds.add(su.getId());
		}
		List<SysUserRoleDO> listSUR = sysUserRoleService.selectByUserIds(userIds);
		List<Long> roleIds = new ArrayList<Long>();
		for (SysUserRoleDO sur : listSUR) {
			roleIds.add(sur.getRoleId());
		}
		List<SysRoleDO> listRoles = sysRoleService.selectByIds(roleIds);
		List<SysUserDTO> listResult = new ArrayList<SysUserDTO>();
		for (SysUserDO su : listUsers) {
			SysUserDTO dto = new SysUserDTO();
			try {
				BeanUtils.copyProperties(dto, su);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("", e);
			}
			long id = su.getId();
			String strRoleName = "";
			for (SysUserRoleDO sur : listSUR) {
				long id2 = sur.getUserId();
				long roleId = sur.getRoleId();
				if (id != id2) {
					continue;
				}
				for (SysRoleDO sr : listRoles) {
					long roleId2 = sr.getId();
					if (roleId == roleId2) {
						strRoleName += sr.getName() + ",";
					}
				}
			}
			if (StringUtils.isNotEmpty(strRoleName)) {
				dto.setRoles(strRoleName.substring(0, strRoleName.length() - 1));
			}
			listResult.add(dto);
		}
		result.setList(listResult);
		return result;
	}

	public ResultMessage save(SysUserDO sysUserDO, String roleIds) {
		if (validIsExists(sysUserDO)) {
			return ResultMessage.validIsExist();
		}
		sysUserDO.setCreateTime(new Date());
		sysUserDO.setModifyTime(new Date());
		sysUserDO.setCreateUserId(UserHandler.getUser().getId());
		sysUserDO.setModifyUserId(UserHandler.getUser().getId());
		if (StringUtils.isEmpty(roleIds)) {
			sysUserService.insert(sysUserDO);
			return new ResultMessage();
		}
		List<Long> list = new ArrayList<Long>();
		String[] strs = roleIds.split(",");
		for (String str : strs) {
			list.add(Long.parseLong(str));
		}
		sysUserService.saveUserAndRelationRoles(sysUserDO, list);
		return new ResultMessage();
	}

	public boolean validIsExists(SysUserDO valid) {
		String loginName = valid.getLoginName();
		String email = valid.getEmail();
		String mobile = valid.getMobile();
		SysUserDO sysUserDO = new SysUserDO();
		sysUserDO.setLoginName(loginName);
		sysUserDO.setMobile(mobile);
		sysUserDO.setEmail(email);
		List<SysUserDO> list = sysUserService.selectDynamic(sysUserDO);
		return CollectionUtils.isEmpty(list) ? false : true;
	}

	public List<SysUserRoleDO> selectRolesByUserId(Long userId) {
		if (null == userId) {
			return null;
		}
		SysUserRoleDO sysUserRole = new SysUserRoleDO();
		sysUserRole.setUserId(userId);
		List<SysUserRoleDO> list = sysUserRoleService.selectDynamic(sysUserRole);
		return list;
	}

	public SysUserDO selectByUserId(Long userId) {
		if (null == userId) {
			return null;
		}
		SysUserDO sysUser = sysUserService.selectById(userId);
		return sysUser;
	}

	public ResultMessage sysUserUpdate(SysUserDO sysUserDO, String roleIds) {
		sysUserDO.setModifyTime(new Date());
		sysUserDO.setModifyUserId(UserHandler.getUser().getId());
		List<Long> ids = new ArrayList<Long>();
		if (StringUtils.isNotEmpty(roleIds)) {
			String[] strs = roleIds.split(",");
			for (String str : strs) {
				ids.add(Long.parseLong(str));
			}
		}
		sysUserService.updateUserAndRelationRoles(sysUserDO, ids);
		return new ResultMessage();
	}

	public SysUserDO findByLoginNameOrMobileOrEmail(String param) {
		if (StringUtils.isEmpty(param)) {
			return null;
		}
		return sysUserService.findByLoginNameOrEmailOrMobile(param);
	}

	/**
	 * <pre>
	 * 使用管理员权限的用户重置其他用户的密码，密码重置为登录名
	 * </pre>
	 *
	 * @param userId
	 * @param loginName
	 */
	public void resetPassword(Long userId, String loginName) {
		if (null == userId || userId.longValue() < 1 || StringUtils.isEmpty(loginName)) {
			return;
		}
		sysUserService.updatePassword(userId, loginName, UserHandler.getUser().getId());
	}

}
