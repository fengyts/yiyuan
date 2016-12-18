package ng.bayue.item.service;

import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.item.exception.ServiceException;

public interface ItemManagerService {
	
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


}
