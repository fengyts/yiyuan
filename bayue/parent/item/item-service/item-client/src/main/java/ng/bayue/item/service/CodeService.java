package ng.bayue.item.service;

import java.util.List;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.util.Page;
import ng.bayue.item.domain.CodeDO;
 /**
 * spu,prdid编码生成 Service
 * @author haisheng.long 2016-12-06 09:07:19
 */
public interface CodeService {

	/**
	 * 插入  spu,prdid编码生成
	 * @param codeDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Long insert(CodeDO codeDO) throws ServiceException;

	/**
	 * 根据CodeDO对象更新 spu,prdid编码生成
	 * @param codeDO
	 * @param isAllField 是否更新所有字段(false 动态更新字段，true 更新所有字段,传null或false将动态更新，建议动态更新)
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	int update(CodeDO codeDO,boolean isAllField) throws ServiceException;

	/**
	 * 根据ID删除 spu,prdid编码生成
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 spu,prdid编码生成
	 * @param id
	 * @return CodeDO
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	CodeDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  spu,prdid编码生成 动态返回记录数
	 * @param codeDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Long selectCountDynamic(CodeDO codeDO) throws ServiceException;

	/**
	 * 动态返回 spu,prdid编码生成 列表
	 * @param codeDO
	 * @return List<CodeDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	List<CodeDO> selectDynamic(CodeDO codeDO) throws ServiceException;

	/**
	 * 动态返回 spu,prdid编码生成 分页列表
	 * @param codeDO
	 * @return Page<CodeDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Page<CodeDO> queryPageListByCodeDO(CodeDO codeDO);

	/**
	 * 动态返回 spu,prdid编码生成 分页列表
	 * @param codeDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<CodeDO>
	 * @throws ServiceException
	 * @author longhaisheng 2016-12-06 09:07:19
	 */
	Page<CodeDO> queryPageListByCodeDOAndStartPageSize(CodeDO codeDO,int startPage,int pageSize);

}
