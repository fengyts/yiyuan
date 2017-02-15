package ng.bayue.item.service;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemInfoDTO;
import ng.bayue.item.exception.ServiceException;
 /**
 * 商品基础信息 Service
 * @author haisheng.long 2016-07-13 13:17:52
 */
public interface ItemInfoService {

	/**
	 * 插入  商品基础信息
	 * @param itemInfoDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long insert(ItemInfoDO itemInfoDO) throws ServiceException;

	/**
	 * 根据ItemInfoDO对象更新 商品基础信息
	 * @param itemInfoDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	int update(ItemInfoDO itemInfoDO,boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 商品基础信息
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品基础信息
	 * @param id
	 * @return ItemInfoDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	ItemInfoDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品基础信息 动态返回记录数
	 * @param itemInfoDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Long selectCountDynamic(ItemInfoDO itemInfoDO) throws ServiceException;

	/**
	 * 动态返回 商品基础信息 列表
	 * @param itemInfoDO
	 * @return List<ItemInfoDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	List<ItemInfoDO> selectDynamic(ItemInfoDO itemInfoDO) throws ServiceException;

	/**
	 * 动态返回 商品基础信息 分页列表
	 * @param itemInfoDO
	 * @return Page<ItemInfoDTO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Page<ItemInfoDTO> queryPageListByItemInfoDO(ItemInfoDO itemInfoDO);

	/**
	 * 动态返回 商品基础信息 分页列表
	 * @param itemInfoDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<ItemInfoDTO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-13 13:17:52
	 */
	Page<ItemInfoDTO> queryPageListByItemInfoDOAndStartPageSize(ItemInfoDO itemInfoDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 根据spu查询iteminfo信息
	 * </pre>
	 *
	 * @param spu
	 * @return
	 */
	ItemInfoDO selectBySPU(String spu) throws ServiceException;
	
	/**
	 * <pre>
	 * 根据spu列表查询
	 * </pre>
	 *
	 * @param spus
	 * @return
	 * @throws ServiceException
	 */
	List<ItemInfoDO> selectBySpus(List<String> spus) throws ServiceException;
	
	/**
	 * <pre>
	 * 更新商品spu信息
	 * </pre>
	 *
	 * @param infoDO
	 * @param isRebuildSpu 修改类别时需要根据小类code重新生成spu编码；true-重新生成spu编码，false-不重新生成
	 * @return
	 * @throws ServiceException
	 */
	int updateRebuildSpu(ItemInfoDO infoDO,boolean isRebuildSpu) throws ServiceException;
	

}
