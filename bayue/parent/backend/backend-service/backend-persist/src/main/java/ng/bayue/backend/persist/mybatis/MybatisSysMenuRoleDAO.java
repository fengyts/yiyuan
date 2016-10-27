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
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_updateById", sysMenuRoleDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_update_dynamic", sysMenuRoleDO);
	}

	@Override
	public SysMenuRoleDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_select_dynamic_count", sysMenuRoleDO);
	}

	@Override
	public List<SysMenuRoleDO> selectDynamic(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_select_dynamic", sysMenuRoleDO);
	}

	@Override
	public List<SysMenuRoleDO> selectDynamicPageQuery(SysMenuRoleDO sysMenuRoleDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_select_dynamic_page_query", sysMenuRoleDO);
	}

	@Override
	public void insertBatch(Map<String,Object> map) throws DAOException {
		getSqlSession().insert("ng.bayue.backend.domain.SysMenuRoleMapper.MybatisSysMenuRoleDAO_insert_batch", map);
	}
	

}
