package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 规格组 DAO
 * @author fengyts 2016-07-26 10:08:29
 */
public interface SpecGroupDAO {

	/**
	 * 插入  规格组
	 * @param specGroupDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Long insert(SpecGroupDO specGroupDO) throws DAOException;

	/**
	 * 根据ID更新 规格组全部属性
	 * @param specGroupDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Integer update(SpecGroupDO specGroupDO) throws DAOException;

	/**
	 * 根据ID删除 规格组
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 规格组部分属性，包括全部
	 * @param specGroupDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Integer updateDynamic(SpecGroupDO specGroupDO) throws DAOException;

	/**
	 * 根据ID查询 一个 规格组
	 * @param id
	 * @return SpecGroupDO
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	SpecGroupDO selectById(Long id) throws DAOException;

	/**
	 * 根据  规格组 动态返回记录数
	 * @param specGroupDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	Long selectCountDynamic(SpecGroupDO specGroupDO) throws DAOException;

	/**
	 * 根据  规格组 动态返回 规格组 列表
	 * @param specGroupDO
	 * @return List<SpecGroupDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	List<SpecGroupDO> selectDynamic(SpecGroupDO specGroupDO) throws DAOException;

	/**
	 * 根据  规格组 动态返回 规格组 Limit 列表
	 * @param specGroupDO start,pageSize属性必须指定
	 * @return List<SpecGroupDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:29
	 */
	List<SpecGroupDO> selectDynamicPageQuery(SpecGroupDO specGroupDO) throws DAOException;
}
