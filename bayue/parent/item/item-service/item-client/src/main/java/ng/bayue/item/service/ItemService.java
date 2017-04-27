package ng.bayue.item.service;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.dto.ItemDetailDTO;

/**
 * <pre>
 * 为前端提供开放接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemService.java, v 0.1 2016年12月19日 下午1:32:37 lenovopc Exp $
 */
public interface ItemService {
	
	
	ItemDescDO selectDescByDetailId(Long detailId);
	
	/**
	 * <pre>
	 * 获取app首页轮播幻灯片列表,弃用，请用<link>ng.bayue.item.service.CarouselService</link>接口的 getAllCarousel()方法
	 * </pre>
	 *
	 * @return
	 */
	//@Deprecated
	List<CarouselDO> getAllCarousel();
	

}
