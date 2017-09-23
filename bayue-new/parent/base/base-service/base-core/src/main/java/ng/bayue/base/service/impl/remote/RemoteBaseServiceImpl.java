package ng.bayue.base.service.impl.remote;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.json.JSONObject;
import ng.bayue.base.constant.BaseRedisKeyConstant;
import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.domain.FrontCategoryLinkDO;
import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.base.dto.FrontCategoryViewDTO;
import ng.bayue.base.persist.dao.FrontCategoryDAO;
import ng.bayue.base.persist.dao.FrontCategoryLinkDAO;
import ng.bayue.base.service.remote.RemoteBaseService;
import ng.bayue.constant.CommonConstant;
import ng.bayue.constant.RedisCacheTimeConstant;
import ng.bayue.exception.CommonDAOException;
import ng.bayue.service.RedisCacheService;

@Service(value = "remoteBaseService")
public class RemoteBaseServiceImpl implements RemoteBaseService {

	private final Logger logger = LoggerFactory.getLogger(RemoteBaseServiceImpl.class);

	@Autowired
	private RedisCacheService redisCacheService;
	@Autowired
	private FrontCategoryDAO frontCategoryDAO;
	@Autowired
	private FrontCategoryLinkDAO frontCategoryLinkDAO;
	
	@Override
	public List<FrontCategoryViewDTO> getFrontCategoryList() {
		List<FrontCategoryViewDTO> result = (List<FrontCategoryViewDTO>) redisCacheService.getRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F);
		if(CollectionUtils.isNotEmpty(result)){
			return result;
		}
		result = new ArrayList<FrontCategoryViewDTO>();
		try {
			FrontCategoryDO fcateDO = new FrontCategoryDO();
			fcateDO.setStatus(CommonConstant.STATUS.TRUE);
			fcateDO.setIsPublish(Boolean.TRUE);
			List<FrontCategoryDO> listFCate = frontCategoryDAO.selectDynamic(fcateDO);
			FrontCategoryLinkDO fcateLinkDO = new FrontCategoryLinkDO();
			List<FrontCategoryLinkDO> listFCLinks = frontCategoryLinkDAO.selectDynamic(fcateLinkDO);
			for(FrontCategoryDO fcate : listFCate){
				long cateId = fcate.getId();
				int level = fcate.getLevel();
				if(2 == level){
					continue;
				}
				
				FrontCategoryViewDTO fcateView = new FrontCategoryViewDTO();
				fcateView.setId(cateId);
				fcateView.setIsHighlight(fcate.getIsHighlight());
				fcateView.setLogoUrl(fcate.getLogoUrl());
				fcateView.setSeq(fcate.getSort());
				fcateView.setIsUrlLink(false);
				
				fcateView.setLevel(fcate.getLevel());
				fcateView.setName(fcate.getName());
				
				List<FrontCategoryViewDTO> childs = new ArrayList<FrontCategoryViewDTO>();
				for(FrontCategoryDO fcate1 : listFCate){
					long parentId = fcate1.getParentId();
					if(cateId == parentId){
						FrontCategoryViewDTO child = new FrontCategoryViewDTO();
						for(FrontCategoryLinkDO linkDO : listFCLinks){
							child.setId(fcate1.getId());
							child.setIsHighlight(fcate1.getIsHighlight());
							child.setLogoUrl(fcate1.getLogoUrl());
							child.setIsUrlLink(false);
							child.setPcUrlLink(linkDO.getLinkUrlPc());
							
							child.setLevel(fcate1.getLevel());
							child.setName(fcate1.getName());
							continue;
						}
						childs.add(child);
					}
				}
				fcateView.setChilds(childs);
				result.add(fcateView);
				
			}
			
			if(CollectionUtils.isNotEmpty(result)){
				redisCacheService.setRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F, result, RedisCacheTimeConstant.WEEK);
			}
			return result;
			
		} catch (CommonDAOException e) {
			logger.error("", e);
		} 
		return result;
	}

//	@Override
//	public List<FrontCategoryViewDTO> getFrontCategoryList() {
//		Object object = redisCacheService.getRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F);
//
//		if (object != null)
//			return (List<FrontCategoryViewDTO>) object;
//
//		List<FrontCategoryViewDTO> list = new ArrayList<FrontCategoryViewDTO>();
//		// 查询所有分类
//		List<FrontCategoryDO> all = getAllFrontCategory();
//		if (all == null || all.size() <= 0) {
//			logger.info("查询前台分类为空");
//			return list;
//		}
//
//		// 查询固定url链接
//		Map<Long, Map<String, String>> urlMap = getUrlLinksMap();
//		Map<Long, List<FrontCategoryViewDTO>> map = getAllFrontCategoryMap(list, all, urlMap, "pc", 10);
//
//		// 设置childs
//		for (FrontCategoryViewDTO dto : list) {
//			List<FrontCategoryViewDTO> childs = map.get(dto.getId());
//			if (CollectionUtils.isEmpty(childs)) {
//				continue;
//			}
//			List<FrontCategoryViewDTO> allThirdList = new ArrayList<FrontCategoryViewDTO>();
//			for (FrontCategoryViewDTO child : childs) {
//				List<FrontCategoryViewDTO> thirdList = map.get(child.getId());
//				if (CollectionUtils.isNotEmpty(thirdList)) {
//					allThirdList.addAll(thirdList);
//				}
//			}
//			dto.setChilds(allThirdList);
//		}
//
//		try {
//			redisCacheService.setRedisCache(BaseRedisKeyConstant.BASE_FRONT_CATEGORY_ALL_F, list, RedisCacheTimeConstant.WEEK);
//		} catch (Exception e) {
//			logger.error("设置前台分类缓存失败 ", e);
//		}
//
//		return list;
//	}
//
//	private List<FrontCategoryDO> getAllFrontCategory() {
//		FrontCategoryDO fcateDO = new FrontCategoryDO();
//		fcateDO.setStatus(CommonConstant.STATUS.TRUE);
//		fcateDO.setIsPublish(Boolean.TRUE);
//		try {
//			List<FrontCategoryDO> listFCate = frontCategoryDAO.selectDynamic(fcateDO);
//			return listFCate;
//		} catch (DAOException e) {
//			logger.error("", e);
//		}
//		return null;
//	}
//
//	private Map<Long, Map<String, String>> getUrlLinksMap() {
//		List<FrontCategoryLinkDO> list = getUrlLinks();
//		if(list == null || list.size() <= 0) return null;
//		Map<Long,Map<String,String>> map = new HashMap<Long,Map<String,String>>();
//		Map<String,String> tmp;
//		for(FrontCategoryLinkDO link : list){
//			tmp = new HashMap<String,String>();
//			tmp.put("pclink", link.getLinkUrlPc());
//			tmp.put("applink", link.getLinkUrlApp());
//			tmp.put("waplink", link.getLinkUrlWap());
//			map.put(link.getFrontCategoryId(), tmp);
//		}
//		return map;
//	}
//	
//	/**
//	 * 查询所有固定url跳转的跳转方式
//	 * @return
//	 */
//	private List<FrontCategoryLinkDO> getUrlLinks(){
//		FrontCategoryLinkDO frontCategoryLinkDO = new FrontCategoryLinkDO();
//		frontCategoryLinkDO.setStatus(false);
//		try {
//			return frontCategoryLinkDAO.selectDynamic(frontCategoryLinkDO);
//		} catch (DAOException e) {
//			logger.error("", e);
//		}
//		return null;
//	}
//
//	private Map<Long, List<FrontCategoryViewDTO>> getAllFrontCategoryMap(List<FrontCategoryViewDTO> list,
//			List<FrontCategoryDO> all, Map<Long, Map<String, String>> urlMap, String pc, int screen) {
//		Map<Long,List<FrontCategoryViewDTO>> map = new HashMap<Long,List<FrontCategoryViewDTO>>();
//		FrontCategoryDO tmp;
//		FrontCategoryViewDTO dto;
//		Map<String,String> urlLinks;
//		List<FrontCategoryViewDTO> childs;
//		int screenType = 1024;
//		for(int i = 0, n = all.size(); i < n; i++){
//			tmp = all.get(i);
//			dto = buildIndexFrontCategoryDTO(tmp,screenType);
//			urlLinks = urlMap != null ? urlMap.get(tmp.getId()) : null;
//			if(urlLinks == null) {
//				dto.setIsUrlLink(false);
//			}else{
//				dto.setIsUrlLink(true);
//				dto.setPcUrlLink(urlLinks.get("pclink"));
//				dto.setAppUrlLink(urlLinks.get("applink"));
//				dto.setWapUrlLink(urlLinks.get("waplink"));
//			}
//			if(tmp.getLevel() != null && 1 == tmp.getLevel().intValue()){
//				list.add(dto);
//			}else{
//				childs = map.get(tmp.getParentId());
//				if(childs == null) childs = new ArrayList<FrontCategoryViewDTO>();
//				childs.add(dto);
//				map.put(tmp.getParentId(), childs);
//			}
//		}
//		return map;
//	}
//	
//	/**
//	 * 
//	 * 创建IndexFrontCategoryDTO对象
//	 * 
//	 * @param frontCategoryDO
//	 * @return
//	 */
//	protected FrontCategoryViewDTO buildIndexFrontCategoryDTO(FrontCategoryDO frontCategoryDO,int screenType){
//		FrontCategoryViewDTO dto = new FrontCategoryViewDTO();
//		dto.setId(frontCategoryDO.getId());
//		dto.setIsHighlight(frontCategoryDO.getIsHighlight());
//		dto.setName(frontCategoryDO.getName());
//		dto.setLevel(frontCategoryDO.getLevel());
//		dto.setSort(frontCategoryDO.getSort());
//		/*if(StringUtils.isBlank(type) || StringUtils.isBlank(frontCategoryDO.getLogoUrl()) || "{}".equals(frontCategoryDO.getLogoUrl())){
//			dto.setLogoUrl("");
//			return dto;
//		}*/
//		
//		if(screenType <= 0 || StringUtils.isBlank(frontCategoryDO.getLogoUrl()) || "{}".equals(frontCategoryDO.getLogoUrl())){
//			dto.setLogoUrl("");
//			return dto;
//		}
//		String logoUrl = frontCategoryDO.getLogoUrl();
//		JSONObject json = JSONObject.fromObject(logoUrl);
//		
//		/*if(FrontCategoryConstant.FC_APP.equals(type)){
//			dto.setLogoUrl(json.get("appLogo") == null ? "" : json.get("appLogo").toString());
//		} else if(FrontCategoryConstant.FC_WAP.equals(type))
//			dto.setLogoUrl(json.get("wapLogo") == null ? "" : json.get("wapLogo").toString());
//		else
//			dto.setLogoUrl(json.get("pcLogo") == null ? "" : dfsDomainUtil.getFileFullUrl(json.get("pcLogo").toString()));*/
//		
//		dto.setLogoUrl((String) json.get("pcLogo"));
//		
//		return dto;
//	}

}
