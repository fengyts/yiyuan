package ng.bayue.base.service.impl;

import java.util.List;

import ng.bayue.base.domain.DistrictInfoDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.DistrictInfoDAO;
import ng.bayue.base.service.DistrictInfoService;
import ng.bayue.common.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="districtInfoService")
public class DistrictInfoServiceImpl  implements DistrictInfoService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private DistrictInfoDAO districtInfoDAO;

	@Override
	public Long insert(DistrictInfoDO districtInfoDO) throws ServiceException {
		try {
			return districtInfoDAO.insert(districtInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(DistrictInfoDO districtInfoDO) throws ServiceException {
//		try {
//			return (Integer) districtInfoDAO.updateById(districtInfoDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(DistrictInfoDO districtInfoDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) districtInfoDAO.update(districtInfoDO);
			}else{
				return (Integer) districtInfoDAO.updateDynamic(districtInfoDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) districtInfoDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(DistrictInfoDO districtInfoDO) throws ServiceException {
//		try {
//			return (Integer) districtInfoDAO.updateDynamic(districtInfoDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public DistrictInfoDO selectById(Long id) throws ServiceException {
		try {
			return districtInfoDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(DistrictInfoDO districtInfoDO) throws ServiceException {
		try {
			return districtInfoDAO.selectCountDynamic(districtInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<DistrictInfoDO> selectDynamic(DistrictInfoDO districtInfoDO) throws ServiceException {
		try {
			return districtInfoDAO.selectDynamic(districtInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<DistrictInfoDO> selectDynamicPageQuery(DistrictInfoDO districtInfoDO) throws ServiceException {
		try {
			return districtInfoDAO.selectDynamicPageQuery(districtInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<DistrictInfoDO> queryPageListByDistrictInfoDO(DistrictInfoDO districtInfoDO) {
		if (districtInfoDO != null) {
			Long totalCount = this.selectCountDynamic(districtInfoDO);
			List<DistrictInfoDO> resultList = this.selectDynamicPageQuery(districtInfoDO);

			Page<DistrictInfoDO> page = new Page<DistrictInfoDO>();
			page.setPageNo(districtInfoDO.getStartPage());
			page.setPageSize(districtInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<DistrictInfoDO>();
	}

	public Page<DistrictInfoDO> queryPageListByDistrictInfoDOAndStartPageSize(DistrictInfoDO districtInfoDO,int startPage,int pageSize){
		if (districtInfoDO != null && startPage>0 && pageSize>0) {
			districtInfoDO.setStartPage(startPage);
			districtInfoDO.setPageSize(pageSize);
			return this.queryPageListByDistrictInfoDO(districtInfoDO);
		}
		return new Page<DistrictInfoDO>();
	}

}
