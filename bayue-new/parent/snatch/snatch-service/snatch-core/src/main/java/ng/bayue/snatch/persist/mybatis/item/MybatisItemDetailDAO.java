package ng.bayue.snatch.persist.mybatis.item;
import java.util.List;

import ng.bayue.snatch.domain.item.ItemDetailDO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.persist.dao.item.ItemDetailDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="itemDetailDAO")
public class MybatisItemDetailDAO extends MybatisBaseDAO implements ItemDetailDAO {
	public Long insert(ItemDetailDO itemDetailDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_insert", itemDetailDO);
		if (i > 0) {
			return Long.valueOf(itemDetailDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemDetailDO itemDetailDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_updateById", itemDetailDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(ItemDetailDO itemDetailDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_update_dynamic", itemDetailDO);
	}

	@Override
	public ItemDetailDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(ItemDetailDO itemDetailDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_select_dynamic_count", itemDetailDO);
	}

	@Override
	public List<ItemDetailDO> selectDynamic(ItemDetailDO itemDetailDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_select_dynamic", itemDetailDO);
	}

	@Override
	public List<ItemDetailDO> selectDynamicPageQuery(ItemDetailDO itemDetailDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_select_dynamic_page_query", itemDetailDO);
	}

	@Override
	public int updateBatch(List<ItemDetailDO> list) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_updateBatch", list);
	}

	@Override
	public List<ItemDetailDO> selectByIds(List<Long> ids) {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemDetailMapper.MybatisItemDetailDAO_select_byIds", ids);
	}

}
