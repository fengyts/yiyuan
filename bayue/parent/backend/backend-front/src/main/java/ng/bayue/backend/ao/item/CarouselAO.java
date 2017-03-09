package ng.bayue.backend.ao.item;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.common.Page;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.service.CarouselService;

@Service
public class CarouselAO {

	private static final Logger logger = LoggerFactory.getLogger(CarouselAO.class);

	@Autowired
	private CarouselService carouselService;

	public Page<CarouselDO> queryPage(CarouselDO carouselDO, Integer pageNo, Integer pageSize) {
		Page<CarouselDO> page = carouselService.queryPageListByCarouselDOAndStartPageSize(carouselDO, pageNo, pageSize);
		return page;
	}

	public CarouselDO selectById(Long id) {
		if (null == id) {
			return null;
		}
		return carouselService.selectById(id);
	}
	
	public ResultMessage save(CarouselDO carouselDO){
		if(null == carouselDO){
			return ResultMessage.validParameterNull(new String[0]);
		}
		Date date = new Date();
		carouselDO.setCreateTime(date);
		carouselDO.setModifyTime(date);
		Long userId = UserHandler.getUser().getId();
		carouselDO.setCreateUserId(userId);
		carouselDO.setModifyUserId(userId);
		Long id = carouselService.insert(carouselDO);
		if(id == null || id < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	public ResultMessage update(CarouselDO carouselDO){
		if(null == carouselDO){
			return ResultMessage.validParameterNull(new String[0]);
		}
		carouselDO.setModifyTime(new Date());
		carouselDO.setModifyUserId(UserHandler.getUser().getId());
		int res = carouselService.update(carouselDO, false);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}

}
