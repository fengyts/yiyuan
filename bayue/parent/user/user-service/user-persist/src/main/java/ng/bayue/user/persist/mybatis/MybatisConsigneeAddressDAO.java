package ng.bayue.user.persist.mybatis;

import java.util.List;

import org.springframework.stereotype.Component;

import ng.bayue.exception.DAOException;
import ng.bayue.user.domain.ConsigneeAddressDO;
import ng.bayue.user.persist.MybatisBaseDAO;
import ng.bayue.user.persist.dao.ConsigneeAddressDAO;

@Component(value = "consigneeAddressDAO")
public class MybatisConsigneeAddressDAO extends MybatisBaseDAO implements ConsigneeAddressDAO {

	private static final String NAMESPACE = "ng.bayue.user.domain.ConsigneeAddressMapper.MybatisConsigneeAddressDAO_";

	public Long insert(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		int i = getSqlSession().insert(NAMESPACE + "insert", consigneeAddressDO);
		if (i > 0) {
			return Long.valueOf(consigneeAddressDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "updateById", consigneeAddressDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete(NAMESPACE + "deleteById", id);
	}

	@Override
	public Integer updateDynamic(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		return getSqlSession().update(NAMESPACE + "update_dynamic", consigneeAddressDO);
	}

	@Override
	public ConsigneeAddressDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "selectById", id);
	}

	@Override
	public Long selectCountDynamic(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		return getSqlSession().selectOne(NAMESPACE + "select_dynamic_count", consigneeAddressDO);
	}

	@Override
	public List<ConsigneeAddressDO> selectDynamic(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic", consigneeAddressDO);
	}

	@Override
	public List<ConsigneeAddressDO> selectDynamicPageQuery(ConsigneeAddressDO consigneeAddressDO) throws DAOException {
		return getSqlSession().selectList(NAMESPACE + "select_dynamic_page_query", consigneeAddressDO);
	}

}
