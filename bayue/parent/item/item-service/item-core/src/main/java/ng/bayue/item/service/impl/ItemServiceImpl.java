package ng.bayue.item.service.impl;

import java.util.List;

import ng.bayue.constant.CommonConstant;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.service.CarouselService;
import ng.bayue.item.service.ItemDescService;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.item.service.ItemService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemDescService descService;
	@Autowired
	private CarouselService carouselService;


	@Override
	public ItemDescDO selectDescByDetailId(Long detailId) {
		if(null == detailId){
			return null;
		}
		ItemDescDO descDO = new ItemDescDO();
		descDO.setDetailId(detailId);
		List<ItemDescDO> list = descService.selectDynamic(descDO);
		if(list.size() != 1){
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CarouselDO> listCarousel() {
		CarouselDO carouselDO = new CarouselDO();
		carouselDO.setStatus(CommonConstant.STATUS.TRUE);
		List<CarouselDO> list = carouselService.selectDynamic(carouselDO);
		return list;
	}
	
}
