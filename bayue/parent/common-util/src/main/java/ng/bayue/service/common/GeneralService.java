package ng.bayue.service.common;

import ng.bayue.exception.ServiceException;
import ng.bayue.util.Page;

public interface GeneralService<T> {

	/**
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @return 主键
	 * @throws ServiceException
	 */
	Long insert(T t) throws ServiceException;

	/**
	 * <pre>
	 * 动态更新
	 * </pre>
	 *
	 * @param t
	 * @param isAllField
	 *            是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 */
	int update(T t, boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 规格
	 * 
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 对象
	 * 
	 * @param id
	 * @return T
	 * @throws ServiceException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	T selectById(Long id) throws ServiceException;

	T selectDynamic(T t) throws ServiceException;

	/**
	 * <pre>
	 * 根据条件动态查询记录数
	 * </pre>
	 *
	 * @param t
	 * @return
	 * @throws ServiceException
	 */
	Long selectCountDynamic(T t) throws ServiceException;

	Page<T> queryPageListDynamic(T t) throws ServiceException;

	Page<T> queryPageListDynamicAndStartPageSize(T t, Integer pageNo, Integer pageSize) throws ServiceException;

}
