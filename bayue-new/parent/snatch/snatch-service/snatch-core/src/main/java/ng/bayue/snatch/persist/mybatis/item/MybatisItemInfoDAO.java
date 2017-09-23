package ng.bayue.snatch.persist.mybatis.item;
import java.util.List;

import ng.bayue.snatch.domain.item.ItemInfoDO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.persist.dao.item.ItemInfoDAO;
import ng.bayue.snatch.persist.mybatis.MybatisBaseDAO;

import org.springframework.stereotype.Component;

@Component(value="itemInfoDAO")
public class MybatisItemInfoDAO extends MybatisBaseDAO implements ItemInfoDAO {
	public Long insert(ItemInfoDO itemInfoDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_insert", itemInfoDO);
		if (i > 0) {
			return Long.valueOf(itemInfoDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemInfoDO itemInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_updateById", itemInfoDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(ItemInfoDO itemInfoDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_update_dynamic", itemInfoDO);
	}

	@Override
	public ItemInfoDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(ItemInfoDO itemInfoDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_select_dynamic_count", itemInfoDO);
	}

	@Override
	public List<ItemInfoDO> selectDynamic(ItemInfoDO itemInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_select_dynamic", itemInfoDO);
	}

	@Override
	public List<ItemInfoDO> selectDynamicPageQuery(ItemInfoDO itemInfoDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_select_dynamic_page_query", itemInfoDO);
	}
	
	@Override
	public List<ItemInfoDO> selectBySPU (String spu) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemInfoMapper.MybatisItemInfoDAO_select_by_spu", spu);
	}

}
