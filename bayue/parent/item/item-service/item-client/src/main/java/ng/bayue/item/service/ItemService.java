package ng.bayue.item.service;

import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.exception.ServiceException;

/**
 * <pre>
 * 为前端提供开放接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemService.java, v 0.1 2016年12月19日 下午1:32:37 lenovopc Exp $
 */
public interface ItemService {
	
	/**
	 * <pre>
	 * 插入itemInfo和itemDetail信息
	 * </pre>
	 *
	 * @return
	 * @throws ServiceException
	 */
	@Deprecated
	int saveInfoAndDetail(ItemInfoDO infoDO,ItemDetailDO detailDO) throws ServiceException;
	
	ItemDescDO selectDescByDetailId(Long detailId);

}
