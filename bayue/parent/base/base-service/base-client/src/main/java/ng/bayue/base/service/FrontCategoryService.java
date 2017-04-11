package ng.bayue.base.service;

import ng.bayue.service.common.GeneralService;

import java.util.List;

import ng.bayue.base.domain.FrontCategoryDO;

 /**
 * 前台类目 Service
 * @author haisheng.long 2017-04-01 16:32:53
 */
public interface FrontCategoryService extends GeneralService<FrontCategoryDO, FrontCategoryDO> {

	int insertBatch(List<FrontCategoryDO> list);
	
	
}
