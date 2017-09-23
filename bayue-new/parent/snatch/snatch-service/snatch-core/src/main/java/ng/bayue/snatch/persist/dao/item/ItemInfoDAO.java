package ng.bayue.snatch.persist.dao.item;

import java.util.List;

import ng.bayue.snatch.domain.item.ItemInfoDO;
import ng.bayue.snatch.exception.DAOException;

/**
 * 商品基础信息 DAO
 * 
 * @author haisheng.Long 2016-07-13 13:17:52
 */
public interface ItemInfoDAO {

	/**
	 * 插入 商品基础信息
	 * 
	 * @param itemInfoDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long insert(ItemInfoDO itemInfoDO) throws DAOException;

	/**
	 * 根据ID更新 商品基础信息全部属性
	 * 
	 * @param itemInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer update(ItemInfoDO itemInfoDO) throws DAOException;

	/**
	 * 根据ID删除 商品基础信息
	 * 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品基础信息部分属性，包括全部
	 * 
	 * @param itemInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer updateDynamic(ItemInfoDO itemInfoDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品基础信息
	 * 
	 * @param id
	 * @return ItemInfoDO
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	ItemInfoDO selectById(Long id) throws DAOException;

	/**
	 * 根据 商品基础信息 动态返回记录数
	 * 
	 * @param itemInfoDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long selectCountDynamic(ItemInfoDO itemInfoDO) throws DAOException;

	/**
	 * 根据 商品基础信息 动态返回 商品基础信息 列表
	 * 
	 * @param itemInfoDO
	 * @return List<ItemInfoDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemInfoDO> selectDynamic(ItemInfoDO itemInfoDO) throws DAOException;

	/**
	 * 根据 商品基础信息 动态返回 商品基础信息 Limit 列表
	 * 
	 * @param itemInfoDO
	 *            start,pageSize属性必须指定
	 * @return List<ItemInfoDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemInfoDO> selectDynamicPageQuery(ItemInfoDO itemInfoDO) throws DAOException;
	
	List<ItemInfoDO> selectBySPU(String spu) throws DAOException;
}
