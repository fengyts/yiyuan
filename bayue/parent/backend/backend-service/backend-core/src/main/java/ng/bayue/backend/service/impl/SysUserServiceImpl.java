package ng.bayue.backend.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.backend.domain.SysUserDO;
import ng.bayue.backend.exception.ServiceException;
import ng.bayue.backend.persist.dao.SysUserDAO;
import ng.bayue.backend.persist.exception.DAOException;
import ng.bayue.backend.service.SysUserService;
import ng.bayue.util.Page;

@Service(value="sysUserService")
public class SysUserServiceImpl  implements SysUserService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private SysUserDAO sysUserDAO;

	@Override
	public Long insert(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.insert(sysUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(SysUserDO sysUserDO) throws ServiceException {
//		try {
//			return (Integer) sysUserDAO.updateById(sysUserDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(SysUserDO sysUserDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) sysUserDAO.update(sysUserDO);
			}else{
				return (Integer) sysUserDAO.updateDynamic(sysUserDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) sysUserDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(SysUserDO sysUserDO) throws ServiceException {
//		try {
//			return (Integer) sysUserDAO.updateDynamic(sysUserDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public SysUserDO selectById(Long id) throws ServiceException {
		try {
			return sysUserDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectCountDynamic(sysUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<SysUserDO> selectDynamic(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectDynamic(sysUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<SysUserDO> selectDynamicPageQuery(SysUserDO sysUserDO) throws ServiceException {
		try {
			return sysUserDAO.selectDynamicPageQuery(sysUserDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<SysUserDO> queryPageListBySysUserDO(SysUserDO sysUserDO) {
		if (sysUserDO != null) {
			Long totalCount = this.selectCountDynamic(sysUserDO);
			List<SysUserDO> resultList = this.selectDynamicPageQuery(sysUserDO);

			Page<SysUserDO> page = new Page<SysUserDO>();
			page.setPageNo(sysUserDO.getStartPage());
			page.setPageSize(sysUserDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<SysUserDO>();
	}

	public Page<SysUserDO> queryPageListBySysUserDOAndStartPageSize(SysUserDO sysUserDO,int startPage,int pageSize){
		if (sysUserDO != null && startPage>0 && pageSize>0) {
			sysUserDO.setStartPage(startPage);
			sysUserDO.setPageSize(pageSize);
			return this.queryPageListBySysUserDO(sysUserDO);
		}
		return new Page<SysUserDO>();
	}

}
