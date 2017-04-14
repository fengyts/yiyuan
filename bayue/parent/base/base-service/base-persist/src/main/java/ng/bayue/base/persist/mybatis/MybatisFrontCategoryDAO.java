package ng.bayue.base.persist.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;
import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.persist.dao.FrontCategoryDAO;
import ng.bayue.exception.DAOException;

@Component(value = "frontCategoryDAO")
public class MybatisFrontCategoryDAO extends MybatisBaseDAO implements FrontCategoryDAO {

	private static final String NAMESPACE = "ng.bayue.base.domain.FrontCategoryMapper.MybatisFrontCategoryDAO_";

	private static String getStatement(String operation) {
		return NAMESPACE + operation;
	}

	public Long insert(FrontCategoryDO frontCategoryDO) throws DAOException {
		int i = getSqlSession().insert(getStatement("insert"), frontCategoryDO);
		if (i > 0) {
			return Long.valueOf(frontCategoryDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(FrontCategoryDO frontCategoryDO) throws DAOException {
		return getSqlSession().update(getStatement("updateById"), frontCategoryDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(getStatement("deleteById"), id);
	}

	@Override
	public Integer updateDynamic(FrontCategoryDO frontCategoryDO) throws DAOException {
		return getSqlSession().update(getStatement("update_dynamic"), frontCategoryDO);
	}

	@Override
	public FrontCategoryDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(getStatement("selectById"), id);
	}

	@Override
	public Long selectCountDynamic(FrontCategoryDO frontCategoryDO) throws DAOException {
		return getSqlSession().selectOne(getStatement("select_dynamic_count"), frontCategoryDO);
	}

	@Override
	public List<FrontCategoryDO> selectDynamic(FrontCategoryDO frontCategoryDO) throws DAOException {
		return getSqlSession().selectList(getStatement("select_dynamic"), frontCategoryDO);
	}

	@Override
	public List<FrontCategoryDO> selectDynamicPageQuery(FrontCategoryDO frontCategoryDO) throws DAOException {
		return getSqlSession().selectList(getStatement("select_dynamic_page_query"), frontCategoryDO);
	}

	@Override
	public int insertBatch(List<FrontCategoryDO> list) {
		return getSqlSession().insert(getStatement("insertBatch"), list);
	}

	@Override
	public String selectMaxCodeDynamic(FrontCategoryDO fc) {
		return getSqlSession().selectOne(getStatement("select_dynamic_code"), fc);
	}

}
