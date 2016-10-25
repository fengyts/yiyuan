package ng.bayue.base.persist.dao;

import java.util.List;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 商品类别 DAO
 * @author haisheng.Long 2016-07-04 10:53:55
 */
public interface CategoryDAO {

	/**
	 * 插入  商品类别
	 * @param categoryDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Long insert(CategoryDO categoryDO) throws DAOException;

	/**
	 * 根据ID更新 商品类别全部属性
	 * @param categoryDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Integer update(CategoryDO categoryDO) throws DAOException;

	/**
	 * 根据ID删除 商品类别
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品类别部分属性，包括全部
	 * @param categoryDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Integer updateDynamic(CategoryDO categoryDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品类别
	 * @param id
	 * @return CategoryDO
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	CategoryDO selectById(Long id) throws DAOException;

	/**
	 * 根据  商品类别 动态返回记录数
	 * @param categoryDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Long selectCountDynamic(CategoryDO categoryDO) throws DAOException;

	/**
	 * 根据  商品类别 动态返回 商品类别 列表
	 * @param categoryDO
	 * @return List<CategoryDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	List<CategoryDO> selectDynamic(CategoryDO categoryDO) throws DAOException;

	/**
	 * 根据  商品类别 动态返回 商品类别 Limit 列表
	 * @param categoryDO start,pageSize属性必须指定
	 * @return List<CategoryDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	List<CategoryDO> selectDynamicPageQuery(CategoryDO categoryDO) throws DAOException;
	
	/**
	 * <pre>
	 * 获取某类别子类code的最大值
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 * @throws DAOException
	 */
	String selectMaxCodeDynamic(CategoryDO categoryDO) throws DAOException;
	
	/**
	 * <pre>
	 * 批量更新
	 * </pre>
	 *
	 * @param categoryDO
	 * @throws DAOException
	 */
	void updateBatch(List<CategoryDO> list) throws DAOException;
	
	List<CategoryDO> selectByIds(List<Long> ids) throws DAOException;
	
	
}
