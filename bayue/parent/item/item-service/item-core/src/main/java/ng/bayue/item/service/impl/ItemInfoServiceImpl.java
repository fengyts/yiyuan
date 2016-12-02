package ng.bayue.item.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemInfoDTO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.ItemInfoDAO;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.util.Page;

@Service(value="itemInfoService")
public class ItemInfoServiceImpl  implements ItemInfoService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private ItemInfoDAO itemInfoDAO;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public Long insert(ItemInfoDO itemInfoDO) throws ServiceException {
		try {
			return itemInfoDAO.insert(itemInfoDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

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

	public Page<ItemInfoDTO> queryPageListByItemInfoDO(ItemInfoDO itemInfoDO) {
		if (itemInfoDO != null) {
			Long totalCount = this.selectCountDynamic(itemInfoDO);
			List<ItemInfoDO> resultList = this.selectDynamicPageQuery(itemInfoDO);
			List<ItemInfoDTO> result = handleAssociationData(resultList);

			Page<ItemInfoDTO> page = new Page<ItemInfoDTO>();
			page.setPageNo(itemInfoDO.getStartPage());
			page.setPageSize(itemInfoDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(result);
			return page;
		}
		return new Page<ItemInfoDTO>();
	}

	public Page<ItemInfoDTO> queryPageListByItemInfoDOAndStartPageSize(ItemInfoDO itemInfoDO,int startPage,int pageSize){
		if (itemInfoDO != null && startPage>0 && pageSize>0) {
			itemInfoDO.setStartPage(startPage);
			itemInfoDO.setPageSize(pageSize);
			Page<ItemInfoDTO> page = this.queryPageListByItemInfoDO(itemInfoDO);
			
			return page;
		}
		return new Page<ItemInfoDTO>();
	}
	
	private List<ItemInfoDTO> handleAssociationData(List<ItemInfoDO> list){
		List<ItemInfoDTO> result = new ArrayList<ItemInfoDTO>();
		if(CollectionUtils.isEmpty(list)){
			return result;
		}
		List<Long> listLarge = new ArrayList<Long>();
		List<Long> listSmall = new ArrayList<Long>();
		List<Long> listDic = new ArrayList<Long>();
		for(ItemInfoDO info : list){
			listLarge.add(info.getLargeId());
			listSmall.add(info.getSmallId());
			listDic.add(info.getUnitId());
		}
		List<CategoryDO> listLargeDO = categoryService.selectByIds(listLarge);
		List<CategoryDO> listSmallDO = categoryService.selectByIds(listSmall);
		List<DictionaryDO> listDicDO = dictionaryService.selectByIds(listDic);
		for(ItemInfoDO info : list){
			ItemInfoDTO infoDto = new ItemInfoDTO();
			try {
				long largeId = info.getLargeId();
				for(CategoryDO cate : listLargeDO){
					if(largeId == cate.getId().longValue()){
						infoDto.setLargeCateName(cate.getName());
						break;
					}
				}
				long smallId = info.getSmallId();
				for(CategoryDO cateSmall : listSmallDO){
					if(smallId == cateSmall.getId().longValue()){
						infoDto.setSmallCateName(cateSmall.getName());
						break;
					}
				}
				long dicId = info.getUnitId();
				for(DictionaryDO dic : listDicDO){
					if(dicId == dic.getId().longValue()){
						infoDto.setUnitName(dic.getName());
						break;
					}
				}
				BeanUtils.copyProperties(infoDto, info);
				result.add(infoDto);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("", e);
			}
		}
		return result;
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
