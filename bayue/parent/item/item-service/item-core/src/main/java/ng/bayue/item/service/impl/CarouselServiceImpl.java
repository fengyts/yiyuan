package ng.bayue.item.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.CarouselDAO;
import ng.bayue.item.service.CarouselService;

@Service(value="carouselService")
public class CarouselServiceImpl  implements CarouselService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CarouselDAO carouselDAO;

	@Override
	public Long insert(CarouselDO carouselDO) throws ServiceException {
		try {
			return carouselDAO.insert(carouselDO);
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
			if(isAllField){
				return (Integer) carouselDAO.update(carouselDO);
			}else{
				return (Integer) carouselDAO.updateDynamic(carouselDO);
			}
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

	public Page<CarouselDO> queryPageListByCarouselDOAndStartPageSize(CarouselDO carouselDO,int startPage,int pageSize){
		if (carouselDO != null && startPage>0 && pageSize>0) {
			carouselDO.setStartPage(startPage);
			carouselDO.setPageSize(pageSize);
			return this.queryPageListByCarouselDO(carouselDO);
		}
		return new Page<CarouselDO>();
	}

}
