package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.DistrictInfoDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 地区信息 Service
 * @author fengyts 2016-07-12 20:29:58
 */
public interface DistrictInfoService {

	/**
	 * 插入  地区信息
	 * @param districtInfoDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Long insert(DistrictInfoDO districtInfoDO) throws ServiceException;

	/**
	 * 根据DistrictInfoDO对象更新 地区信息
	 * @param districtInfoDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	int update(DistrictInfoDO districtInfoDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 地区信息全部字段
//	 * @param districtInfoDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-12 20:29:58
//	 */
//	int updateById(DistrictInfoDO districtInfoDO) throws ServiceException;

	/**
	 * 根据ID删除 地区信息
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 地区信息部分字段
//	 * @param districtInfoDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-12 20:29:58
//	 */
//	int updateDynamic(DistrictInfoDO districtInfoDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 地区信息
	 * @param id
	 * @return DistrictInfoDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	DistrictInfoDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  地区信息 动态返回记录数
	 * @param districtInfoDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Long selectCountDynamic(DistrictInfoDO districtInfoDO) throws ServiceException;

	/**
	 * 动态返回 地区信息 列表
	 * @param districtInfoDO
	 * @return List<DistrictInfoDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	List<DistrictInfoDO> selectDynamic(DistrictInfoDO districtInfoDO) throws ServiceException;

	/**
	 * 动态返回 地区信息 分页列表
	 * @param districtInfoDO
	 * @return Page<DistrictInfoDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Page<DistrictInfoDO> queryPageListByDistrictInfoDO(DistrictInfoDO districtInfoDO);

	/**
	 * 动态返回 地区信息 分页列表
	 * @param districtInfoDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<DistrictInfoDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Page<DistrictInfoDO> queryPageListByDistrictInfoDOAndStartPageSize(DistrictInfoDO districtInfoDO,int startPage,int pageSize);

}
