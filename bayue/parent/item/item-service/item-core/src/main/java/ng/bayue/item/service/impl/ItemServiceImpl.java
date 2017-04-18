package ng.bayue.item.service.impl;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.constant.CommonConstant;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.dto.ItemDetailDTO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.persist.dao.CarouselDAO;
import ng.bayue.item.persist.dao.ItemDescDAO;
import ng.bayue.item.persist.dao.ItemDetailDAO;
import ng.bayue.item.persist.dao.ItemInfoDAO;
import ng.bayue.item.service.ItemPicturesService;
import ng.bayue.item.service.ItemService;

@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

	@Autowired
	private ItemDescDAO descDAO;
	@Autowired
	private CarouselDAO carouselDAO;
	@Autowired
	private ItemInfoDAO infoDAO;
	@Autowired
	private ItemDetailDAO detailDAO;
	@Autowired
	private ItemPicturesService picturesService;


	@Override
	public ItemDescDO selectDescByDetailId(Long detailId) {
		if(null == detailId){
			return null;
		}
		ItemDescDO descDO = new ItemDescDO();
		descDO.setDetailId(detailId);
		try {
			List<ItemDescDO> list = descDAO.selectDynamic(descDO);
			if(list.size() != 1){
				return null;
			}
			return list.get(0);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	public List<CarouselDO> getAllCarousel() {
		CarouselDO carouselDO = new CarouselDO();
		carouselDO.setStatus(CommonConstant.STATUS.TRUE);
		try {
			List<CarouselDO> list = carouselDAO.selectDynamic(carouselDO);
			return list;
		} catch (DAOException e) {
			logger.error("", e);
		}
		return Collections.emptyList();
	}
	

}
