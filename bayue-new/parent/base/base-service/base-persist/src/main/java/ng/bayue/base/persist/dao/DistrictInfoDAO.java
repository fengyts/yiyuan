package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.DistrictInfoDO;
import ng.bayue.base.exception.DAOException;

/**
 * 地区信息 DAO
 * 
 * @author fengyts 2016-07-12 20:29:58
 */
public interface DistrictInfoDAO {

	/**
	 * 插入 地区信息
	 * 
	 * @param districtInfoDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Long insert(DistrictInfoDO districtInfoDO) throws DAOException;

	/**
	 * 根据ID更新 地区信息全部属性
	 * 
	 * @param districtInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Integer update(DistrictInfoDO districtInfoDO) throws DAOException;

	/**
	 * 根据ID删除 地区信息
	 * 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 地区信息部分属性，包括全部
	 * 
	 * @param districtInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Integer updateDynamic(DistrictInfoDO districtInfoDO) throws DAOException;

	/**
	 * 根据ID查询 一个 地区信息
	 * 
	 * @param id
	 * @return DistrictInfoDO
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	DistrictInfoDO selectById(Long id) throws DAOException;

	/**
	 * 根据 地区信息 动态返回记录数
	 * 
	 * @param districtInfoDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	Long selectCountDynamic(DistrictInfoDO districtInfoDO) throws DAOException;

	/**
	 * 根据 地区信息 动态返回 地区信息 列表
	 * 
	 * @param districtInfoDO
	 * @return List<DistrictInfoDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	List<DistrictInfoDO> selectDynamic(DistrictInfoDO districtInfoDO) throws DAOException;

	/**
	 * 根据 地区信息 动态返回 地区信息 Limit 列表
	 * 
	 * @param districtInfoDO
	 *            start,pageSize属性必须指定
	 * @return List<DistrictInfoDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-12 20:29:58
	 */
	List<DistrictInfoDO> selectDynamicPageQuery(DistrictInfoDO districtInfoDO) throws DAOException;
}
