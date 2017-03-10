package ng.bayue.user.persist.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.exception.DAOException;
import ng.bayue.user.domain.UserDO;
import ng.bayue.user.persist.MybatisBaseDAO;
import ng.bayue.user.persist.dao.UserDAO;

@Component(value = "userDAO")
public class MybatisUserDAO extends MybatisBaseDAO implements UserDAO {

	private static final String NAMESPACE = "ng.bayue.user.domain.UserMapper.MybatisUserDAO_";

	public Long insert(UserDO userDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", userDO);
		if (i > 0) {
			return Long.valueOf(userDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(UserDO userDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", userDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(UserDO userDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", userDO);
	}

	@Override
	public UserDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(UserDO userDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", userDO);
	}

	@Override
	public List<UserDO> selectDynamic(UserDO userDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", userDO);
	}

	@Override
	public List<UserDO> selectDynamicPageQuery(UserDO userDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", userDO);
	}

}
