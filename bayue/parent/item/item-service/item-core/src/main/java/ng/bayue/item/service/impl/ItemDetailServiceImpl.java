package ng.bayue.item.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.ItemDetailDAO;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.util.Page;

@Service(value = "itemDetailService")
public class ItemDetailServiceImpl implements ItemDetailService {

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemDetailDAO itemDetailDAO;

	@Override
	public Long insert(ItemDetailDO itemDetailDO) throws ServiceException {
		try {
			Long id = itemDetailDAO.insert(itemDetailDO);
			return id;
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int update(ItemDetailDO itemDetailDO, boolean isAllField) throws ServiceException {
		try {
			if (isAllField) {
				return (Integer) itemDetailDAO.update(itemDetailDO);
			} else {
				return (Integer) itemDetailDAO.updateDynamic(itemDetailDO);
			}
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) itemDetailDAO.deleteById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public ItemDetailDO selectById(Long id) throws ServiceException {
		try {
			return itemDetailDAO.selectById(id);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemDetailDO itemDetailDO) throws ServiceException {
		try {
			return itemDetailDAO.selectCountDynamic(itemDetailDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ItemDetailDO> selectDynamic(ItemDetailDO itemDetailDO) throws ServiceException {
		try {
			return itemDetailDAO.selectDynamic(itemDetailDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	private List<ItemDetailDO> selectDynamicPageQuery(ItemDetailDO itemDetailDO)
			throws ServiceException {
		try {
			return itemDetailDAO.selectDynamicPageQuery(itemDetailDO);
		} catch (DAOException e) {
			logger.error(e);
			throw new ServiceException(e);
		}
	}

	public Page<ItemDetailDO> queryPageListByItemDetailDO(ItemDetailDO itemDetailDO) {
		if (itemDetailDO != null) {
			Long totalCount = this.selectCountDynamic(itemDetailDO);
			List<ItemDetailDO> resultList = this.selectDynamicPageQuery(itemDetailDO);

			Page<ItemDetailDO> page = new Page<ItemDetailDO>();
			page.setPageNo(itemDetailDO.getStartPage());
			page.setPageSize(itemDetailDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<ItemDetailDO>();
	}

	public Page<ItemDetailDO> queryPageListByItemDetailDOAndStartPageSize(
			ItemDetailDO itemDetailDO, int startPage, int pageSize) {
		if (itemDetailDO != null && startPage > 0 && pageSize > 0) {
			itemDetailDO.setStartPage(startPage);
			itemDetailDO.setPageSize(pageSize);
			return this.queryPageListByItemDetailDO(itemDetailDO);
		}
		return new Page<ItemDetailDO>();
	}

	@Override
	@Transactional
	public int updateBatch(List<ItemDetailDO> list) throws ServiceException {
		if(CollectionUtils.isEmpty(list)){
			return -1;
		}
		int res = itemDetailDAO.updateBatch(list);
		return res < 1 ? -1 : res;
	}

}
