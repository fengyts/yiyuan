package ng.bayue.snatch.persist.dao.item;

import java.util.List;

import ng.bayue.snatch.domain.item.CodeDO;
import ng.bayue.snatch.exception.DAOException;


 /**
 * spu,prdid编码生成 DAO
 * @author haisheng.Long 2016-12-06 09:07:19
 */
public interface CodeDAO {

	/**
	 * 插入  spu,prdid编码生成
	 * @param codeDO
	 * @return 主键
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Long insert(CodeDO codeDO) throws DAOException;

	/**
	 * 根据ID更新 spu,prdid编码生成全部属性
	 * @param codeDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Integer update(CodeDO codeDO) throws DAOException;

	/**
	 * 根据ID删除 spu,prdid编码生成
	 * @param id
	 * @return 删除行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Integer deleteById(Long id) throws DAOException;

	/**
	 * 动态更新 spu,prdid编码生成部分属性，包括全部
	 * @param codeDO
	 * @return 更新行数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Integer updateDynamic(CodeDO codeDO) throws DAOException;

	/**
	 * 根据ID查询 一个 spu,prdid编码生成
	 * @param id
	 * @return CodeDO
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	CodeDO selectById(Long id) throws DAOException;

	/**
	 * 根据  spu,prdid编码生成 动态返回记录数
	 * @param codeDO
	 * @return 记录条数
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Long selectCountDynamic(CodeDO codeDO) throws DAOException;

	/**
	 * 根据  spu,prdid编码生成 动态返回 spu,prdid编码生成 列表
	 * @param codeDO
	 * @return List<CodeDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	List<CodeDO> selectDynamic(CodeDO codeDO) throws DAOException;

	/**
	 * 根据  spu,prdid编码生成 动态返回 spu,prdid编码生成 Limit 列表
	 * @param codeDO start,pageSize属性必须指定
	 * @return List<CodeDO>
	 * @throws DAOException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	List<CodeDO> selectDynamicPageQuery(CodeDO codeDO) throws DAOException;
	
	Integer updateCode(String code) throws DAOException;
}
