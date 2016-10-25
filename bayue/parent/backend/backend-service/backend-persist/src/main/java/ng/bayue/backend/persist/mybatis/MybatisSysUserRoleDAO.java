package ng.bayue.backend.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysUserRoleDO;
import ng.bayue.backend.persist.common.MybatisBaseDAO;
import ng.bayue.backend.persist.dao.SysUserRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;

@Component(value="sysUserRoleDAO")
public class MybatisSysUserRoleDAO extends MybatisBaseDAO implements SysUserRoleDAO {
	public Long insert(SysUserRoleDO sysUserRoleDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_insert", sysUserRoleDO);
		if (i > 0) {
			return Long.valueOf(sysUserRoleDO.getUserId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysUserRoleDO sysUserRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_updateById", sysUserRoleDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_update_dynamic", sysUserRoleDO);
	}

	@Override
	public SysUserRoleDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_select_dynamic_count", sysUserRoleDO);
	}

	@Override
	public List<SysUserRoleDO> selectDynamic(SysUserRoleDO sysUserRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_select_dynamic", sysUserRoleDO);
	}

	@Override
	public List<SysUserRoleDO> selectDynamicPageQuery(SysUserRoleDO sysUserRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysUserRoleMapper.MybatisSysUserRoleDAO_select_dynamic_page_query", sysUserRoleDO);
	}

}
