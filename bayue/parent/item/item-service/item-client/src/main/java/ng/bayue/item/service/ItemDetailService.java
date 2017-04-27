package ng.bayue.item.service;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.exception.ServiceException;
 /**
 * 商品详情 Service
 * @author haisheng.long 2016-07-13 13:17:52
 */
public interface ItemDetailService {

	/**
	 * 插入  商品详情
	 * @param itemDetailDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long insert(ItemDetailDO itemDetailDO) throws ServiceException;

	/**
	 * 根据ItemDetailDO对象更新 商品详情
	 * @param itemDetailDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	int update(ItemDetailDO itemDetailDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 商品详情全部字段
//	 * @param itemDetailDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-13 13:17:52
//	 */
//	int updateById(ItemDetailDO itemDetailDO) throws ServiceException;

	/**
	 * 根据ID删除 商品详情
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 商品详情部分字段
//	 * @param itemDetailDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-13 13:17:52
//	 */
//	int updateDynamic(ItemDetailDO itemDetailDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品详情
	 * @param id
	 * @return ItemDetailDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	ItemDetailDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品详情 动态返回记录数
	 * @param itemDetailDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long selectCountDynamic(ItemDetailDO itemDetailDO) throws ServiceException;

	/**
	 * 动态返回 商品详情 列表
	 * @param itemDetailDO
	 * @return List<ItemDetailDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemDetailDO> selectDynamic(ItemDetailDO itemDetailDO) throws ServiceException;

	/**
	 * 动态返回 商品详情 分页列表
	 * @param itemDetailDO
	 * @return Page<ItemDetailDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Page<ItemDetailDO> queryPageListByItemDetailDO(ItemDetailDO itemDetailDO);

	/**
	 * 动态返回 商品详情 分页列表
	 * @param itemDetailDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<ItemDetailDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Page<ItemDetailDO> queryPageListByItemDetailDOAndStartPageSize(ItemDetailDO itemDetailDO,int startPage,int pageSize);

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
