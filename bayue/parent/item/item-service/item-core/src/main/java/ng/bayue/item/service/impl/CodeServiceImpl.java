package ng.bayue.item.service.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.item.domain.CodeDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.CodeDAO;
import ng.bayue.item.service.CodeService;
import ng.bayue.util.Page;

@Service(value="codeService")
public class CodeServiceImpl  implements CodeService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CodeDAO codeDAO;

	@Override
	public Long insert(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.insert(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int update(CodeDO codeDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) codeDAO.update(codeDO);
			}else{
				return (Integer) codeDAO.updateDynamic(codeDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) codeDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public CodeDO selectById(Long id) throws ServiceException {
		try {
			return codeDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectCountDynamic(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<CodeDO> selectDynamic(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectDynamic(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<CodeDO> selectDynamicPageQuery(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectDynamicPageQuery(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<CodeDO> queryPageListByCodeDO(CodeDO codeDO) {
		if (codeDO != null) {
			Long totalCount = this.selectCountDynamic(codeDO);
			List<CodeDO> resultList = this.selectDynamicPageQuery(codeDO);

			Page<CodeDO> page = new Page<CodeDO>();
			page.setPageNo(codeDO.getStartPage());
			page.setPageSize(codeDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<CodeDO>();
	}

	public Page<CodeDO> queryPageListByCodeDOAndStartPageSize(CodeDO codeDO,int startPage,int pageSize){
		if (codeDO != null && startPage>0 && pageSize>0) {
			codeDO.setStartPage(startPage);
			codeDO.setPageSize(pageSize);
			return this.queryPageListByCodeDO(codeDO);
		}
		return new Page<CodeDO>();
	}

}
