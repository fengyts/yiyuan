package ng.bayue.base.service.remote;

import java.util.List;

import ng.bayue.base.dto.IndexFrontCategoryDTO;

/**
 * <pre>
 * 前端base项目的远程接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: RemoteBaseService.java, v 0.1 2017年3月31日 下午4:55:31 lenovopc Exp
 *          $
 */
public interface RemoteBaseService {

	/**
	 * <pre>
	 * 获取前段商品分类列表
	 * </pre>
	 *
	 * @return
	 */
	List<IndexFrontCategoryDTO> getFrontCategoryList();

}
