package ng.bayue.backend.ao.item;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemDTO;
import ng.bayue.item.domain.dto.ItemDetailDTO;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.item.service.ItemService;
import ng.bayue.util.Page;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDetailAO {

	private static final Logger logger = LoggerFactory.getLogger(ItemDetailAO.class);

	@Autowired
	private ItemDetailService itemDetailService;
	@Autowired
	private ItemInfoService itemInfoService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DictionaryService dictionaryService;

	public Page<ItemDTO> queryPageList(ItemDetailDTO detailDto, Integer pageNo, Integer pageSize) {
		ItemDetailDO detailDO = new ItemDetailDO();
		detailDO.setSpu(detailDto.getSpu());
		detailDO.setPrdid(detailDto.getPrdid());
		detailDO.setBarcode(detailDto.getBarcode());
		detailDO.setItemId(detailDto.getItemId());
		detailDO.setMainTitle(detailDto.getMainTitle());
		detailDO.setItemType(detailDto.getItemType());
		detailDO.setStatus(detailDto.getStatus());
		detailDO.setSubTitle(detailDto.getSubTitle());
		Page<ItemDetailDO> page1 = itemDetailService.queryPageListByItemDetailDOAndStartPageSize(
				detailDO, pageNo, pageSize);
		
		Page<ItemDTO> page = new Page<ItemDTO>();
		List<ItemDTO> listRes = new ArrayList<ItemDTO>();
		
		List<ItemDetailDO> list = page1.getList();
		if(CollectionUtils.isEmpty(list)){
			return page;
		}
		List<Long> smallIds = new ArrayList<Long>();
		List<Long> largeIds = new ArrayList<Long>();
		List<Long> unitIds = new ArrayList<Long>();
		for(ItemDetailDO detailDOTemp : list){
			largeIds.add(detailDOTemp.getLargeId());
			smallIds.add(detailDOTemp.getSmallId());
			unitIds.add(detailDOTemp.getUnitId());
		}
		List<CategoryDO> listCate = categoryService.selectByIds(largeIds);
		List<CategoryDO> listCateSmall = categoryService.selectByIds(smallIds);
		List<DictionaryDO> listUnit = dictionaryService.selectByIds(unitIds);
		
		for(ItemDetailDO detail : list){
			ItemDTO dto = new ItemDTO();
			Long largeId = detail.getLargeId();
			if(null == largeId){
				continue;
			}
			if(CollectionUtils.isNotEmpty(listCate)){
				for(CategoryDO cate : listCate){
					Long cateId = cate.getId();
					if(largeId.longValue() == cateId.longValue()){
						dto.setLargeCateName(cate.getName());
						dto.setLargeId(cateId);
					}
				}
			}
			Long smallId = detail.getSmallId();
			if(null == smallId){
				continue;
			}
			if(CollectionUtils.isNotEmpty(listCateSmall)){
				for(CategoryDO cateSmall : listCateSmall){
					Long smallIdCate = cateSmall.getId();
					if(smallId.longValue() == smallIdCate.longValue()){
						dto.setSmallCateName(cateSmall.getName());
						dto.setSmallId(smallId);
					}
				}
			}
			Long unitId = detail.getUnitId();
			if(null == unitId){
				continue;
			}
			if(CollectionUtils.isNotEmpty(listUnit)){
				for(DictionaryDO dictionaryDO : listUnit){
					Long unitIdCate = dictionaryDO.getId();
					if(unitId.longValue() == unitIdCate.longValue()){
						dto.setUnitName(dictionaryDO.getName());
						dto.setUnitId(dictionaryDO.getId());
					}
				}
			}
			
			try {
				BeanUtils.copyProperties(dto, detail);
				listRes.add(dto);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("", e);
			}
			
		}
		
		page.setList(listRes);
		page.setTotalCount(page.getTotalCount());
		page.setPageNo(page1.getPageNo());
		page.setPageSize(page1.getPageSize());
		
		return page;
	}

	@Deprecated
	public ResultMessage saveItemInfoAndDetail(ItemDTO itemDto) {

		String spu = itemDto.getSpu();
		if (StringUtils.isEmpty(spu)) {
			logger.info("spu 不能为空");
			return ResultMessage.validParameterNull("spu");
		}

		boolean isNewSpu = false;// 是否新增spu,默认不是

		ItemInfoDO infoDO = new ItemInfoDO();
		Long largeId = itemDto.getLargeId();
		Long smallId = itemDto.getSmallId();
		Long unitId = itemDto.getUnitId();
		if (null == largeId && null == smallId && null == unitId) {
			isNewSpu = true;
			infoDO.setSpu(spu);
			infoDO = itemInfoService.selectBySPU(spu);
		}
		if (null == infoDO) {
			logger.info("根据spu获取itemInfo异常");
			return new ResultMessage(ResultMessage.Failure, "根据spu获取itemInfo异常");
		}

		Date date = new Date();
		Long userId = UserHandler.getUser().getId();

		ItemDetailDO detailDO = new ItemDetailDO();
		try {
			BeanUtils.copyProperties(detailDO, itemDto);
			detailDO.setItemTitle(itemDto.getMainTitle());

			detailDO.setCreateTime(date);
			detailDO.setModifyTime(date);
			detailDO.setCreateUserId(userId);
			detailDO.setModifyUserId(userId);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("", e);
		}

		if (isNewSpu) {
			infoDO.setLargeId(largeId);
			infoDO.setSmallId(smallId);
			infoDO.setUnitId(unitId);
			infoDO.setRemark(itemDto.getSpu());

			infoDO.setCreateTime(date);
			infoDO.setModifyTime(date);
			infoDO.setCreateUserId(userId);
			infoDO.setModifyUserId(userId);

			itemService.saveInfoAndDetail(infoDO, detailDO);
		} else {
			detailDO.setItemId(infoDO.getId());
			detailDO.setSpu(infoDO.getSpu());

			itemService.saveInfoAndDetail(null, detailDO);
		}

		return new ResultMessage();
	}
	
	public ResultMessage saveItemDetail(ItemDTO itemDto){
		
		return new ResultMessage();
	}

}
