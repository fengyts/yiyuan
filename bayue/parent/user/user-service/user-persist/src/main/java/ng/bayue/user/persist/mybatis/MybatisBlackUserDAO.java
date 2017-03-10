package ng.bayue.user.persist.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.exception.DAOException;
import ng.bayue.user.domain.BlackUserDO;
import ng.bayue.user.persist.MybatisBaseDAO;
import ng.bayue.user.persist.dao.BlackUserDAO;

@Component(value = "blackUserDAO")
public class MybatisBlackUserDAO extends MybatisBaseDAO implements BlackUserDAO {

	private static final String NAMESPACE = "ng.bayue.user.domain.BlackUserMapper.MybatisBlackUserDAO_";

	public Long insert(BlackUserDO blackUserDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", blackUserDO);
		if (i > 0) {
			return Long.valueOf(blackUserDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(BlackUserDO blackUserDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", blackUserDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(BlackUserDO blackUserDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", blackUserDO);
	}

	@Override
	public BlackUserDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(BlackUserDO blackUserDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", blackUserDO);
	}

	@Override
	public List<BlackUserDO> selectDynamic(BlackUserDO blackUserDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", blackUserDO);
	}

	@Override
	public List<BlackUserDO> selectDynamicPageQuery(BlackUserDO blackUserDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", blackUserDO);
	}

}
