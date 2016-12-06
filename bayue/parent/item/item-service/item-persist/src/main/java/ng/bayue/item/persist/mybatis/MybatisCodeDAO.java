package ng.bayue.item.persist.mybatis;
import java.util.List;

import org.springframework.stereotype.Component;
import ng.bayue.item.domain.CodeDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.persist.dao.CodeDAO;

@Component(value="codeDAO")
public class MybatisCodeDAO extends MybatisBaseDAO implements CodeDAO {
	
	private static final String NAMESPACE = "ng.bayue.item.domain.CodeMapper.MybatisCodeDAO_";
	
	public Long insert(CodeDO codeDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", codeDO);
		if (i > 0) {
			return Long.valueOf(codeDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(CodeDO codeDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", codeDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(CodeDO codeDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", codeDO);
	}

	@Override
	public CodeDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(CodeDO codeDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", codeDO);
	}

	@Override
	public List<CodeDO> selectDynamic(CodeDO codeDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", codeDO);
	}

	@Override
	public List<CodeDO> selectDynamicPageQuery(CodeDO codeDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", codeDO);
	}

	@Override
	public Integer updateCode(String code) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_increase", code);
	}

}
