package ng.bayue.user.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import ng.bayue.user.domain.BlackUserDO;
import ng.bayue.user.exception.ServiceException;
import ng.bayue.user.persist.dao.BlackUserDAO;
import ng.bayue.user.service.BlackUserService;
import ng.bayue.exception.DAOException;
import ng.bayue.common.Page;

@Service(value="blackUserService")
public class BlackUserServiceImpl  implements BlackUserService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private BlackUserDAO blackUserDAO;

	@Override
	public Long insert(BlackUserDO blackUserDO) throws ServiceException {
		try {
			return blackUserDAO.insert(blackUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(BlackUserDO blackUserDO) throws ServiceException {
//		try {
//			return (Integer) blackUserDAO.updateById(blackUserDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(BlackUserDO blackUserDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) blackUserDAO.update(blackUserDO);
			}else{
				return (Integer) blackUserDAO.updateDynamic(blackUserDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) blackUserDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(BlackUserDO blackUserDO) throws ServiceException {
//		try {
//			return (Integer) blackUserDAO.updateDynamic(blackUserDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public BlackUserDO selectById(Long id) throws ServiceException {
		try {
			return blackUserDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(BlackUserDO blackUserDO) throws ServiceException {
		try {
			return blackUserDAO.selectCountDynamic(blackUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<BlackUserDO> selectDynamic(BlackUserDO blackUserDO) throws ServiceException {
		try {
			return blackUserDAO.selectDynamic(blackUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<BlackUserDO> selectDynamicPageQuery(BlackUserDO blackUserDO) throws ServiceException {
		try {
			return blackUserDAO.selectDynamicPageQuery(blackUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<BlackUserDO> queryPageListDynamic(BlackUserDO blackUserDO) throws ServiceException{
		if (blackUserDO != null) {
			Long totalCount = this.selectCountDynamic(blackUserDO);

			Page<BlackUserDO> page = new Page<BlackUserDO>();
			page.setPageNo(blackUserDO.getStartPage());
			page.setPageSize(blackUserDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<BlackUserDO> resultList = this.selectDynamicPageQuery(blackUserDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<BlackUserDO>();
	}
	
	@Override
	public Page<BlackUserDO> queryPageListDynamicAndStartPageSize(BlackUserDO blackUserDO, Integer startPage, Integer pageSize) throws ServiceException {
		if (blackUserDO != null && startPage>0 && pageSize>0) {
			blackUserDO.setStartPage(startPage);
			blackUserDO.setPageSize(pageSize);
			return this.queryPageListDynamic(blackUserDO);
		}
		return new Page<BlackUserDO>();
	}
	
	
}
