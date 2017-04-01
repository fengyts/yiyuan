package ng.bayue.base.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.persist.dao.FrontCategoryLinkDAO;
import ng.bayue.exception.DAOException;

@Component(value="frontCategoryLinkDAO")
public class MybatisFrontCategoryLinkDAO extends MybatisBaseDAO implements FrontCategoryLinkDAO {
	
	private static final String NAMESPACE = "ng.bayue.base.domain.FrontCategoryLinkMapper.MybatisFrontCategoryLinkDAO_";
	
	public Long insert(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", frontCategoryLinkDO);
		if (i > 0) {
			return Long.valueOf(frontCategoryLinkDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", frontCategoryLinkDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", frontCategoryLinkDO);
	}

	@Override
	public FrontCategoryLinkDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", frontCategoryLinkDO);
	}

	@Override
	public List<FrontCategoryLinkDO> selectDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", frontCategoryLinkDO);
	}

	@Override
	public List<FrontCategoryLinkDO> selectDynamicPageQuery(FrontCategoryLinkDO frontCategoryLinkDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", frontCategoryLinkDO);
	}

}
