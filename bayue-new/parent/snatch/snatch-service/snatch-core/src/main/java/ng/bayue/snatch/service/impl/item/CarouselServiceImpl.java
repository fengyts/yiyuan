package ng.bayue.snatch.service.impl.item;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import ng.bayue.common.Page;
import ng.bayue.constants.CommonConstant;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.service.RedisCacheService;
import ng.bayue.snatch.constant.item.ItemCommonConstant.RedisCacheKeyConstant;
import ng.bayue.snatch.domain.item.CarouselDO;
import ng.bayue.snatch.exception.DAOException;
import ng.bayue.snatch.exception.ServiceException;
import ng.bayue.snatch.persist.dao.item.CarouselDAO;
import ng.bayue.snatch.service.item.CarouselService;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="carouselService")
public class CarouselServiceImpl  implements CarouselService{

	private Log logger = LogFactory.getLog(this.getClass());
	
//	@Resource(type=RedisCacheServiceImpl.class)
	@Resource(name="redisCacheService")
	private RedisCacheService cacheService;
	
	@Autowired
	private CarouselDAO carouselDAO;
	
	@Override
	public Long insert(CarouselDO carouselDO) throws ServiceException {
		try {
			Long id = carouselDAO.insert(carouselDO);
			cacheService.deleteRedisCache(RedisCacheKeyConstant.ITEM_CAROUSEL_REDIS_LIST_ALL_KEY);
			return id;
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(CarouselDO carouselDO) throws ServiceException {
//		try {
//			return (Integer) carouselDAO.updateById(carouselDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(CarouselDO carouselDO,boolean isAllField) throws ServiceException {
		try {
			int res = -1;
			if(isAllField){
				res = carouselDAO.update(carouselDO);
			}else{
				res = carouselDAO.updateDynamic(carouselDO);
			}
			cacheService.deleteRedisCache(RedisCacheKeyConstant.ITEM_CAROUSEL_REDIS_LIST_ALL_KEY);
			return res ;
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) carouselDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(CarouselDO carouselDO) throws ServiceException {
//		try {
//			return (Integer) carouselDAO.updateDynamic(carouselDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public CarouselDO selectById(Long id) throws ServiceException {
		try {
			return carouselDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CarouselDO carouselDO) throws ServiceException {
		try {
			return carouselDAO.selectCountDynamic(carouselDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<CarouselDO> selectDynamic(CarouselDO carouselDO) throws ServiceException {
		try {
			return carouselDAO.selectDynamic(carouselDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<CarouselDO> selectDynamicPageQuery(CarouselDO carouselDO) throws ServiceException {
		try {
			return carouselDAO.selectDynamicPageQuery(carouselDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<CarouselDO> queryPageListByCarouselDO(CarouselDO carouselDO) {
		if (carouselDO != null) {
			Long totalCount = this.selectCountDynamic(carouselDO);
			List<CarouselDO> resultList = this.selectDynamicPageQuery(carouselDO);

			Page<CarouselDO> page = new Page<CarouselDO>();
			page.setPageNo(carouselDO.getStartPage());
			page.setPageSize(carouselDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<CarouselDO>();
	}

	@Override
	public Page<CarouselDO> queryPageListByCarouselDOAndStartPageSize(CarouselDO carouselDO,int startPage,int pageSize){
		if (carouselDO != null && startPage>0 && pageSize>0) {
			carouselDO.setStartPage(startPage);
			carouselDO.setPageSize(pageSize);
			return this.queryPageListByCarouselDO(carouselDO);
		}
		return new Page<CarouselDO>();
	}

	@Override
	public List<CarouselDO> getAllCarousel() {
		try{
			@SuppressWarnings("unchecked")
			List<CarouselDO> list = (List<CarouselDO>) cacheService.getRedisCache(RedisCacheKeyConstant.ITEM_CAROUSEL_REDIS_LIST_ALL_KEY);
			if(CollectionUtils.isNotEmpty(list)){
				return list;
			}
			CarouselDO carouselDO = new CarouselDO();
			carouselDO.setStatus(CommonConstant.STATUS.TRUE);
			list = selectDynamic(carouselDO);
			if(CollectionUtils.isNotEmpty(list)){
				cacheService.setRedisCache(RedisCacheKeyConstant.ITEM_CAROUSEL_REDIS_LIST_ALL_KEY, list, RedisCacheTimeConstant.WEEK);
			}
			return list;
		}catch(Exception e){
			logger.info("获取轮播图异常");
		}
		return Collections.emptyList();
	}
	

}
