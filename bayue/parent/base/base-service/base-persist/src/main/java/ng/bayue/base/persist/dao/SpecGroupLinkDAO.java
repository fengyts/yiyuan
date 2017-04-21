package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.exception.DAOException;


 /**
 * 规格与规格组关系 DAO
 * @author fengyts 2016-07-26 10:08:30
 */
public interface SpecGroupLinkDAO {

	/**
	 * 插入  规格与规格组关系
	 * @param specGroupLinkDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long insert(SpecGroupLinkDO specGroupLinkDO) throws DAOException;

	/**
	 * 根据ID更新 规格与规格组关系全部属性
	 * @param specGroupLinkDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer update(SpecGroupLinkDO specGroupLinkDO) throws DAOException;

	/**
	 * 根据ID删除 规格与规格组关系
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 规格与规格组关系部分属性，包括全部
	 * @param specGroupLinkDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Integer updateDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException;

	/**
	 * 根据ID查询 一个 规格与规格组关系
	 * @param id
	 * @return SpecGroupLinkDO
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	SpecGroupLinkDO selectById(Long id) throws DAOException;

	/**
	 * 根据  规格与规格组关系 动态返回记录数
	 * @param specGroupLinkDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	Long selectCountDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException;

	/**
	 * 根据  规格与规格组关系 动态返回 规格与规格组关系 列表
	 * @param specGroupLinkDO
	 * @return List<SpecGroupLinkDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecGroupLinkDO> selectDynamic(SpecGroupLinkDO specGroupLinkDO) throws DAOException;

	/**
	 * 根据  规格与规格组关系 动态返回 规格与规格组关系 Limit 列表
	 * @param specGroupLinkDO start,pageSize属性必须指定
	 * @return List<SpecGroupLinkDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-26 10:08:30
	 */
	List<SpecGroupLinkDO> selectDynamicPageQuery(SpecGroupLinkDO specGroupLinkDO) throws DAOException;
	
	void insertBatch(List<SpecGroupLinkDO> list) throws DAOException;
	
	int updateBatch(List<SpecGroupLinkDO> list) throws DAOException;
}
