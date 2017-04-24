package ng.bayue.snatch.ao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.dto.FrontCategoryViewDTO;
import ng.bayue.base.service.remote.RemoteBaseService;

@Service
public class IndexAO {
	
	@Autowired
	private RemoteBaseService remoteBaseService;
	
	public List<FrontCategoryViewDTO> loadFrontCategory(){
		
		List<FrontCategoryViewDTO> list = remoteBaseService.getFrontCategoryList();
		return list;
		
		/*List<FrontCategoryViewDTO> list = new ArrayList<FrontCategoryViewDTO>();
		FrontCategoryViewDTO view = new FrontCategoryViewDTO();
		view.setId(1L);
		view.setLevel(1);
		view.setIsHighlight(true);
		view.setIsUrlLink(false);
		view.setName("分类1");
		view.setLogoUrl("");
		view.setSeq(1);
		FrontCategoryViewDTO viewC = new FrontCategoryViewDTO();
		viewC.setId(2L);
		viewC.setIsHighlight(true);
		viewC.setIsUrlLink(true);
		viewC.setLogoUrl("http://img04.meituncdn.com/group1/M00/1D/FA/wKgyOldFGzKALQkoAABBth0Pvy8309.jpg");
		viewC.setSeq(1);
		viewC.setName("其他");
		viewC.setLevel(2);
		viewC.setPcUrlLink("http://img01.meituncdn.com/group1/M00/F7/88/wKgyOlg4SDeAV0p5AAAnxReR6Yc013.jpg");
		List<FrontCategoryViewDTO> childs = new ArrayList<FrontCategoryViewDTO>();
		childs.add(viewC);
		view.setChilds(childs);
		list.add(view);
		
		return list;*/
		
		/*String url = "http://www.meitun.com/loadfc?callback=";
		String dataStr = RequestUtils.doRequestReturnStr(url, null, "UTF-8");
		dataStr = dataStr.substring(1, dataStr.length() - 1);
		// System.out.println(dataStr);
		JSONObject object = JSONObject.parseObject(dataStr);
		String arrData = object.getString("data");
		List<FrontCategoryViewDTO> list = JSONArray.parseArray(arrData, FrontCategoryViewDTO.class);
		return list;*/
	}

}
