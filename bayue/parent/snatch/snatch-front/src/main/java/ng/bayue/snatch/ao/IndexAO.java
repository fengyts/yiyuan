package ng.bayue.snatch.ao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.dto.FrontCategoryViewDTO;
import ng.bayue.base.service.remote.RemoteBaseService;
import ng.bayue.fastdfs.ImageUrlUtil;
import ng.bayue.item.domain.CarouselDO;
import ng.bayue.item.service.ItemService;
import ng.bayue.promotion.dto.TopicItemDTO;
import ng.bayue.promotion.service.TopicExportService;

@Service
public class IndexAO {
	
	@Autowired
	private ImageUrlUtil imageUrlUtil;
	
	@Autowired
	private RemoteBaseService remoteBaseService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private TopicExportService topicExportService;
	
	public List<FrontCategoryViewDTO> loadFrontCategory(){
		
		List<FrontCategoryViewDTO> list = remoteBaseService.getFrontCategoryList();
		return list;
		
		/*String url = "http://www.meitun.com/loadfc?callback=";
		String dataStr = RequestUtils.doRequestReturnStr(url, null, "UTF-8");
		dataStr = dataStr.substring(1, dataStr.length() - 1);
		// System.out.println(dataStr);
		JSONObject object = JSONObject.parseObject(dataStr);
		String arrData = object.getString("data");
		List<FrontCategoryViewDTO> list = JSONArray.parseArray(arrData, FrontCategoryViewDTO.class);
		return list;*/
	}
	
	public List<CarouselDO> getAllCarousel(){
		List<CarouselDO> result = itemService.getAllCarousel();
		for(CarouselDO carousel : result){
			carousel.setPicture(imageUrlUtil.getFileFullUrl(carousel.getPicture()));
		}
		
		return result;
	}
	
	public List<TopicItemDTO> getHostItems(){
		List<TopicItemDTO> list = topicExportService.listItemHot(1, 10);
		return list;
	}

}
