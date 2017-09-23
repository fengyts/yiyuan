package ng.bayue.service.common;

import java.util.List;

import ng.bayue.common.Page;

public interface  GeneralService<T, U> {

	/**
	 * <pre>
	 * 
	 * </pre>
	 *
	 * @param t
	 * @return 主键
	 */
	Long insert(T t);

	/**
	 * <pre>
	 * 动态更新
	 * </pre>
	 *
	 * @param t
	 * @param isAllField
	 *            是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 */
	int update(T t, boolean isAllField);

	/**
	 * 根据ID删除 规格
	 * 
	 * @param id
	 * @return 物理删除行
	 * @author fengyts 2016-07-26 10:08:30
	 */
	int deleteById(Long id);

	/**
	 * 根据ID查询 一个 对象
	 * 
	 * @param id
	 * @return T
	 * @author fengyts 2016-07-26 10:08:30
	 */
	T selectById(Long id);

	List<T> selectDynamic(T t);

	/**
	 * <pre>
	 * 根据条件动态查询记录数
	 * </pre>
	 *
	 * @param t
	 * @return
	 */
	Long selectCountDynamic(T t);

	Page<U> queryPageListDynamic(T t);

	Page<U> queryPageListDynamicAndStartPageSize(T t, Integer pageNo, Integer pageSize);

}
