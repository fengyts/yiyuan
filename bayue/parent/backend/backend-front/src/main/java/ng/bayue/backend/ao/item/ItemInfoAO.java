package ng.bayue.backend.ao.item;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.common.Page;
import ng.bayue.item.domain.ItemDetailDO;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.dto.ItemInfoDTO;
import ng.bayue.item.service.ItemDetailService;
import ng.bayue.item.service.ItemInfoService;

@Service
public class ItemInfoAO {

	private static final Logger logger = LoggerFactory.getLogger(ItemInfoAO.class);

	@Autowired
	private ItemInfoService itemInfoService;
	@Autowired
	private ItemDetailService itemDetailService;

	public Page<ItemInfoDTO> queryPageList(ItemInfoDO infoDO, Integer pageNo, Integer pageSize) {
		Page<ItemInfoDTO> page = itemInfoService.queryPageListByItemInfoDOAndStartPageSize(infoDO, pageNo, pageSize);
		return page;
	}

	public ItemInfoDO getInfoBySPU(String spu) {
		if (StringUtils.isEmpty(spu)) {
			return null;
		}
		ItemInfoDO infoDO = itemInfoService.selectBySPU(spu);
		return infoDO;

	}

	public ItemInfoDO selectItemInfoById(Long id) {
		if (null == id || id.longValue() < 1L) {
			return null;
		}
		return itemInfoService.selectById(id);
	}

	public ResultMessage saveItemInfo(ItemInfoDO infoDO) {
		String mainTitle = infoDO.getMainTitle();
		Long largeId = infoDO.getLargeId();
		Long smallId = infoDO.getSmallId();
		Long unitId = infoDO.getUnitId();
		if (StringUtils.isBlank(mainTitle) || null == largeId || null == smallId || null == unitId) {
			return ResultMessage.validParameterNull("SPU名称 ","一级类别","二级类别","单位");
		}
		
		infoDO.setCreateTime(new Date());
		infoDO.setModifyTime(new Date());
		infoDO.setCreateUserId(UserHandler.getUser().getId());
		infoDO.setModifyUserId(UserHandler.getUser().getId());
		
		Long id = itemInfoService.insert(infoDO);
		if(null != id && id.longValue() < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	private boolean validateSPU(String spu){
		ItemDetailDO itemDetailDO  = new ItemDetailDO();
		itemDetailDO.setSpu(spu);
		List<ItemDetailDO> list = itemDetailService.selectDynamic(itemDetailDO);
		return CollectionUtils.isEmpty(list);
	}
	
	public ResultMessage updateItemInfo(ItemInfoDO infoDO,boolean isRebuildSPU){
		String mainTitle = infoDO.getMainTitle();
		Long largeId = infoDO.getLargeId();
		Long smallId = infoDO.getSmallId();
		Long unitId = infoDO.getUnitId();
		if (StringUtils.isBlank(mainTitle) || null == largeId || null == smallId || null == unitId) {
			return ResultMessage.validParameterNull("SPU名称 ","一级类别","二级类别","单位");
		}
		if(!validateSPU(infoDO.getSpu())){
			return ResultMessage.validIsUsed();
		}
		
		itemInfoService.updateRebuildSpu(infoDO, isRebuildSPU);
		
		return new ResultMessage();
	}

}
