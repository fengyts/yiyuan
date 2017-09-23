package ng.bayue.base.persist.dao;

import java.util.List;

import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 前台类目跳转链接 DAO
 *
 * @author fengyts 2017-04-01 16:32:53
 */
public interface FrontCategoryLinkDAO extends GeneralDAO<FrontCategoryLinkDO> {
	
	/**
	 * <pre>
	 * 批量插入
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertBatch(List<FrontCategoryLinkDO> list);
	
	/**
	 * <pre>
	 * 根据前台分类id更新链接信息
	 * </pre>
	 *
	 * @param fcLinkDO
	 * @return
	 */
	int updateByFrontCateId(FrontCategoryLinkDO fcLinkDO) ;
	
}
