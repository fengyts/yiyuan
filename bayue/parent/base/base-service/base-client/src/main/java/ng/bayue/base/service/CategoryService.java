package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.util.Page;


 /**
 * 商品类别 Service
 * @author haisheng.long 2016-07-04 10:53:55
 */
public interface CategoryService {

	/**
	 * 插入  商品类别
	 * @param categoryDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Long insert(CategoryDO categoryDO) throws ServiceException;

	/**
	 * 根据CategoryDO对象更新 商品类别
	 * @param categoryDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	int update(CategoryDO categoryDO,boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 商品类别
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 商品类别
	 * @param id
	 * @return CategoryDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	CategoryDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  商品类别 动态返回记录数
	 * @param categoryDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Long selectCountDynamic(CategoryDO categoryDO) throws ServiceException;

	/**
	 * 动态返回 商品类别 列表
	 * @param categoryDO
	 * @return List<CategoryDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	List<CategoryDO> selectDynamic(CategoryDO categoryDO) throws ServiceException;

	/**
	 * 动态返回 商品类别 分页列表
	 * @param categoryDO
	 * @return Page<CategoryDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Page<CategoryDO> queryPageListByCategoryDO(CategoryDO categoryDO);

	/**
	 * 动态返回 商品类别 分页列表
	 * @param categoryDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<CategoryDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-07-04 10:53:55
	 */
	Page<CategoryDO> queryPageListByCategoryDOAndStartPageSize(CategoryDO categoryDO,int startPage,int pageSize);
	
	/**
	 * <pre>
	 * 获取某一类别子类code的最大值
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 */
	String selectMaxCodeDynamic(CategoryDO categoryDO);
	
	/**
	 * <pre>
	 * 批量更新
	 * </pre>
	 *
	 * @param list
	 * @throws ServiceException
	 */
	void updateBatch(List<CategoryDO> list) throws ServiceException;
	
	List<CategoryDO> selectByIds(List<Long> ids);
	
	/**
	 * <pre>
	 * 根据传递过来的子类id获取他的祖先以及自己,排序依次为 大类,小类
	 * </pre>
	 *
	 * @param cateId 必须是子类的id,否则返回为空
	 * @return
	 */
	List<CategoryDO> selectAncestors(Long cateId);
	
}
