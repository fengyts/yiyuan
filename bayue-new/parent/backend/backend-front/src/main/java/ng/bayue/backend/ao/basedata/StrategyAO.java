package ng.bayue.backend.ao.basedata;

import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.service.StrategyService;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Service
public class StrategyAO {

	private static final Logger logger = LoggerFactory.getLogger(StrategyAO.class);
	
	@Autowired
	private StrategyService strategyService;
	
	public JSONArray strategyJsonData(){
		List<StrategyDO> list = strategyService.selectDynamic(new StrategyDO());
		JSONArray arr = new JSONArray();
		for(StrategyDO strategyDO : list){
			JSONObject obj = insertRowJson(strategyDO);
			arr.add(obj);
		}
		return arr;
	}
	
	private JSONObject insertRowJson(StrategyDO strategyDO){
		JSONObject o = new JSONObject();
		o.put("id", strategyDO.getId());
		o.put("module", strategyDO.getModule());
		o.put("title", strategyDO.getTitle());
		o.put("content", strategyDO.getContent());
		o.put("level", strategyDO.getLevel());
		o.put("status", strategyDO.getStatus());
		o.put("parentId", strategyDO.getParentId());
		o.put("remark", strategyDO.getRemark());
		o.put("expanded", false);
		return o;
		
	}
	
	public StrategyDO selectById(Long id){
		if(null == id){
			return null;
		}
		return strategyService.selectById(id);
	}
	
	/**
	 * <pre>
	 * 获取所有的顶级模块
	 * </pre>
	 *
	 * @return
	 */
	public List<StrategyDO> selectParents(){
		StrategyDO strategyDO = new StrategyDO();
		strategyDO.setLevel(1);
		List<StrategyDO> list = strategyService.selectDynamic(strategyDO);
		return list;
	}
	
	public ResultMessage addStrategy(StrategyDO strategyDO){
		if(null == strategyDO){
			return ResultMessage.validParamResult();
		}
		
		Long parentId = strategyDO.getParentId();
		if(null != parentId && 0 == parentId){//顶级模块
			if(StringUtils.isEmpty(strategyDO.getModule())){
				return new ResultMessage(ResultMessage.Failure,"顶级模块,名称不能为空");
			}
			strategyDO.setLevel(1);
			strategyDO.setTitle(strategyDO.getModule());
		}else{//攻略
			strategyDO.setLevel(2);
			StrategyDO strParent = selectById(parentId);
			strategyDO.setModule(strParent.getModule());
		}
		
		strategyDO.setCreateTime(new Date());
		strategyDO.setModifyTime(new Date());
		strategyDO.setCreateUserId(UserHandler.getUser().getId());
		strategyDO.setModifyUserId(UserHandler.getUser().getId());
		
		Long id = strategyService.insert(strategyDO);
		ResultMessage msg = new ResultMessage(id);
		return msg;
	}
	
	public ResultMessage updateStrategy(StrategyDO strategyDO){
		if(null == strategyDO){
			return ResultMessage.validParamResult();
		}
		
		strategyDO.setModifyTime(new Date());
		strategyDO.setModifyUserId(UserHandler.getUser().getId());
		
		int res = strategyService.update(strategyDO, false);
		return new ResultMessage();
	}
	
	
	
}
