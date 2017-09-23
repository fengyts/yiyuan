package ng.bayue.base.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.persist.dao.FrontCategoryLinkDAO;
import ng.bayue.exception.CommonDAOException;

@Component(value="frontCategoryLinkDAO")
public class MybatisFrontCategoryLinkDAO extends MybatisBaseDAO implements FrontCategoryLinkDAO {
	
	private static final String NAMESPACE = "ng.bayue.base.domain.FrontCategoryLinkMapper.MybatisFrontCategoryLinkDAO_";
	
	private static String getStatement (String operation){
		return NAMESPACE + operation;
	}
	
	public Long insert(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		int i = getSqlSession().insert(getStatement("insert"), frontCategoryLinkDO);
		if (i > 0) {
			return Long.valueOf(frontCategoryLinkDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("updateById"), frontCategoryLinkDO);
	}

	@Override
	public Integer deleteById(Long id) throws CommonDAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		return getSqlSession().update(getStatement("update_dynamic"), frontCategoryLinkDO);
	}
	
	@Override
	public int updateByFrontCateId(FrontCategoryLinkDO fcLinkDO) {
		return getSqlSession().update(getStatement("updateByFrontCateId_dynamic"), fcLinkDO);
	}

	@Override
	public FrontCategoryLinkDO selectById(Long id) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), frontCategoryLinkDO);
	}

	@Override
	public List<FrontCategoryLinkDO> selectDynamic(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), frontCategoryLinkDO);
	}

	@Override
	public List<FrontCategoryLinkDO> selectDynamicPageQuery(FrontCategoryLinkDO frontCategoryLinkDO) throws CommonDAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), frontCategoryLinkDO);
	}

	@Override
	public int insertBatch(List<FrontCategoryLinkDO> list) {
		return getSqlSession().insert(getStatement("insertBatch"), list);
	}

}
