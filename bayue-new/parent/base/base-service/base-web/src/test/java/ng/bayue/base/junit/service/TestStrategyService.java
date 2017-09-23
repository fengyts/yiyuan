package ng.bayue.base.junit.service;

import java.util.List;

import ng.bayue.base.domain.StrategyDO;
import ng.bayue.base.service.StrategyService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-beans.xml"})
public class TestStrategyService {
	
	@Autowired
	private StrategyService strategyService;
	
	@Test
	public void test(){
		
		JSONObject o = strategyJsonData();
		
		System.out.println(o.toJSONString());
		
	}
	
	public JSONObject strategyJsonData(){
		List<StrategyDO> list = strategyService.selectDynamic(new StrategyDO());
		JSONArray arr = new JSONArray();
		for(StrategyDO strategyDO : list){
			JSONObject obj = insertRowJson(strategyDO);
			arr.add(obj);
		}
		
		JSONObject o = new JSONObject();
		o.put("rows",arr);
		return o;
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
		o.put("expanded", false);
		return o;
		
	}
	
	

}
