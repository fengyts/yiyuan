package ng.bayue.item.persist.mybatis;
import java.util.List;

import ng.bayue.item.domain.ItemPicturesDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.persist.dao.ItemPicturesDAO;

import org.springframework.stereotype.Component;

@Component(value="itemPicturesDAO")
public class MybatisItemPicturesDAO extends MybatisBaseDAO implements ItemPicturesDAO {
	public Long insert(ItemPicturesDO itemPicturesDO) throws DAOException {
		int i = getSqlSession().insert("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_insert", itemPicturesDO);
		if (i > 0) {
			return Long.valueOf(itemPicturesDO.getId());
		}
		return 0L;
	}

	@Override
	public Integer update(ItemPicturesDO itemPicturesDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_updateById", itemPicturesDO);
	}

	@Override
	public Integer deleteById(Long id) throws DAOException {
		return getSqlSession().delete("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_deleteById", id);
	}

	@Override
	public Integer updateDynamic(ItemPicturesDO itemPicturesDO) throws DAOException {
		return getSqlSession().update("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_update_dynamic", itemPicturesDO);
	}

	@Override
	public ItemPicturesDO selectById(Long id) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_selectById", id);
	}

	@Override
	public Long selectCountDynamic(ItemPicturesDO itemPicturesDO) throws DAOException {
		return getSqlSession().selectOne("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_select_dynamic_count", itemPicturesDO);
	}

	@Override
	public List<ItemPicturesDO> selectDynamic(ItemPicturesDO itemPicturesDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_select_dynamic", itemPicturesDO);
	}

	@Override
	public List<ItemPicturesDO> selectDynamicPageQuery(ItemPicturesDO itemPicturesDO) throws DAOException {
		return getSqlSession().selectList("ng.bayue.item.domain.ItemPicturesMapper.MybatisItemPicturesDAO_select_dynamic_page_query", itemPicturesDO);
	}

}
