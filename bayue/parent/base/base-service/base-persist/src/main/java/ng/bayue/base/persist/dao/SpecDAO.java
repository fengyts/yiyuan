package ng.bayue.base.persist.dao;

import java.util.List;

import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 规格 DAO
 * @author fengyts 2016-07-26 10:08:30
 */
public interface SpecDAO {

	/**
	 * 插入  规格
	 * @param specDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long insert(SpecDO specDO) throws DAOException;

	/**
	 * 根据ID更新 规格全部属性
	 * @param specDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer update(SpecDO specDO) throws DAOException;

	/**
	 * 根据ID删除 规格
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 规格部分属性，包括全部
	 * @param specDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer updateDynamic(SpecDO specDO) throws DAOException;

	/**
	 * 根据ID查询 一个 规格
	 * @param id
	 * @return SpecDO
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	SpecDO selectById(Long id) throws DAOException;

	/**
	 * 根据  规格 动态返回记录数
	 * @param specDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long selectCountDynamic(SpecDO specDO) throws DAOException;

	/**
	 * 根据  规格 动态返回 规格 列表
	 * @param specDO
	 * @return List<SpecDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecDO> selectDynamic(SpecDO specDO) throws DAOException;

	/**
	 * 根据  规格 动态返回 规格 Limit 列表
	 * @param specDO start,pageSize属性必须指定
	 * @return List<SpecDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecDO> selectDynamicPageQuery(SpecDO specDO) throws DAOException;
	
	List<SpecDO> selectByIds(List<Long> ids) throws DAOException;
}
