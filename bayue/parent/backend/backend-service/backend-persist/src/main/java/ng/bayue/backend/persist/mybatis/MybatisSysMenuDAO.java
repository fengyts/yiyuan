package ng.bayue.backend.persist.mybatis;
import java.util.List;

import ng.bayue.backend.domain.SysMenuDO;
import ng.bayue.backend.persist.common.MybatisBaseDAO;
import ng.bayue.backend.persist.dao.SysMenuDAO;
import ng.bayue.backend.persist.exception.DAOException;

import org.springframework.stereotype.Component;


@Component(value="sysMenuDAO")
public class MybatisSysMenuDAO extends MybatisBaseDAO implements SysMenuDAO {
	public Long insert(SysMenuDO sysMenuDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_insert", sysMenuDO);
		if (i > 0) {
			return Long.valueOf(sysMenuDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_updateById", sysMenuDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().update("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_update_dynamic", sysMenuDO);
	}

	@Override
	public SysMenuDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_select_dynamic_count", sysMenuDO);
	}

	@Override
	public List<SysMenuDO> selectDynamic(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_select_dynamic", sysMenuDO);
	}

	@Override
	public List<SysMenuDO> selectDynamicPageQuery(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_select_dynamic_page_query", sysMenuDO);
	}
	
	@Override
	public List<SysMenuDO> findListByParentIds(List<SysMenuDO> sysMenuList) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findListByParentIds", sysMenuList);
	}
	
	@Override
	public List<SysMenuDO> findListByIds(List<Long> ids) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findListByIds", ids);
	}
	
	@Override
	public List<SysMenuDO> findParentMenu() throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.findParentMenu");
	}

	@Override
	public List<SysMenuDO> selectByIds(List<Long> ids){
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.selectByIds", ids);
	}
	
	@Override
	public List<SysMenuDO> selectDynamicForUrlIsNull(SysMenuDO sysMenuDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.backend.domain.SysMenuMapper.MybatisSysMenuDAO_select_dynamic_url_is_null", sysMenuDO);
	}
}
