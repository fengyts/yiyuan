package ng.bayue.backend.ao.item;

import ng.bayue.item.domain.ItemInfoDO;
import ng.bayue.item.service.ItemInfoService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemInfoAO {

	private static final Logger logger = LoggerFactory.getLogger(ItemInfoAO.class);

	@Autowired
	private ItemInfoService itemInfoService;
	
	public ItemInfoDO getInfoBySPU(String spu){
		if(StringUtils.isEmpty(spu)){
			return null;
		}
		ItemInfoDO infoDO = itemInfoService.selectBySPU(spu);
		return infoDO;
		
	}

}
