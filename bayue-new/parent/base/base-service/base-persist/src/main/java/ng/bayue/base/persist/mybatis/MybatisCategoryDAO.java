package ng.bayue.base.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.persist.dao.CategoryDAO;

@Component(value="categoryDAO")
public class MybatisCategoryDAO extends MybatisBaseDAO implements CategoryDAO {
	public Long insert(CategoryDO categoryDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_insert", categoryDO);
		if (i > 0) {
			return Long.valueOf(categoryDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_updateById", categoryDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().update("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_update_dynamic", categoryDO);
	}

	@Override
	public CategoryDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_select_dynamic_count", categoryDO);
	}

	@Override
	public List<CategoryDO> selectDynamic(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_select_dynamic", categoryDO);
	}

	@Override
	public List<CategoryDO> selectDynamicPageQuery(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_select_dynamic_page_query", categoryDO);
	}

	@Override
	public String selectMaxCodeDynamic(CategoryDO categoryDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_select_dynamic_code", categoryDO);
	}

	@Override
	public void updateBatch(List<CategoryDO> list) throws DAOException {
		getSqlSession().update("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_batch_update", list);
	}

	@Override
	public List<CategoryDO> selectByIds(List<Long> ids) throws DAOException {
		return getSqlSession().selectList("ng.bayue.base.domain.CategoryMapper.MybatisCategoryDAO_select_byIds", ids);
	}

}
