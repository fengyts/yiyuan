package ng.bayue.service.common;

import java.util.List;

import ng.bayue.exception.DAOException;

public interface GeneralDAO<T> {
	
	/**
	 * <pre>
	 * 插入 
	 * </pre>
	 *
	 * @param t
	 * @return
	 * @throws DAOException
	 */
	Long insert(T t) throws DAOException;
	
	/**
	 * <pre>
	 * 根据ID更新 ,全字段更新
	 * </pre>
	 *
	 * @param t
	 * @return  更新行数
	 * @throws DAOException
	 */
	Integer update(T t) throws DAOException;
	
	/**
	 * <pre>
	 * 根据ID删除
	 * </pre>
	 *
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 */
	Integer deleteById(Long id) throws DAOException;
	
	/**
	 * 动态更新 <T>部分属性,包括全部
	 * @param t
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2017-03-07 11:29:32
	 */
	Integer updateDynamic(T t) throws DAOException;

	/**
	 * 根据ID查询 一条 记录
	 * @param id
	 * @return T
	 * @throws DAOException
	 * @author fengyts 2017-03-07 11:29:32
	 */
	T selectById(Long id) throws DAOException;

	/**
	 * 根据  <T> 动态返回记录数
	 * @param t
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2017-03-07 11:29:32
	 */
	Long selectCountDynamic(T t) throws DAOException;

	/**
	 * 根据  <T> 动态返回 <T> 列表
	 * @param t
	 * @return List<T>
	 * @throws DAOException
	 * @author fengyts 2017-03-07 11:29:32
	 */
	List<T> selectDynamic(T t) throws DAOException;

	/**
	 * 根据  <T> 动态返回 <T> Limit 列表
	 * @param t start,pageSize属性必须指定
	 * @return List<T>
	 * @throws DAOException
	 * @author fengyts 2017-03-07 11:29:32
	 */
	List<T> selectDynamicPageQuery(T t) throws DAOException;

}
