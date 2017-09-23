package ng.bayue.snatch.persist.dao.item;

import java.util.List;

import ng.bayue.snatch.domain.item.ItemDescDO;
import ng.bayue.snatch.exception.DAOException;


 /**
 * 商品介绍-详情页图文混排 DAO
 * @author fengyts 2016-12-10 09:24:15
 */
public interface ItemDescDAO {

	/**
	 * 插入  商品介绍-详情页图文混排
	 * @param itemDescDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Long insert(ItemDescDO itemDescDO) throws DAOException;

	/**
	 * 根据ID更新 商品介绍-详情页图文混排全部属性
	 * @param itemDescDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Integer update(ItemDescDO itemDescDO) throws DAOException;

	/**
	 * 根据ID删除 商品介绍-详情页图文混排
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品介绍-详情页图文混排部分属性，包括全部
	 * @param itemDescDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Integer updateDynamic(ItemDescDO itemDescDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品介绍-详情页图文混排
	 * @param id
	 * @return ItemDescDO
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	ItemDescDO selectById(Long id) throws DAOException;

	/**
	 * 根据  商品介绍-详情页图文混排 动态返回记录数
	 * @param itemDescDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Long selectCountDynamic(ItemDescDO itemDescDO) throws DAOException;

	/**
	 * 根据  商品介绍-详情页图文混排 动态返回 商品介绍-详情页图文混排 列表
	 * @param itemDescDO
	 * @return List<ItemDescDO>
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws DAOException;

	/**
	 * 根据  商品介绍-详情页图文混排 动态返回 商品介绍-详情页图文混排 Limit 列表
	 * @param itemDescDO start,pageSize属性必须指定
	 * @return List<ItemDescDO>
	 * @throws DAOException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	List<ItemDescDO> selectDynamicPageQuery(ItemDescDO itemDescDO) throws DAOException;
	
	/**
	 * <pre>
	 * 根据detailId更新商品描述信息，需确保detailId的唯一性
	 * </pre>
	 *
	 * @param detailId
	 * @return
	 * @throws DAOException
	 */
	int updateByDetailId(ItemDescDO itemDescDO) throws DAOException;
}
