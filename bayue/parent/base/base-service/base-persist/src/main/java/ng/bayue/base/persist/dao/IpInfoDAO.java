package ng.bayue.base.persist.dao;

import java.util.List;
import ng.bayue.base.domain.IpInfoDO;
import ng.bayue.base.exception.DAOException;


 /**
 *  DAO
 * @author fengyts 2016-07-15 11:56:24
 */
public interface IpInfoDAO {

	/**
	 * 插入  
	 * @param ipInfoDO
	 * @return 主键
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	Long insert(IpInfoDO ipInfoDO) throws DAOException;

	/**
	 * 根据ID更新 全部属性
	 * @param ipInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	Integer update(IpInfoDO ipInfoDO) throws DAOException;

	/**
	 * 根据ID删除 
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 部分属性，包括全部
	 * @param ipInfoDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	Integer updateDynamic(IpInfoDO ipInfoDO) throws DAOException;

	/**
	 * 根据ID查询 一个 
	 * @param id
	 * @return IpInfoDO
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	IpInfoDO selectById(Long id) throws DAOException;

	/**
	 * 根据   动态返回记录数
	 * @param ipInfoDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	Long selectCountDynamic(IpInfoDO ipInfoDO) throws DAOException;

	/**
	 * 根据   动态返回  列表
	 * @param ipInfoDO
	 * @return List<IpInfoDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	List<IpInfoDO> selectDynamic(IpInfoDO ipInfoDO) throws DAOException;

	/**
	 * 根据   动态返回  Limit 列表
	 * @param ipInfoDO start,pageSize属性必须指定
	 * @return List<IpInfoDO>
	 * @throws DAOException
	 * @author fengyts 2016-07-15 11:56:24
	 */
	List<IpInfoDO> selectDynamicPageQuery(IpInfoDO ipInfoDO) throws DAOException;
	
	/**
	 * <pre>
	 * 根据int型ip获取ipinfo
	 * </pre>
	 *
	 * @param ip
	 * @return
	 */
	IpInfoDO selectIpInfoByIpInt(Long ip);
}
