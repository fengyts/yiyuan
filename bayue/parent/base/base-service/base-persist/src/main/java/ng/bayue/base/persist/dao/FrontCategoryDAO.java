package ng.bayue.base.persist.dao;

import java.util.List;

import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.service.common.GeneralDAO;

 /**
 * 前台类目 DAO
 *
 * @author fengyts 2017-04-01 16:32:53
 */
public interface FrontCategoryDAO extends GeneralDAO<FrontCategoryDO> {
	
	/**
	 * <pre>
	 * 批量插入
	 * </pre>
	 *
	 * @param list
	 * @return
	 */
	int insertBatch(List<FrontCategoryDO> list);
	
}
