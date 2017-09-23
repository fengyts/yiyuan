package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 数据字典：信息 DAO
 * @author fengyts 2016-07-15 22:53:11
 */
public interface DictionaryDAO {

	/**
	 * 插入  数据字典：信息
	 * @param dictionaryDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Long insert(DictionaryDO dictionaryDO) throws DAOException;

	/**
	 * 根据ID更新 数据字典：信息全部属性
	 * @param dictionaryDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Integer update(DictionaryDO dictionaryDO) throws DAOException;

	/**
	 * 根据ID删除 数据字典：信息
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 数据字典：信息部分属性，包括全部
	 * @param dictionaryDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Integer updateDynamic(DictionaryDO dictionaryDO) throws DAOException;

	/**
	 * 根据ID查询 一个 数据字典：信息
	 * @param id
	 * @return DictionaryDO
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	DictionaryDO selectById(Long id) throws DAOException;

	/**
	 * 根据  数据字典：信息 动态返回记录数
	 * @param dictionaryDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	Long selectCountDynamic(DictionaryDO dictionaryDO) throws DAOException;

	/**
	 * 根据  数据字典：信息 动态返回 数据字典：信息 列表
	 * @param dictionaryDO
	 * @return List<DictionaryDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	List<DictionaryDO> selectDynamic(DictionaryDO dictionaryDO) throws DAOException;

	/**
	 * 根据  数据字典：信息 动态返回 数据字典：信息 Limit 列表
	 * @param dictionaryDO start,pageSize属性必须指定
	 * @return List<DictionaryDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-15 22:53:11
	 */
	List<DictionaryDO> selectDynamicPageQuery(DictionaryDO dictionaryDO) throws DAOException;
	
	/**
	 * <pre>
	 * 获取所有数据字典code类别
	 * </pre>
	 *
	 * @return
	 */
	List<DictionaryDO> listAllCode();
	
	List<DictionaryDO> selectByIds(List<Long> ids);
	
}
