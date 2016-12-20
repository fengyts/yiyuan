package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.util.Page;
 /**
 * 规格组 Service
 * @author fengyts 2016-07-26 10:08:29
 */
public interface SpecGroupService {

	/**
	 * 插入  规格组
	 * @param specGroupDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Long insert(SpecGroupDO specGroupDO) throws ServiceException;

	/**
	 * 根据SpecGroupDO对象更新 规格组
	 * @param specGroupDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	int update(SpecGroupDO specGroupDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 规格组全部字段
//	 * @param specGroupDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:29
//	 */
//	int updateById(SpecGroupDO specGroupDO) throws ServiceException;

	/**
	 * 根据ID删除 规格组
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 规格组部分字段
//	 * @param specGroupDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:29
//	 */
//	int updateDynamic(SpecGroupDO specGroupDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 规格组
	 * @param id
	 * @return SpecGroupDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	SpecGroupDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  规格组 动态返回记录数
	 * @param specGroupDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Long selectCountDynamic(SpecGroupDO specGroupDO) throws ServiceException;

	/**
	 * 动态返回 规格组 列表
	 * @param specGroupDO
	 * @return List<SpecGroupDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	List<SpecGroupDO> selectDynamic(SpecGroupDO specGroupDO) throws ServiceException;

	/**
	 * 动态返回 规格组 分页列表
	 * @param specGroupDO
	 * @return Page<SpecGroupDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Page<SpecGroupDO> queryPageListBySpecGroupDO(SpecGroupDO specGroupDO);

	/**
	 * 动态返回 规格组 分页列表
	 * @param specGroupDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SpecGroupDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Page<SpecGroupDO> queryPageListBySpecGroupDOAndStartPageSize(SpecGroupDO specGroupDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 插入规格组信息，同时插入规格组个规格关联信息
	 * </pre>
	 *
	 * @param specGroupDO
	 * @param specIds
	 * @return
	 * @throws ServiceException
	 */
	int insertSpecGroupAndLink(SpecGroupDO specGroupDO, List<Long> specIds) throws ServiceException;
	
	List<SpecGroupDO> selectByIds(List<Long> groupIds);

}
