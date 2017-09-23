package ng.bayue.backend.persist.mybatis;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysMenuRoleDO;
import ng.bayue.backend.persist.common.MybatisBaseDAO;
import ng.bayue.backend.persist.dao.SysMenuRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;

@Component(value="sysMenuRoleDAO")
public class MybatisSysMenuRoleDAO extends MybatisBaseDAO implements SysMenuRoleDAO {
	public Long insert(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_insert", sysMenuRoleDO);
		if (i > 0) {
			return Long.valueOf(sysMenuRoleDO.getRoleId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_updateByRoleId", sysMenuRoleDO);
	}

	@Override
	public Integer deleteByRoleId(Long roleId) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_deleteByRoleId", roleId);
	}

	@Override
	public Integer updateDynamic(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_update_dynamic", sysMenuRoleDO);
	}

	@Override
	public List<SysMenuRoleDO> selectByRoleId(Long roleId) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_selectByRoleId", roleId);
	}

	@Override
	public void insertBatch(Map<String,Object> map) throws DAOException {
		getSqlSession().insert("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_insert_batch", map);
	}
	

}
