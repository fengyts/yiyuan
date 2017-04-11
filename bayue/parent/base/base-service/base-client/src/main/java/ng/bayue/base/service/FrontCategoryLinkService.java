package ng.bayue.base.service;

import ng.bayue.service.common.GeneralService;

import java.util.List;

import ng.bayue.base.domain.FrontCategoryLinkDO;

/**
 * 前台类目跳转链接 Service
 * 
 * @author haisheng.long 2017-04-01 16:32:53
 */
public interface FrontCategoryLinkService extends GeneralService<FrontCategoryLinkDO, FrontCategoryLinkDO> {

	int insertBatch(List<FrontCategoryLinkDO> list);
	
}
