package ng.bayue.snatch.ao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.util.net.RequestUtils;

@Service
public class IndexAO {
	
	public List<FrontCategoryDTO> loadFrontCategory(){
		String url = "http://www.meitun.com/loadfc?callback=";
		String dataStr = RequestUtils.doRequestReturnStr(url, null, "UTF-8");
		dataStr = dataStr.substring(1, dataStr.length() - 1);
		// System.out.println(dataStr);
		JSONObject object = JSONObject.parseObject(dataStr);
		String arrData = object.getString("data");
		List<FrontCategoryDTO> list = JSONArray.parseArray(arrData, FrontCategoryDTO.class);
		return list;
	}

}
