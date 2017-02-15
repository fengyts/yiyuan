package ng.bayue.item.service.impl;

import java.util.List;

import ng.bayue.common.Page;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.ItemDescDAO;
import ng.bayue.item.service.ItemDescService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="itemDescService")
public class ItemDescServiceImpl  implements ItemDescService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemDescDAO itemDescDAO;

	@Override
	public Long insert(ItemDescDO itemDescDO) throws ServiceException {
		try {
			return itemDescDAO.insert(itemDescDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int update(ItemDescDO itemDescDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) itemDescDAO.update(itemDescDO);
			}else{
				return (Integer) itemDescDAO.updateDynamic(itemDescDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) itemDescDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public ItemDescDO selectById(Long id) throws ServiceException {
		try {
			return itemDescDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemDescDO itemDescDO) throws ServiceException {
		try {
			return itemDescDAO.selectCountDynamic(itemDescDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<ItemDescDO> selectDynamic(ItemDescDO itemDescDO) throws ServiceException {
		try {
			return itemDescDAO.selectDynamic(itemDescDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<ItemDescDO> selectDynamicPageQuery(ItemDescDO itemDescDO) throws ServiceException {
		try {
			return itemDescDAO.selectDynamicPageQuery(itemDescDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<ItemDescDO> queryPageListByItemDescDO(ItemDescDO itemDescDO) {
		if (itemDescDO != null) {
			Long totalCount = this.selectCountDynamic(itemDescDO);
			List<ItemDescDO> resultList = this.selectDynamicPageQuery(itemDescDO);

			Page<ItemDescDO> page = new Page<ItemDescDO>();
			page.setPageNo(itemDescDO.getStartPage());
			page.setPageSize(itemDescDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<ItemDescDO>();
	}

	public Page<ItemDescDO> queryPageListByItemDescDOAndStartPageSize(ItemDescDO itemDescDO,int startPage,int pageSize){
		if (itemDescDO != null && startPage>0 && pageSize>0) {
			itemDescDO.setStartPage(startPage);
			itemDescDO.setPageSize(pageSize);
			return this.queryPageListByItemDescDO(itemDescDO);
		}
		return new Page<ItemDescDO>();
	}

	@Override
	public int updateByDetailId(ItemDescDO itemDescDO) throws ServiceException {
		if(null == itemDescDO || 1 > itemDescDO.getDetailId().longValue()){
			return -1;
		}
		try {
			return itemDescDAO.updateByDetailId(itemDescDO);
		} catch (DAOException e) {
			logger.error("", e);
		}
		return -1;
	}

}
