package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 规格 Service
 * @author fengyts 2016-07-26 10:08:30
 */
public interface SpecService {

	/**
	 * 插入  规格
	 * @param specDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long insert(SpecDO specDO) throws ServiceException;

	/**
	 * 根据SpecDO对象更新 规格
	 * @param specDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int update(SpecDO specDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 规格全部字段
//	 * @param specDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:30
//	 */
//	int updateById(SpecDO specDO) throws ServiceException;

	/**
	 * 根据ID删除 规格
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 规格部分字段
//	 * @param specDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:30
//	 */
//	int updateDynamic(SpecDO specDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 规格
	 * @param id
	 * @return SpecDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	SpecDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  规格 动态返回记录数
	 * @param specDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long selectCountDynamic(SpecDO specDO) throws ServiceException;

	/**
	 * 动态返回 规格 列表
	 * @param specDO
	 * @return List<SpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecDO> selectDynamic(SpecDO specDO) throws ServiceException;

	/**
	 * 动态返回 规格 分页列表
	 * @param specDO
	 * @return Page<SpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Page<SpecDO> queryPageListBySpecDO(SpecDO specDO);

	/**
	 * 动态返回 规格 分页列表
	 * @param specDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SpecDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Page<SpecDO> queryPageListBySpecDOAndStartPageSize(SpecDO specDO,int startPage,int pageSize);
	
	List<SpecDO> selectByIds(List<Long> ids);

}
