package ng.bayue.user.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.common.Page;
import ng.bayue.exception.DAOException;
import ng.bayue.user.domain.UserDO;
import ng.bayue.user.exception.ServiceException;
import ng.bayue.user.persist.dao.UserDAO;
import ng.bayue.user.service.UserService;

@Service(value="userService")
public class UserServiceImpl  implements UserService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserDAO userDAO;

	@Override
	public Long insert(UserDO userDO) throws ServiceException {
		try {
			return userDAO.insert(userDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(UserDO userDO) throws ServiceException {
//		try {
//			return (Integer) userDAO.updateById(userDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(UserDO userDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) userDAO.update(userDO);
			}else{
				return (Integer) userDAO.updateDynamic(userDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) userDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(UserDO userDO) throws ServiceException {
//		try {
//			return (Integer) userDAO.updateDynamic(userDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public UserDO selectById(Long id) throws ServiceException {
		try {
			return userDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(UserDO userDO) throws ServiceException {
		try {
			return userDAO.selectCountDynamic(userDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<UserDO> selectDynamic(UserDO userDO) throws ServiceException {
		try {
			return userDAO.selectDynamic(userDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<UserDO> selectDynamicPageQuery(UserDO userDO) throws ServiceException {
		try {
			return userDAO.selectDynamicPageQuery(userDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	
	@Override
	public Page<UserDO> queryPageListDynamic(UserDO userDO) throws ServiceException{
		if (userDO != null) {
			Long totalCount = this.selectCountDynamic(userDO);

			Page<UserDO> page = new Page<UserDO>();
			page.setPageNo(userDO.getStartPage());
			page.setPageSize(userDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			
			if(null != totalCount && totalCount.longValue() > 0){
				List<UserDO> resultList = this.selectDynamicPageQuery(userDO);
				page.setList(resultList);
			}
			return page;
		}
		return new Page<UserDO>();
	}
	
	@Override
	public Page<UserDO> queryPageListDynamicAndStartPageSize(UserDO userDO, Integer startPage, Integer pageSize) throws ServiceException {
		if (userDO != null && startPage>0 && pageSize>0) {
			userDO.setStartPage(startPage);
			userDO.setPageSize(pageSize);
			return this.queryPageListDynamic(userDO);
		}
		return new Page<UserDO>();
	}
	
	
}
