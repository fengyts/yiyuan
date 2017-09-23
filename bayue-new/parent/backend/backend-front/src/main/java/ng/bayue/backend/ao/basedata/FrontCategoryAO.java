package ng.bayue.backend.ao.basedata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.constant.CategoryConstant;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.domain.FrontCategoryDO;
import ng.bayue.base.dto.FrontCategoryDTO;
import ng.bayue.base.service.FrontCategoryService;
import ng.bayue.constant.CommonConstant;

@Service
public class FrontCategoryAO {

	private static final Logger logger = LoggerFactory.getLogger(FrontCategoryAO.class);

	@Autowired
	private FrontCategoryService frontCategoryService;
	
	public JSONObject getFrontCategoryJSON(){
		JSONObject obj = new JSONObject();
		List<FrontCategoryDTO> listAll = frontCategoryService.getAllFrontCategoryAndLink();
		if(CollectionUtils.isEmpty(listAll)){
			return obj;
		}
		
		/*
		Map<Long, List<FrontCategoryDTO>> map = new HashMap<Long, List<FrontCategoryDTO>>();
		Map<Long, FrontCategoryDTO> mapFirst = new HashMap<Long, FrontCategoryDTO>();
		*/
		JSONArray rows = new JSONArray();
		for(FrontCategoryDTO dto : listAll){
			int level = dto.getLevel();
			long id = dto.getId();
			if(2 == level){
				continue;
			}
			//JSONObject row = insertJSONObj(dto);
			//row.put("isLeaf", false);//是否叶子节点
			//rows.add(row);
			/*List<FrontCategoryDTO> leaves = new ArrayList<FrontCategoryDTO>();*/
			List<JSONObject> childs = new ArrayList<JSONObject>();
			for(FrontCategoryDTO dto1 : listAll){
				long cateId = dto1.getParentId();
				int levelSec = dto1.getLevel();
				if(2 == levelSec && id == cateId){
					/*leaves.add(dto1);*/
					JSONObject row2 = insertJSONObj(dto1);
					row2.put("isLeaf", true);//是否叶子节点
					childs.add(row2);
					//rows.add(row2);
				}
			}
			JSONObject row = insertJSONObj(dto);
			if(childs.size() > 0){
				row.put("isLeaf", false);//是否叶子节点
			}else {
				row.put("isLeaf", true);
			}
			rows.add(row);
			rows.addAll(childs);
			/*if(1 == level){
				mapFirst.put(id, dto);
				map.put(id, leaves);
			}*/
		}
		
		/*JSONArray rows = new JSONArray();
		for(Map.Entry<Long, List<FrontCategoryDTO>> entry : map.entrySet()){
			long id = entry.getKey();
			FrontCategoryDTO fcFir = mapFirst.get(id);
			JSONObject row = insertJSONObj(fcFir);
			row.put("isLeaf", false);//是否叶子节点
			rows.add(row);
			List<FrontCategoryDTO> fcSec = entry.getValue();
			for(FrontCategoryDTO dto : fcSec){
				JSONObject rowSec = insertJSONObj(dto);
				rowSec.put("isLeaf", true);
				rows.add(rowSec);
			}
		}*/
		
		obj.put("rows", rows);
		obj.put("records", rows.size());
		obj.put("page", 1);
		obj.put("total", 1);
		
		return obj;
	}
	
	private JSONObject insertJSONObj (FrontCategoryDTO categoryDTO) {
		JSONObject obj = new JSONObject();
		// 注意：parentId的值必须要加上双引号才行(即必须是字符串形式),否则expanded 会无效,比如格式必须这样："parentId":"1",而不能这样："parentId":1
		obj.put("parentId", categoryDTO.getParentId().toString());
		obj.put("id", categoryDTO.getId());
		obj.put("name", categoryDTO.getName());
		obj.put("code", categoryDTO.getCode());
		obj.put("level", categoryDTO.getLevel());
		obj.put("status", categoryDTO.getStatus());
		obj.put("logUrl", categoryDTO.getLogoUrl());
		obj.put("sort", categoryDTO.getSort());
		
		obj.put("linkUrlPc", categoryDTO.getLinkUrlPc());
		obj.put("expanded", false);
		return obj;
		
	}
	
	public FrontCategoryDTO selectById(Long id){
		return frontCategoryService.selectFCAndLinkByFCID(id);
	}
	
	public List<FrontCategoryDO> selectAllTop(){
		FrontCategoryDO fcdo = new FrontCategoryDO();
		fcdo.setStatus(CommonConstant.STATUS.TRUE);
		fcdo.setLevel(CategoryConstant.LEVEL.LARGE);
		List<FrontCategoryDO> list = frontCategoryService.selectDynamic(fcdo);
		return list;
	}
	
	/**
	 * <pre>
	 * 同级下重名校验,存在重复：true,不存在重复：false
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 */
	private boolean checkDuplicationName(FrontCategoryDO fcate) {
		FrontCategoryDO cate = new FrontCategoryDO();
		cate.setParentId(fcate.getParentId());
		cate.setName(fcate.getName());
		List<FrontCategoryDO> list = frontCategoryService.selectDynamic(cate);
		return CollectionUtils.isEmpty(list) ? false : true;
	}

	public ResultMessage save(FrontCategoryDTO fcdto) {
		if(null == fcdto){
			return ResultMessage.validParamResult();
		}
		FrontCategoryDO fcate = new FrontCategoryDO();
		fcate.setParentId(fcdto.getParentId());
		fcate.setName(fcdto.getName());
		if(checkDuplicationName(fcate)){
			return ResultMessage.validIsExist();
		}
		
		Long userId = UserHandler.getUser().getId();
		int res = frontCategoryService.insertFrontCategoryAndLinked(fcdto, userId);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	public ResultMessage update(FrontCategoryDTO fcdto) {
		if(null == fcdto){
			return ResultMessage.serverInnerError();
		}
		
		Long id = fcdto.getId();
		FrontCategoryDO fcateDb = frontCategoryService.selectById(id);
		FrontCategoryDO fcateCheck = new FrontCategoryDO();
		fcateCheck.setId(id);
		fcateCheck.setName(fcdto.getName());
		if(fcateDb != null && !fcateDb.getName().equals(fcdto.getName()) && checkDuplicationName(fcateCheck)){
			return ResultMessage.validIsExist();
		}
		
		// 父类修改状态为无效时其子类都要修改为无效
		if(CategoryConstant.LEVEL.LARGE == fcdto.getLevel()){
			List<FrontCategoryDO> listChilds = selectListByParentId(id);
			if(listChilds.size() > 0 && fcateDb.getStatus() && !fcdto.getStatus() ){
				// 批量修改子类状态为无效的
				//功能待晚上
				//....
			}
		}
		
		Long userId = UserHandler.getUser().getId();
		int res = frontCategoryService.updateFrontCategoryAndLinked(fcdto, userId);
		if(res < 1){
			return ResultMessage.serverInnerError();
		}
		return new ResultMessage();
	}
	
	public List<FrontCategoryDO> selectListByParentId(Long parentId){
		if(null == parentId){
			return null;
		}
		FrontCategoryDO fcate = new FrontCategoryDO();
		fcate.setParentId(parentId);
		fcate.setStatus(CategoryConstant.STATUS.TRUE);
		
		return frontCategoryService.selectDynamic(fcate);
		
	}

}
