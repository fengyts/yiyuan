package ng.bayue.base.service.impl.remote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.base.dto.IndexFrontCategoryDTO;
import ng.bayue.base.service.remote.RemoteBaseService;
import ng.bayue.constant.RedisCacheTimeConstant;
import ng.bayue.service.RedisCacheService;

@Service(value = "remoteBaseService")
public class RemoteBaseServiceImpl implements RemoteBaseService {
	
	private Logger logger = LoggerFactory.getLogger(RemoteBaseServiceImpl.class);
	
	@Autowired
	private RedisCacheService redisCacheService;

	@Override
	public List<IndexFrontCategoryDTO> getFrontCategoryList() {
		Object object = redisCacheService.getRedisCache("frontCategory_pc");
		
		if(object != null) return (List<IndexFrontCategoryDTO>)object;
		
		List<IndexFrontCategoryDTO> list = new ArrayList<IndexFrontCategoryDTO>();
		//查询所有分类
		List<FrontCategoryDO> all = getAllFrontCategory();
		if(all == null || all.size() <= 0){
			logger.info("查询前台分类为空");
			return list;
		}
		
		//查询固定url链接
		Map<Long,Map<String,String>> urlMap = getUrlLinksMap();
		Map<Long,List<IndexFrontCategoryDTO>> map = getAllFrontCategoryMap(list, all, urlMap,FrontCategoryConstant.FC_PC,FrontCategoryConstant.SCREEN_PC);
		
		//设置childs
		for(IndexFrontCategoryDTO dto : list){
			List<IndexFrontCategoryDTO> childs = map.get(dto.getId());
			if(CollectionUtils.isEmpty(childs)) {
				continue;
			}
			List<IndexFrontCategoryDTO> allThirdList = new ArrayList<IndexFrontCategoryDTO>();
			for(IndexFrontCategoryDTO child : childs){
				List<IndexFrontCategoryDTO> thirdList = map.get(child.getId());
				if(CollectionUtils.isNotEmpty(thirdList)) {
					allThirdList.addAll(thirdList);
				}
			}
			dto.setChilds(allThirdList);
		}
		
		
		try {
			redisCacheService.setRedisCache("frontCategory_pc", list, RedisCacheTimeConstant.HALF_MONTH);
		} catch (Exception e) {
			logger.error("设置前台分类缓存失败 ",e);
		}
		
		
		return list;
	}
	

}
