package ng.bayue.backend.ao.item;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.service.CategoryService;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.domain.dto.ItemInfoDTO;
import ng.bayue.item.service.ItemInfoService;
import ng.bayue.util.Page;

@Service
public class ItemInfoAO {

	private static final Logger logger = LoggerFactory.getLogger(ItemInfoAO.class);

	@Autowired
	private ItemInfoService itemInfoService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DictionaryService dictionaryService;
	
	public Page<ItemInfoDTO> queryPageList(ItemInfoDO infoDO,Integer pageNo,Integer pageSize){
		Page<ItemInfoDTO> page = itemInfoService.queryPageListByItemInfoDOAndStartPageSize(infoDO, pageNo, pageSize);
		return page;
	}
	
	public ItemInfoDO getInfoBySPU(String spu){
		if(StringUtils.isEmpty(spu)){
			return null;
		}
		ItemInfoDO infoDO = itemInfoService.selectBySPU(spu);
		return infoDO;
		
	}
	
	public ItemInfoDO selectItemInfoById(Long id){
		if(null == id || id.longValue() < 1L){
			return null;
		}
		return itemInfoService.selectById(id);
	}

}
