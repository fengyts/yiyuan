package ng.bayue.item.service;

import java.util.List;

import ng.bayue.item.domain.ItemPicturesDO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 商品图片信息 Service
 * @author haisheng.long 2016-07-13 13:17:51
 */
public interface ItemPicturesService {

	/**
	 * 插入  商品图片信息
	 * @param itemPicturesDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Long insert(ItemPicturesDO itemPicturesDO) throws ServiceException;

	/**
	 * 根据ItemPicturesDO对象更新 商品图片信息
	 * @param itemPicturesDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	int update(ItemPicturesDO itemPicturesDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 商品图片信息全部字段
//	 * @param itemPicturesDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-13 13:17:51
//	 */
//	int updateById(ItemPicturesDO itemPicturesDO) throws ServiceException;

	/**
	 * 根据ID删除 商品图片信息
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 商品图片信息部分字段
//	 * @param itemPicturesDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author longhaisheng 2016-07-13 13:17:51
//	 */
//	int updateDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品图片信息
	 * @param id
	 * @return ItemPicturesDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	ItemPicturesDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品图片信息 动态返回记录数
	 * @param itemPicturesDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Long selectCountDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException;

	/**
	 * 动态返回 商品图片信息 列表
	 * @param itemPicturesDO
	 * @return List<ItemPicturesDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	List<ItemPicturesDO> selectDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException;

	/**
	 * 动态返回 商品图片信息 分页列表
	 * @param itemPicturesDO
	 * @return Page<ItemPicturesDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Page<ItemPicturesDO> queryPageListByItemPicturesDO(ItemPicturesDO itemPicturesDO);

	/**
	 * 动态返回 商品图片信息 分页列表
	 * @param itemPicturesDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<ItemPicturesDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:51
	 */
	Page<ItemPicturesDO> queryPageListByItemPicturesDOAndStartPageSize(ItemPicturesDO itemPicturesDO,int startPage,int pageSize);

}
