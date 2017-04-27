package ng.bayue.item.persist.dao;

import java.util.List;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;

/**
 * 商品详情 DAO
 * 
 * @author haisheng.Long 2016-07-13 13:17:52
 */
public interface ItemDetailDAO {

	/**
	 * 插入 商品详情
	 * 
	 * @param itemDetailDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long insert(ItemDetailDO itemDetailDO) throws DAOException;

	/**
	 * 根据ID更新 商品详情全部属性
	 * 
	 * @param itemDetailDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer update(ItemDetailDO itemDetailDO) throws DAOException;

	/**
	 * 根据ID删除 商品详情
	 * 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品详情部分属性，包括全部
	 * 
	 * @param itemDetailDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Integer updateDynamic(ItemDetailDO itemDetailDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品详情
	 * 
	 * @param id
	 * @return ItemDetailDO
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	ItemDetailDO selectById(Long id) throws DAOException;

	/**
	 * 根据 商品详情 动态返回记录数
	 * 
	 * @param itemDetailDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long selectCountDynamic(ItemDetailDO itemDetailDO) throws DAOException;

	/**
	 * 根据 商品详情 动态返回 商品详情 列表
	 * 
	 * @param itemDetailDO
	 * @return List<ItemDetailDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemDetailDO> selectDynamic(ItemDetailDO itemDetailDO) throws DAOException;

	/**
	 * 根据 商品详情 动态返回 商品详情 Limit 列表
	 * 
	 * @param itemDetailDO
	 *            start,pageSize属性必须指定
	 * @return List<ItemDetailDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemDetailDO> selectDynamicPageQuery(ItemDetailDO itemDetailDO) throws DAOException;
	
	/**
	 * <pre>
	 * 批量更新商品(批量上架、作废)
	 * </pre>
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	int updateBatch(List<ItemDetailDO> list) throws ServiceException;
	
	List<ItemDetailDO> selectByIds(List<Long> ids);
	
}
