package ng.bayue.item.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ng.bayue.item.constant.CodeConstant;
import ng.bayue.item.domain.DetailSpecDO;
import ng.bayue.item.domain.ItemDescDO;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.ItemPicturesDO;
import ng.bayue.item.dto.ItemDetailDTO;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.service.CodeService;
import ng.bayue.item.service.DetailSpecService;
import ng.bayue.item.service.ItemDescService;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.item.service.ItemManagerService;
import ng.bayue.item.service.ItemPicturesService;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * <pre>
 * item公用接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemManagerServiceImpl.java, v 0.1 2016年12月6日 上午10:21:31
 *          lenovopc Exp $
 */
@Service(value = "itemManagerService")
public class ItemManagerServiceImpl implements ItemManagerService {

	private static final Logger logger = LoggerFactory.getLogger(ItemManagerServiceImpl.class);
	
	@Autowired
	private ItemInfoService infoService;
	@Autowired
	private ItemDetailService detailService;
	@Autowired
	private CodeService codeService;
	@Autowired
	private ItemDetailService itemDetailService;
	@Autowired
	private ItemDescService itemDescService;
	@Autowired
	private DetailSpecService detailSpecService;
	@Autowired
	private ItemPicturesService picturesService;
	
	@Override
	@Deprecated
	@Transactional(propagation = Propagation.REQUIRED)
	public int saveInfoAndDetail(ItemInfoDO infoDO, ItemDetailDO detailDO) throws ServiceException {
		if (null == detailDO) {
			logger.info("插入itemDetail异常,ItemDetailDO 不能为空");
			return -1;
		}
		String spuDetail = detailDO.getSpu();
		if (StringUtils.isEmpty(spuDetail)) {
			logger.info("插入iteminfo和itemdetail异常,spu不能为空");
			return -1;
		}
		Long itemId = detailDO.getItemId();
		if (null == itemId) {
			logger.info("插入itemDetail异常,itemId不能为空");
			return -1;
		}
		String mainTitle = detailDO.getMainTitle();
		String subTitle = detailDO.getSubTitle();
		if (StringUtils.isEmpty(mainTitle) || StringUtils.isEmpty(subTitle)) {
			logger.info("插入itemDetail异常,subTitle和mainTitle不能为空");
			return -1;
		}
		Long res1 = null, res2 = null;

		if (null == infoDO) {
			res1 = detailService.insert(detailDO);
			return 0 < res1 ? 1 : -1;
		} else {
			String spu = infoDO.getSpu();
			Long largeId = infoDO.getLargeId();
			Long smallId = infoDO.getSmallId();
			Long unitId = infoDO.getUnitId();
			String mainTitleSpu = infoDO.getMainTitle();
			if (StringUtils.isEmpty(spu) || null == largeId || null == smallId || null == unitId
					|| StringUtils.isEmpty(mainTitleSpu)) {
				logger.info("插入itemInfo异常，spu,大类id,小类id,单位id不能为空");
				return -1;
			}
			res1 = infoService.insert(infoDO);
			res2 = detailService.insert(detailDO);
			return res1 > 0 && res2 > 0 ? 1 : -1;
		}

	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long saveItemDetail(ItemDetailDTO detailDto) throws ServiceException {
		ItemDetailDO detailDO = new ItemDetailDO();
		try {
			String spu = detailDto.getSpu();
			if(StringUtils.isBlank(spu)){
				throw new ServiceException("save item detail error: the parameter spu is null");
			}
			String description = detailDto.getDescription();
			if(StringUtils.isBlank(description)){
				throw new ServiceException("save item detail error: the parameter description is null");
			}
			String prdid = codeService.getUniqueCode(spu, CodeConstant.CodeType.PRDID_CODE);
			BeanUtils.copyProperties(detailDO, detailDto);
			detailDO.setPrdid(prdid);
			Long detailId = itemDetailService.insert(detailDO);
			if(detailId < 1){
				throw new ServiceException("保存商品详情出错");
			}
			
			Date date = detailDto.getCreateTime();
			Long userId = detailDto.getCreateUserId();
			//保存详情描述信息
			ItemDescDO descDO = new ItemDescDO();
			descDO.setDetailId(detailId);
			descDO.setDescription(description);
			descDO.setItemId(detailDto.getItemId());
			descDO.setCreateTime(date);
			descDO.setModifyTime(date);
			descDO.setCreateUserId(userId);
			descDO.setModifyUserId(userId);
			Long descId = itemDescService.insert(descDO);
			if(descId < 1){
				throw new ServiceException("save item detail error: save item description error");
			}
			//保存规格组信息
			List<DetailSpecDO> listSpecGroups = detailDto.getListSpecGroups();
			if(CollectionUtils.isNotEmpty(listSpecGroups)){
				for(DetailSpecDO specDO : listSpecGroups){
					specDO.setCreateTime(date);
					specDO.setModifyTime(date);
					specDO.setDetailId(detailId);
				}
				int res = detailSpecService.insertBatch(listSpecGroups);
				if(res < 1){
					throw new ServiceException("save item detail error: save item specGroup error");
				}
			}
			//保存图片信息
			String picUrls = detailDto.getPicUrls();
			if(StringUtils.isNotBlank(picUrls)){
				List<ItemPicturesDO> listPics = new ArrayList<ItemPicturesDO>();
				String[] urls = picUrls.split(",");
				int count = 0;
				for(String url : urls){
					ItemPicturesDO picDO = new ItemPicturesDO();
					picDO.setPicture(url);
					picDO.setDetailId(detailId);
					picDO.setItemId(detailDto.getItemId());
					picDO.setCreateTime(date);
					if(count > 0){
						picDO.setIsMaster(false);
					} else {
						picDO.setIsMaster(true); //第一张设置为主图
					}
					count ++;
					listPics.add(picDO);
				}
				picturesService.insertBatch(listPics);
			}
			return detailId;
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long updateItemDetail(ItemDetailDTO detailDto) throws ServiceException {
		String spu = detailDto.getSpu();
		String description = detailDto.getDescription();
		if(StringUtils.isBlank(spu) || StringUtils.isBlank(description)){
			throw new ServiceException("update item detail error: the parameter spu or description is null");
		}
		ItemDetailDO detailDO = new ItemDetailDO();
		try {
			//更新item detail信息
			BeanUtils.copyProperties(detailDO, detailDto);
			int res = itemDetailService.update(detailDO, false);
			if(res < 1){
				throw new ServiceException("update item detail error: server inner error!");
			}
			
			Long detailId = detailDto.getId();
			
			//更新商品描述信息
			ItemDescDO descDO = new ItemDescDO();
			descDO.setDetailId(detailId);
			/*
			List<ItemDescDO> list = itemDescService.selectDynamic(descDO);
			if(1 != list.size()){
				throw new ServiceException("update item detail when update description error: the size of description is not unique");
			}
			descDO.setId(list.get(0).getId());
			descDO.setDescription(description);
			descDO.setModifyUserId(detailDto.getModifyUserId());
			descDO.setModifyTime(detailDto.getModifyTime());
			int res = itemDescService.update(descDO, false);
			*/
			descDO.setDescription(description);
			res = itemDescService.updateByDetailId(descDO);
			if(res < 1){
				throw new ServiceException("update item detail error: server inner error!");
			}
			
			//更新商品规格关联信息
			DetailSpecDO detailSpecDO = new DetailSpecDO();
			detailSpecDO.setDetailId(detailId);
			List<DetailSpecDO> listDbSpecGroups = detailSpecService.selectDynamic(detailSpecDO);
			List<DetailSpecDO> listFront = detailDto.getListSpecGroups();
			if(CollectionUtils.isEmpty(listFront) 
					&& CollectionUtils.isNotEmpty(listDbSpecGroups)){
				res = detailSpecService.deleteByDetailId(detailId);
				if(res < 1){
					throw new ServiceException("delete item associate spec groups error:{}");
				}
			}else{
				List<DetailSpecDO> listNewAdd = new ArrayList<DetailSpecDO>();
//				List<DetailSpecDO> listUpdate = new ArrayList<DetailSpecDO>();
//				List<DetailSpecDO> listDelete = new ArrayList<DetailSpecDO>();
				
				Date modifyTime = detailDto.getModifyTime();
				for(DetailSpecDO specDO : listFront){
					Long id = specDO.getId();
					specDO.setModifyTime(modifyTime);
					if(null == id){ // 新增
						specDO.setDetailId(detailId);
						specDO.setCreateTime(modifyTime);
						listNewAdd.add(specDO);
						//listFront.remove(specDO);
						continue;
					}
					
					for(int i=0;i<listDbSpecGroups.size();i++){ //删除和更新
						DetailSpecDO specDb = listDbSpecGroups.get(i);
						long idDb = specDb.getId();
						if(id.longValue() == idDb){
							//存在sort修改
							if(specDO.getSort().intValue() != specDb.getSort().intValue()){
								res = detailSpecService.update(specDO, false);
								if(res < 1){
									throw new ServiceException("update item associate spec groups error:{delete exists}"); 
								}
							}
							listDbSpecGroups.remove(i);
						}
						
					}
					
					/*if(flag){ // 删除
						res = res = detailSpecService.deleteById(specDO.getId());
						if(res < 1){
							throw new ServiceException("delete item associate spec groups error:{}");
						}
					}*/
					
				}
				
				if(CollectionUtils.isNotEmpty(listNewAdd)){
					res = detailSpecService.insertBatch(listNewAdd);
					if(res < 1){
						throw new ServiceException("delete item associate spec groups error:{}");
					}
				}
				/*if(CollectionUtils.isNotEmpty(listUpdate)){
					for(DetailSpecDO specDO : listUpdate){
						res = detailSpecService.update(specDO, false);
						if(res < 1){
							throw new ServiceException("delete item associate spec groups error:{}");
						}
					}
				}*/
				if(CollectionUtils.isNotEmpty(listDbSpecGroups)){
					for(DetailSpecDO specDO : listDbSpecGroups){
						res = detailSpecService.deleteById(specDO.getId());
						if(res < 1){
							throw new ServiceException("delete item associate spec groups error:{}");
						}
					}
				}
				
			}
			
			
			//更新商品图片信息
			
			return 1L;
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}
		return -1L;
	}


	

}
