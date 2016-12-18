package ng.bayue.item.persist.mybatis;
import java.util.List;

import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.persist.dao.ItemDescDAO;

import org.springframework.stereotype.Component;

@Component(value="itemDescDAO")
public class MybatisItemDescDAO extends MybatisBaseDAO implements ItemDescDAO {
	public Long insert(ItemDescDO itemDescDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_insert", itemDescDO);
		if (i > 0) {
			return Long.valueOf(itemDescDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemDescDO itemDescDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_updateById", itemDescDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(ItemDescDO itemDescDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_update_dynamic", itemDescDO);
	}

	@Override
	public ItemDescDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(ItemDescDO itemDescDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_select_dynamic_count", itemDescDO);
	}

	@Override
	public List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_select_dynamic", itemDescDO);
	}

	@Override
	public List<ItemDescDO> selectDynamicPageQuery(ItemDescDO itemDescDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemDescMapper.MybatisItemDescDAO_select_dynamic_page_query", itemDescDO);
	}

}