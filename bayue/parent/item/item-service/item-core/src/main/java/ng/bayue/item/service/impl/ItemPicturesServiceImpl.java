package ng.bayue.item.service.impl;

import java.util.List;

import ng.bayue.item.domain.ItemPicturesDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.ItemPicturesDAO;
import ng.bayue.item.service.ItemPicturesService;
import ng.bayue.util.Page;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="itemPicturesService")
public class ItemPicturesServiceImpl  implements ItemPicturesService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemPicturesDAO itemPicturesDAO;

	@Override
	public Long insert(ItemPicturesDO itemPicturesDO) throws ServiceException {
		try {
			return itemPicturesDAO.insert(itemPicturesDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(ItemPicturesDO itemPicturesDO) throws ServiceException {
//		try {
//			return (Integer) itemPicturesDAO.updateById(itemPicturesDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(ItemPicturesDO itemPicturesDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) itemPicturesDAO.update(itemPicturesDO);
			}else{
				return (Integer) itemPicturesDAO.updateDynamic(itemPicturesDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) itemPicturesDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException {
//		try {
//			return (Integer) itemPicturesDAO.updateDynamic(itemPicturesDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public ItemPicturesDO selectById(Long id) throws ServiceException {
		try {
			return itemPicturesDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException {
		try {
			return itemPicturesDAO.selectCountDynamic(itemPicturesDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<ItemPicturesDO> selectDynamic(ItemPicturesDO itemPicturesDO) throws ServiceException {
		try {
			return itemPicturesDAO.selectDynamic(itemPicturesDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<ItemPicturesDO> selectDynamicPageQuery(ItemPicturesDO itemPicturesDO) throws ServiceException {
		try {
			return itemPicturesDAO.selectDynamicPageQuery(itemPicturesDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<ItemPicturesDO> queryPageListByItemPicturesDO(ItemPicturesDO itemPicturesDO) {
		if (itemPicturesDO != null) {
			Long totalCount = this.selectCountDynamic(itemPicturesDO);
			List<ItemPicturesDO> resultList = this.selectDynamicPageQuery(itemPicturesDO);

			Page<ItemPicturesDO> page = new Page<ItemPicturesDO>();
			page.setPageNo(itemPicturesDO.getStartPage());
			page.setPageSize(itemPicturesDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<ItemPicturesDO>();
	}

	public Page<ItemPicturesDO> queryPageListByItemPicturesDOAndStartPageSize(ItemPicturesDO itemPicturesDO,int startPage,int pageSize){
		if (itemPicturesDO != null && startPage>0 && pageSize>0) {
			itemPicturesDO.setStartPage(startPage);
			itemPicturesDO.setPageSize(pageSize);
			return this.queryPageListByItemPicturesDO(itemPicturesDO);
		}
		return new Page<ItemPicturesDO>();
	}

}
