package ng.bayue.base.service;

import java.util.List;

import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.common.Page;
 /**
 * 违禁词 Service
 * @author haisheng.long 2014-12-24 13:11:20
 */
public interface ForbiddenWordsService {

	/**
	 * 插入  违禁词
	 * @param forbiddenWordsDO
	 * @return 主键
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Long insert(ForbiddenWordsDO forbiddenWordsDO,Long userId) throws ServiceException;

	/**
	 * 根据ForbiddenWordsDO对象更新 违禁词
	 * @param forbiddenWordsDO
	 * @param isAllField 是否更新所有字段
	 * @return 更新行数
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	int update(ForbiddenWordsDO forbiddenWordsDO,boolean isAllField,Long userId) throws ServiceException;


	/**
	 * 根据ID删除 违禁词
	 * @param id
	 * @return 物理删除行
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	int deleteById(Long id) throws ServiceException;

	/**
	 * 根据ID查询 一个 违禁词
	 * @param id
	 * @return ForbiddenWordsDO
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	ForbiddenWordsDO selectById(Long id) throws ServiceException;

	/**
	 * 根据  违禁词 动态返回记录数
	 * @param forbiddenWordsDO
	 * @return 记录数
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Long selectCountDynamic(ForbiddenWordsDO forbiddenWordsDO) throws ServiceException;

	/**
	 * 动态返回 违禁词 列表
	 * @param forbiddenWordsDO
	 * @return List<ForbiddenWordsDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	List<ForbiddenWordsDO> selectDynamic(ForbiddenWordsDO forbiddenWordsDO) throws ServiceException;

	/**
	 * 动态返回 违禁词 分页列表
	 * @param forbiddenWordsDO
	 * @return Page<ForbiddenWordsDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Page<ForbiddenWordsDO> queryPageListByForbiddenWordsDO(ForbiddenWordsDO forbiddenWordsDO);

	/**
	 * 动态返回 违禁词 分页列表
	 * @param forbiddenWordsDO
	 * @param startPage 起始页
	 * @param pageSize 每页记录数
	 * @return Page<ForbiddenWordsDO>
	 * @throws ServiceException
	 * @author longhaisheng 2014-12-24 13:11:20
	 */
	Page<ForbiddenWordsDO> queryPageListByForbiddenWordsDOAndStartPageSize(ForbiddenWordsDO forbiddenWordsDO,int startPage,int pageSize);
   
	/**
	 * 
	 * <pre>
	 *  根据传递过来的long数组返回ForbiddenWordsDO数组
	 * </pre>
	 *
	 * @param ids
	 * @param status
	 * @return
	 */
	List<ForbiddenWordsDO> selectListForbiddenWordsDO( List<Long> ids,Integer status);
    
	/**
	 * 
	 * <pre>
	 * 模糊查询
	 * </pre>
	 *
	 * @param forbiddenWordsDO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	Page<ForbiddenWordsDO> queryAllLikedForbiddenWordByPage(ForbiddenWordsDO forbiddenWordsDO, Integer pageNo, Integer pageSize);
	/**
	 * 
	 * <pre>
	 * 插入并更新一条违禁词
	 * </pre>
	 *
	 * @param insertWords
	 */
	void insertAndUpdate(ForbiddenWordsDO insertWords ,Long userId) throws ServiceException;
	
	/**
	 * 
	 * <pre>
	 *  分析sourceField中存在的违禁词,返回分析结果,没有违禁词返回空字符串
	 * </pre>
	 *
	 * @param sourceField
	 * @return 如果返回空字符串("")则表示不存在违禁词,如果存在则返回相应的信息
	 */
	
	String  checkForbiddenWordsField (String sourceField );
	/**
	 * 
	 * <pre>
	 *  获取所以有效的违禁词列表
	 * </pre>
	 *
	 * @return 获取所以有效的违禁词列表list
	 */
	
    List<ForbiddenWordsDO> getAllEffectiveForbiddenWords();
    
    /**
     * 
     * <pre>
     * 此接口用于违禁词增加或者更新时从主库对违禁词增做校验
     * </pre>
     *
     * @param forbiddenWordsDO
     * @return
     * @throws ServiceException
     */
    List<ForbiddenWordsDO> findSameForbiddenWords(ForbiddenWordsDO forbiddenWordsDO)  throws ServiceException;
}
