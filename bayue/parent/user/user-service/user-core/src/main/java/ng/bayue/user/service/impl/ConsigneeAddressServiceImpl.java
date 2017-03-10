package ng.bayue.user.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.exception.DAOException;
import ng.bayue.exception.ServiceException;
import ng.bayue.user.domain.ConsigneeAddressDO;
import ng.bayue.user.persist.dao.ConsigneeAddressDAO;
import ng.bayue.user.service.ConsigneeAddressService;

@Service(value="consigneeAddressService")
public class ConsigneeAddressServiceImpl  implements ConsigneeAddressService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ConsigneeAddressDAO consigneeAddressDAO;

	@Override
	public Long insert(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
		try {
			return consigneeAddressDAO.insert(consigneeAddressDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
//		try {
//			return (Integer) consigneeAddressDAO.updateById(consigneeAddressDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(ConsigneeAddressDO consigneeAddressDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) consigneeAddressDAO.update(consigneeAddressDO);
			}else{
				return (Integer) consigneeAddressDAO.updateDynamic(consigneeAddressDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) consigneeAddressDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
//		try {
//			return (Integer) consigneeAddressDAO.updateDynamic(consigneeAddressDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public ConsigneeAddressDO selectById(Long id) throws ServiceException {
		try {
			return consigneeAddressDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
		try {
			return consigneeAddressDAO.selectCountDynamic(consigneeAddressDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<ConsigneeAddressDO> selectDynamic(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
		try {
			return consigneeAddressDAO.selectDynamic(consigneeAddressDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<ConsigneeAddressDO> selectDynamicPageQuery(ConsigneeAddressDO consigneeAddressDO) throws ServiceException {
		try {
			return consigneeAddressDAO.selectDynamicPageQuery(consigneeAddressDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<ConsigneeAddressDO> queryPageListDynamic(ConsigneeAddressDO consigneeAddressDO) throws ServiceException{
		if (consigneeAddressDO != null) {
			Long totalCount = this.selectCountDynamic(consigneeAddressDO);

			Page<ConsigneeAddressDO> page = new Page<ConsigneeAddressDO>();
			page.setPageNo(consigneeAddressDO.getStartPage());
			page.setPageSize(consigneeAddressDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<ConsigneeAddressDO> resultList = this.selectDynamicPageQuery(consigneeAddressDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<ConsigneeAddressDO>();
	}
	
	@Override
	public Page<ConsigneeAddressDO> queryPageListDynamicAndStartPageSize(ConsigneeAddressDO consigneeAddressDO, Integer startPage, Integer pageSize) throws ServiceException {
		if (consigneeAddressDO != null && startPage>0 && pageSize>0) {
			consigneeAddressDO.setStartPage(startPage);
			consigneeAddressDO.setPageSize(pageSize);
			return this.queryPageListDynamic(consigneeAddressDO);
		}
		return new Page<ConsigneeAddressDO>();
	}
	
	
}
