package ng.bayue.snatch.service.item;

import ng.bayue.snatch.domain.item.ItemDetailDO;
import ng.bayue.snatch.domain.item.ItemInfoDO;
import ng.bayue.snatch.dto.item.ItemDetailDTO;
import ng.bayue.snatch.exception.ServiceException;

/**
 * <pre>
 * 商品管理接口,模块间或者后台使用
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemManagerService.java, v 0.1 2016年12月19日 下午1:32:03 lenovopc Exp $
 */
public interface ItemManagerService {
	
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
	
	/**
	 * <pre>
	 * 保存商品详情信息，包括如下：
	 * 		1.保存商品detail;2.保存商品描述信息description;3.保存商品规格组信息
	 * </pre>
	 *
	 * @param detailDto
	 * @return
	 * @throws ServiceException
	 */
	Long saveItemDetail(ItemDetailDTO detailDto) throws ServiceException;
	
	/**
	 * <pre>
	 * 更新商品详情
	 * </pre>
	 *
	 * @param detailDto
	 * @return
	 * @throws ServiceException
	 */
	Long updateItemDetail(ItemDetailDTO detailDto) throws ServiceException;


}
