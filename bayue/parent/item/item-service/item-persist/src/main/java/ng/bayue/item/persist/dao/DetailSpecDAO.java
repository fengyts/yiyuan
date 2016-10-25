package ng.bayue.item.persist.dao;

import java.util.List;

import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.exception.DAOException;

/**
 * 商品规格信息 DAO
 * 
 * @author fengyts 2016-07-26 09:58:30
 */
public interface DetailSpecDAO {

	/**
	 * 插入 商品规格信息
	 * 
	 * @param detailSpecDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Long insert(DetailSpecDO detailSpecDO) throws DAOException;

	/**
	 * 根据ID更新 商品规格信息全部属性
	 * 
	 * @param detailSpecDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Integer update(DetailSpecDO detailSpecDO) throws DAOException;

	/**
	 * 根据ID删除 商品规格信息
	 * 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 商品规格信息部分属性，包括全部
	 * 
	 * @param detailSpecDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Integer updateDynamic(DetailSpecDO detailSpecDO) throws DAOException;

	/**
	 * 根据ID查询 一个 商品规格信息
	 * 
	 * @param id
	 * @return DetailSpecDO
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	DetailSpecDO selectById(Long id) throws DAOException;

	/**
	 * 根据 商品规格信息 动态返回记录数
	 * 
	 * @param detailSpecDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	Long selectCountDynamic(DetailSpecDO detailSpecDO) throws DAOException;

	/**
	 * 根据 商品规格信息 动态返回 商品规格信息 列表
	 * 
	 * @param detailSpecDO
	 * @return List<DetailSpecDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	List<DetailSpecDO> selectDynamic(DetailSpecDO detailSpecDO) throws DAOException;

	/**
	 * 根据 商品规格信息 动态返回 商品规格信息 Limit 列表
	 * 
	 * @param detailSpecDO
	 *            start,pageSize属性必须指定
	 * @return List<DetailSpecDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 09:58:30
	 */
	List<DetailSpecDO> selectDynamicPageQuery(DetailSpecDO detailSpecDO) throws DAOException;
}
