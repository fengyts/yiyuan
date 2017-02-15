package ng.bayue.item.service;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.exception.ServiceException;
 /**
 * 商品规格信息 Service
 * @author fengyts 2016-07-26 09:58:30
 */
public interface DetailSpecService {

	/**
	 * 插入  商品规格信息
	 * @param detailSpecDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Long insert(DetailSpecDO detailSpecDO) throws ServiceException;

	/**
	 * 根据DetailSpecDO对象更新 商品规格信息
	 * @param detailSpecDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	int update(DetailSpecDO detailSpecDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 商品规格信息全部字段
//	 * @param detailSpecDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 09:58:30
//	 */
//	int updateById(DetailSpecDO detailSpecDO) throws ServiceException;

	/**
	 * 根据ID删除 商品规格信息
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 商品规格信息部分字段
//	 * @param detailSpecDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 09:58:30
//	 */
//	int updateDynamic(DetailSpecDO detailSpecDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品规格信息
	 * @param id
	 * @return DetailSpecDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	DetailSpecDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品规格信息 动态返回记录数
	 * @param detailSpecDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Long selectCountDynamic(DetailSpecDO detailSpecDO) throws ServiceException;

	/**
	 * 动态返回 商品规格信息 列表
	 * @param detailSpecDO
	 * @return List<DetailSpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	List<DetailSpecDO> selectDynamic(DetailSpecDO detailSpecDO) throws ServiceException;

	/**
	 * 动态返回 商品规格信息 分页列表
	 * @param detailSpecDO
	 * @return Page<DetailSpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Page<DetailSpecDO> queryPageListByDetailSpecDO(DetailSpecDO detailSpecDO);

	/**
	 * 动态返回 商品规格信息 分页列表
	 * @param detailSpecDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<DetailSpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Page<DetailSpecDO> queryPageListByDetailSpecDOAndStartPageSize(DetailSpecDO detailSpecDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 批量插入商品详情规格信息
	 * </pre>
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	int insertBatch(List<DetailSpecDO> list) throws ServiceException;
	
	/**
	 * <pre>
	 * 根据商品detailId删除关联的规格组信息
	 * </pre>
	 *
	 * @param detailId
	 * @return
	 * @throws ServiceException
	 */
	int deleteByDetailId(Long detailId) throws ServiceException;

}
