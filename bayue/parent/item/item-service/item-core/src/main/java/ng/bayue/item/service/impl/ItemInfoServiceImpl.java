package ng.bayue.item.service.impl;

import java.util.List;

import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.ItemInfoDAO;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value="itemInfoService")
public class ItemInfoServiceImpl  implements ItemInfoService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemInfoDAO itemInfoDAO;

	@Override
	public Long insert(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.insert(itemInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateById(ItemInfoDO itemInfoDO) throws ServiceException {
//		try {
//			return (Integer) itemInfoDAO.updateById(itemInfoDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public int update(ItemInfoDO itemInfoDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) itemInfoDAO.update(itemInfoDO);
			}else{
				return (Integer) itemInfoDAO.updateDynamic(itemInfoDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) itemInfoDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

//	@Override
//	public int updateDynamic(ItemInfoDO itemInfoDO) throws ServiceException {
//		try {
//			return (Integer) itemInfoDAO.updateDynamic(itemInfoDO);
//		}catch(DAOException e){
//			logger.error(e);
//            throw new ServiceException(e);
//		}
//	}

	@Override
	public ItemInfoDO selectById(Long id) throws ServiceException {
		try {
			return itemInfoDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectCountDynamic(itemInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<ItemInfoDO> selectDynamic(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectDynamic(itemInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}
	

	private List<ItemInfoDO> selectDynamicPageQuery(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.selectDynamicPageQuery(itemInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<ItemInfoDO> queryPageListByItemInfoDO(ItemInfoDO itemInfoDO) {
		if (itemInfoDO != null) {
			Long totalCount = this.selectCountDynamic(itemInfoDO);
			List<ItemInfoDO> resultList = this.selectDynamicPageQuery(itemInfoDO);

			Page<ItemInfoDO> page = new Page<ItemInfoDO>();
			page.setPageNo(itemInfoDO.getStartPage());
			page.setPageSize(itemInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<ItemInfoDO>();
	}

	public Page<ItemInfoDO> queryPageListByItemInfoDOAndStartPageSize(ItemInfoDO itemInfoDO,int startPage,int pageSize){
		if (itemInfoDO != null && startPage>0 && pageSize>0) {
			itemInfoDO.setStartPage(startPage);
			itemInfoDO.setPageSize(pageSize);
			return this.queryPageListByItemInfoDO(itemInfoDO);
		}
		return new Page<ItemInfoDO>();
	}

	@Override
	public ItemInfoDO selectBySPU(String spu) throws ServiceException{
		if(StringUtils.isEmpty(spu)){
			return null;
		}
		try {
			List<ItemInfoDO> list = itemInfoDAO.selectBySPU(spu);
			if(CollectionUtils.isEmpty(list)){
				logger.info("spu 不存在");
				return null;
			}
			if(1 < list.size()){
				logger.info("数据库中存在2个相同的spu");
				return null;
			}
			return list.get(0);
		} catch (DAOException e) {
			logger.error("根据spu获取iteminfo异常：{}", e);
		}
		return null;
	}

	@Override
	public List<ItemInfoDO> selectBySpus(List<String> spus) throws ServiceException {
		return null;
	}

}
