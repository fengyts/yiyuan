package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 规格与规格组关系 Service
 * @author fengyts 2016-07-26 10:08:30
 */
public interface SpecGroupLinkService {

	/**
	 * 插入  规格与规格组关系
	 * @param specGroupLinkDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long insert(SpecGroupLinkDO specGroupLinkDO) throws ServiceException;

	/**
	 * 根据SpecGroupLinkDO对象更新 规格与规格组关系
	 * @param specGroupLinkDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int update(SpecGroupLinkDO specGroupLinkDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 规格与规格组关系全部字段
//	 * @param specGroupLinkDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:30
//	 */
//	int updateById(SpecGroupLinkDO specGroupLinkDO) throws ServiceException;

	/**
	 * 根据ID删除 规格与规格组关系
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 规格与规格组关系部分字段
//	 * @param specGroupLinkDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-26 10:08:30
//	 */
//	int updateDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 规格与规格组关系
	 * @param id
	 * @return SpecGroupLinkDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	SpecGroupLinkDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  规格与规格组关系 动态返回记录数
	 * @param specGroupLinkDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long selectCountDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException;

	/**
	 * 动态返回 规格与规格组关系 列表
	 * @param specGroupLinkDO
	 * @return List<SpecGroupLinkDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecGroupLinkDO> selectDynamic(SpecGroupLinkDO specGroupLinkDO) throws ServiceException;

	/**
	 * 动态返回 规格与规格组关系 分页列表
	 * @param specGroupLinkDO
	 * @return Page<SpecGroupLinkDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Page<SpecGroupLinkDO> queryPageListBySpecGroupLinkDO(SpecGroupLinkDO specGroupLinkDO);

	/**
	 * 动态返回 规格与规格组关系 分页列表
	 * @param specGroupLinkDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<SpecGroupLinkDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Page<SpecGroupLinkDO> queryPageListBySpecGroupLinkDOAndStartPageSize(SpecGroupLinkDO specGroupLinkDO,int startPage,int pageSize);
	
	void insertBatch(List<SpecGroupLinkDO> list);
	
	/**
	 * <pre>
	 * 根据groupId和specId批量更新规格关联信息
	 * </pre>
	 *
	 * @param list
	 * @return
	 * @throws ServiceException
	 */
	int updateBatch(List<SpecGroupLinkDO> list) throws ServiceException;

}
