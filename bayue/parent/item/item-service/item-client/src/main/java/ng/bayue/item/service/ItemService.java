package ng.bayue.item.service;

import java.util.List;

import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.domain.ItemDescDO;

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
	 * 获取app首页轮播幻灯片列表
	 * </pre>
	 *
	 * @return
	 */
	List<CarouselDO> listCarousel();

}
