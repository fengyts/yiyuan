package ng.bayue.item.service;

import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.exception.ServiceException;

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
