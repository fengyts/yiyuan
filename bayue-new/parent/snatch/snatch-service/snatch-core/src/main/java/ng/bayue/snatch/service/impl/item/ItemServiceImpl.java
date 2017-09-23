package ng.bayue.snatch.service.impl.item;

import java.util.List;

import javax.annotation.Resource;

import ng.bayue.service.RedisCacheService;
import ng.bayue.snatch.domain.item.CarouselDO;
import ng.bayue.snatch.domain.item.ItemDescDO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.persist.dao.item.ItemDescDAO;
import ng.bayue.snatch.persist.dao.item.ItemDetailDAO;
import ng.bayue.snatch.persist.dao.item.ItemInfoDAO;
import ng.bayue.snatch.service.item.CarouselService;
import ng.bayue.snatch.service.item.ItemPicturesService;
import ng.bayue.snatch.service.item.ItemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="itemService")
public class ItemServiceImpl implements ItemService {

	private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);
	
	@Resource(name="redisCacheService")
	private RedisCacheService cacheService;
	
	@Autowired
	private ItemDescDAO descDAO;
	@Autowired
	private CarouselService carouselService;
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
		return carouselService.getAllCarousel();
	}
	

}
