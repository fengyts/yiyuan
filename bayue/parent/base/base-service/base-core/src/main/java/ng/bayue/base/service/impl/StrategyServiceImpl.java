package ng.bayue.base.service.impl;

import java.util.List;

import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.StrategyDAO;
import ng.bayue.base.service.StrategyService;
import ng.bayue.common.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="strategyService")
public class StrategyServiceImpl  implements StrategyService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private StrategyDAO strategyDAO;

	@Override
	public Long insert(StrategyDO strategyDO) throws ServiceException {
		try {
			return strategyDAO.insert(strategyDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(StrategyDO strategyDO) throws ServiceException {
//		try {
//			return (Integer) strategyDAO.updateById(strategyDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(StrategyDO strategyDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) strategyDAO.update(strategyDO);
			}else{
				return (Integer) strategyDAO.updateDynamic(strategyDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) strategyDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(StrategyDO strategyDO) throws ServiceException {
//		try {
//			return (Integer) strategyDAO.updateDynamic(strategyDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public StrategyDO selectById(Long id) throws ServiceException {
		try {
			return strategyDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(StrategyDO strategyDO) throws ServiceException {
		try {
			return strategyDAO.selectCountDynamic(strategyDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<StrategyDO> selectDynamic(StrategyDO strategyDO) throws ServiceException {
		try {
			return strategyDAO.selectDynamic(strategyDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<StrategyDO> selectDynamicPageQuery(StrategyDO strategyDO) throws ServiceException {
		try {
			return strategyDAO.selectDynamicPageQuery(strategyDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<StrategyDO> queryPageListByStrategyDO(StrategyDO strategyDO) {
		if (strategyDO != null) {
			Long totalCount = this.selectCountDynamic(strategyDO);
			List<StrategyDO> resultList = this.selectDynamicPageQuery(strategyDO);

			Page<StrategyDO> page = new Page<StrategyDO>();
			page.setPageNo(strategyDO.getStartPage());
			page.setPageSize(strategyDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<StrategyDO>();
	}

	public Page<StrategyDO> queryPageListByStrategyDOAndStartPageSize(StrategyDO strategyDO,int startPage,int pageSize){
		if (strategyDO != null && startPage>0 && pageSize>0) {
			strategyDO.setStartPage(startPage);
			strategyDO.setPageSize(pageSize);
			return this.queryPageListByStrategyDO(strategyDO);
		}
		return new Page<StrategyDO>();
	}

}
