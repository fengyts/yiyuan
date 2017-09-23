package ng.bayue.base.service;

import ng.bayue.service.common.GeneralService;

import java.util.List;

import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.base.exception.ServiceException;

/**
 * 前台类目 Service
 * 
 * @author fengyts 2017-04-01 16:32:53
 */
public interface FrontCategoryService extends GeneralService<FrontCategoryDO, FrontCategoryDO> {

	/**
	 * <pre>
	 * 批量插入前台类目
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertBatch(List<FrontCategoryDO> list);

	/**
	 * <pre>
	 * 插入前台类目信息和类目链接信息
	 * </pre>
	 *
	 * @param fcDto
	 * @param userId
	 *            操作人Id
	 * @return
	 */
	int insertFrontCategoryAndLinked(FrontCategoryDTO fcDto, Long userId) throws ServiceException;
	
	int updateFrontCategoryAndLinked(FrontCategoryDTO dto, Long userId) throws ServiceException;

	List<FrontCategoryDTO> getAllFrontCategoryAndLink();

	/**
	 * <pre>
	 * 根据frontCategory 的ID查询前台分类以及链接相关信息
	 * </pre>
	 *
	 * @param fcId
	 * @return
	 */
	FrontCategoryDTO selectFCAndLinkByFCID(Long fcId) throws ServiceException;
	
	/**
	 * <pre>
	 * 获取某一类别子类code的最大值
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 */
	String selectMaxCodeDynamic(FrontCategoryDO fcdo);
	

}
