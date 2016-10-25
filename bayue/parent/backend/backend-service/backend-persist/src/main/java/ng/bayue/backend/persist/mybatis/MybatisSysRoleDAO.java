package ng.bayue.backend.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.backend.domain.SysRoleDO;
import ng.bayue.backend.persist.common.MybatisBaseDAO;
import ng.bayue.backend.persist.dao.SysRoleDAO;
import ng.bayue.backend.persist.exception.DAOException;

@Component(value="sysRoleDAO")
public class MybatisSysRoleDAO extends MybatisBaseDAO implements SysRoleDAO {
	public Long insert(SysRoleDO sysRoleDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_insert", sysRoleDO);
		if (i > 0) {
			return Long.valueOf(sysRoleDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysRoleDO sysRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_updateById", sysRoleDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SysRoleDO sysRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_update_dynamic", sysRoleDO);
	}

	@Override
	public SysRoleDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SysRoleDO sysRoleDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_select_dynamic_count", sysRoleDO);
	}

	@Override
	public List<SysRoleDO> selectDynamic(SysRoleDO sysRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_select_dynamic", sysRoleDO);
	}

	@Override
	public List<SysRoleDO> selectDynamicPageQuery(SysRoleDO sysRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysRoleMapper.MybatisSysRoleDAO_select_dynamic_page_query", sysRoleDO);
	}

}
