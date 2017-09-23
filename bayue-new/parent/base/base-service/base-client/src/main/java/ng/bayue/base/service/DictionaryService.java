package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 数据字典：信息 Service
 * @author fengyts 2016-07-15 22:53:11
 */
public interface DictionaryService {

	/**
	 * 插入  数据字典：信息
	 * @param dictionaryDO
	 * @return 主键
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Long insert(DictionaryDO dictionaryDO) throws ServiceException;

	/**
	 * 根据DictionaryDO对象更新 数据字典：信息
	 * @param dictionaryDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	int update(DictionaryDO dictionaryDO,boolean isAllField) throws ServiceException;

//	/**
//	 * 根据ID更新 数据字典：信息全部字段
//	 * @param dictionaryDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-15 22:53:11
//	 */
//	int updateById(DictionaryDO dictionaryDO) throws ServiceException;

	/**
	 * 根据ID删除 数据字典：信息
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	int deleteById(Long id) throws ServiceException;

//	/**
//	 * 动态更新 数据字典：信息部分字段
//	 * @param dictionaryDO
//	 * @return 更新行数
//	 * @throws ServiceException
//	 * @author fengyts 2016-07-15 22:53:11
//	 */
//	int updateDynamic(DictionaryDO dictionaryDO) throws ServiceException;

	/**
	 * 根据ID查询 一个 数据字典：信息
	 * @param id
	 * @return DictionaryDO
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	DictionaryDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  数据字典：信息 动态返回记录数
	 * @param dictionaryDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Long selectCountDynamic(DictionaryDO dictionaryDO) throws ServiceException;

	/**
	 * 动态返回 数据字典：信息 列表
	 * @param dictionaryDO
	 * @return List<DictionaryDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	List<DictionaryDO> selectDynamic(DictionaryDO dictionaryDO) throws ServiceException;

	/**
	 * 动态返回 数据字典：信息 分页列表
	 * @param dictionaryDO
	 * @return Page<DictionaryDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Page<DictionaryDO> queryPageListByDictionaryDO(DictionaryDO dictionaryDO);

	/**
	 * 动态返回 数据字典：信息 分页列表
	 * @param dictionaryDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<DictionaryDO>
	 * @throws ServiceException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Page<DictionaryDO> queryPageListByDictionaryDOAndStartPageSize(DictionaryDO dictionaryDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 获取所有的数据字典code类别
	 * </pre>
	 *
	 * @return
	 */
	List<DictionaryDO> listAllCode();
	
	List<DictionaryDO> selectByIds(List<Long> ids);

}
