package ng.bayue.item.service;

import java.util.List;

import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 商品介绍-详情页图文混排 Service
 * @author fengyts 2016-12-10 09:24:15
 */
public interface ItemDescService {

	/**
	 * 插入  商品介绍-详情页图文混排
	 * @param itemDescDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Long insert(ItemDescDO itemDescDO) throws ServiceException;

	/**
	 * 根据ItemDescDO对象更新 商品介绍-详情页图文混排
	 * @param itemDescDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	int update(ItemDescDO itemDescDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 商品介绍-详情页图文混排全部字段
//	 * @param itemDescDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-12-10 09:24:15
//	 */
//	int updateById(ItemDescDO itemDescDO) throws ServiceException;

	/**
	 * 根据ID删除 商品介绍-详情页图文混排
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 商品介绍-详情页图文混排部分字段
//	 * @param itemDescDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-12-10 09:24:15
//	 */
//	int updateDynamic(ItemDescDO itemDescDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品介绍-详情页图文混排
	 * @param id
	 * @return ItemDescDO
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	ItemDescDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品介绍-详情页图文混排 动态返回记录数
	 * @param itemDescDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Long selectCountDynamic(ItemDescDO itemDescDO) throws ServiceException;

	/**
	 * 动态返回 商品介绍-详情页图文混排 列表
	 * @param itemDescDO
	 * @return List<ItemDescDO>
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws ServiceException;

	/**
	 * 动态返回 商品介绍-详情页图文混排 分页列表
	 * @param itemDescDO
	 * @return Page<ItemDescDO>
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Page<ItemDescDO> queryPageListByItemDescDO(ItemDescDO itemDescDO);

	/**
	 * 动态返回 商品介绍-详情页图文混排 分页列表
	 * @param itemDescDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<ItemDescDO>
	 * @throws ServiceException
	 * @author fengyts 2016-12-10 09:24:15
	 */
	Page<ItemDescDO> queryPageListByItemDescDOAndStartPageSize(ItemDescDO itemDescDO,int startPage,int pageSize);

}
