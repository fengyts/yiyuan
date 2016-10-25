package ng.bayue.backend.ao.basedata;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.constant.CategoryConstant;
import ng.bayue.base.domain.CategoryDO;
import ng.bayue.base.service.CategoryService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;


@Service
public class CategoryAO {

	private final static Logger logger = LoggerFactory.getLogger(CategoryAO.class);
	
	@Autowired
	private CategoryService categoryService;
	
	
	public Page<CategoryDO> pageQueryList(CategoryDO categoryDO,Integer pageNo,Integer pageSize){
		Page<CategoryDO> page = categoryService.queryPageListByCategoryDOAndStartPageSize(categoryDO, pageNo, pageSize);
		
		return page;
		
	}
	
	public JSONObject categoryJsonData(){
		List<CategoryDO> listAll = categoryService.selectDynamic(new CategoryDO());
		if(CollectionUtils.isEmpty(listAll)){return null;}
		
		Map<Long,List<CategoryDO>> map = new HashMap<Long,List<CategoryDO>>();//key：第一级别id，value:该id所有子项
		Map<Long,CategoryDO> mapFirst = new HashMap<Long,CategoryDO>();//第一级别
		for(CategoryDO categoryDO : listAll){ //外层循环只获取第一级别
			int level1 = categoryDO.getLevel();
			if(CategoryConstant.LEVEL.LARGE == level1){
				mapFirst.put(categoryDO.getId(), categoryDO);
			}else{
				continue;
			}
			long id = categoryDO.getId();
			List<CategoryDO> listSec = new ArrayList<CategoryDO>();
			for(CategoryDO cate : listAll){
				long parentId = cate.getParentId();
				int level2 = cate.getLevel();
				if(CategoryConstant.LEVEL.LARGE == level2){continue;}
				if(id == parentId){
					listSec.add(cate);
				}
			}
			map.put(id, listSec);
		}
		
		JSONArray rows = new JSONArray();
		for(Map.Entry<Long, List<CategoryDO>> entry : map.entrySet()){
			long idFir = entry.getKey();
			CategoryDO cateFir = mapFirst.get(idFir);
//			JSONObject row = JSONObject.parseObject(JSONObject.toJSONString(cateFir));
			JSONObject row = insertJSONObj(cateFir);
			List<CategoryDO> listSec = entry.getValue();
			if(CollectionUtils.isNotEmpty(listSec)){
				row.put("isLeaf", false);//是否叶子节点
//				row.put("expanded", false);//是否展开
				rows.add(row);
				for(CategoryDO cateSec : listSec){
//					JSONObject rowSec = JSONObject.parseObject(JSONObject.toJSONString(cateSec));
					JSONObject rowSec = insertJSONObj(cateSec);
					rowSec.put("isLeaf", true);
//					rowSec.put("expanded", false);
					rows.add(rowSec);
				}
			}else{
				row.put("isLeaf", true);
//				row.put("expanded", false);
				rows.add(row);
			}
			
		}
		
		JSONObject obj = new JSONObject();
		obj.put("rows", rows);
		obj.put("records", rows.size());
		obj.put("page", 1);
		obj.put("total", 1);
		return obj;
	}
	
	private JSONObject insertJSONObj(CategoryDO categoryDO){
		/*JSONObject obj = new JSONObject();
		obj.put("id", categoryDO.getId());
		obj.put("name", categoryDO.getName());
		obj.put("code", categoryDO.getCode());
		obj.put("level", categoryDO.getLevel());
		obj.put("status", categoryDO.getStatus());
		obj.put("remark", categoryDO.getRemark());
		obj.put("parentId", categoryDO.getParentId());
		obj.put("expanded", false);
		return obj;*/
		
//		return parseJsonData(categoryDO);
		
		String str = "{\"parentId\":\"" + categoryDO.getParentId() + "\"}";
		JSONObject obj = JSONObject.parseObject(str);
		obj.put("id", categoryDO.getId());
		obj.put("name", categoryDO.getName());
		obj.put("code", categoryDO.getCode());
		obj.put("level", categoryDO.getLevel());
		obj.put("status", categoryDO.getStatus());
		obj.put("remark", categoryDO.getRemark());
		obj.put("expanded", false);
		return obj;
		
	}
	
//	private JSONObject parseJsonData(CategoryDO categoryDO){
////		StringBuffer strJson = new StringBuffer();
//		String str = "{" 
//		+ "\"id\""+":"+categoryDO.getId()+","
//		+ "\"name\""+":\""+categoryDO.getName()+"\"," 
//		+ "\"code\""+":"+categoryDO.getCode()+","
//		+ "\"level\""+":"+categoryDO.getLevel()+","
//		+ "\"status\""+":"+categoryDO.getStatus()+","
////		+ "\"remark\""+":\""+categoryDO.getRemark()+"\","
//		+ "\"parentId\""+":\""+categoryDO.getParentId()+"\","
//		+ "\"expanded\""+":"+false
//		+"}";
//		
//		JSONObject o = JSONObject.parseObject(str);
//		o.put("remark", categoryDO.getRemark());
//		return o;
//	}
	
//	public static void main(String[] args) {
//		CategoryDO cate = new CategoryDO();
//		cate.setId(1L);
//		cate.setName("测试");
//		cate.setCode("01");
//		cate.setLevel(1);
//		cate.setParentId(0L);
//		cate.setStatus(true);
//		cate.setRemark("remark");
//		JSONObject o = parseJsonData(cate);
//		System.out.println(o.toJSONString());
//	}
	
	public ResultMessage addCategory(CategoryDO categoryDO){
		if(null == categoryDO){
			return new ResultMessage(ResultMessage.Failure,Messages.ParameterNull);
		}
		//校验同级下是否已经存在类别
		if(checkDuplicationName(categoryDO)){
			return new ResultMessage(ResultMessage.Failure, Messages.IsExist);
		}
		
		String code = selectMaxCode(categoryDO);
		categoryDO.setCode(code);
		
		categoryDO.setCreateTime(new Date());
		categoryDO.setModifyTime(new Date());
		categoryDO.setCreateUserId(UserHandler.getUser().getId());
		categoryDO.setModifyUserId(UserHandler.getUser().getId());
		
		Long id = categoryService.insert(categoryDO);
		
		return new ResultMessage(id);
	}

	/**
	 * <pre>
	 * 同级下重名校验,存在重复：true,不存在重复：false
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 */
	private boolean checkDuplicationName(CategoryDO categoryDO) {
		CategoryDO cate = new CategoryDO();
		cate.setParentId(categoryDO.getParentId());
		cate.setName(categoryDO.getName());
		List<CategoryDO> list = selectList(cate);
		return CollectionUtils.isEmpty(list) ? false : true;
	}
	
	public List<CategoryDO> selectList(CategoryDO categoryDO){
		List<CategoryDO> list = categoryService.selectDynamic(categoryDO);
		return list;
		
	}
	
	public ResultMessage updateCategory(CategoryDO categoryDO,Boolean oldStatus){
		if(null == categoryDO) {
			return new ResultMessage(ResultMessage.Failure,Messages.ParameterError);
		}
		
		//修改名称时，校验重名
		CategoryDO checkCate = this.selectById(categoryDO.getId());
		if(!categoryDO.getName().equals(checkCate.getName())
				&& checkDuplicationName(checkCate)){
			return new ResultMessage(ResultMessage.Failure,Messages.IsExist);
		}
		
		//如果本来是无效的，则oldStatus取不到值为null
		if(CategoryConstant.LEVEL.LARGE == categoryDO.getLevel().intValue() 
				&& oldStatus && !categoryDO.getStatus()){//状态从有效改为无效，所有子类都修改为无效的
			CategoryDO cate = new CategoryDO();
			cate.setParentId(categoryDO.getId());
			List<CategoryDO> listChilds = selectList(cate);
			for(CategoryDO cateTmp : listChilds){
				cateTmp.setStatus(false);
			}
			updateCategoryBatch(listChilds);
		}
		
		categoryDO.setModifyTime(new Date());
		categoryDO.setModifyUserId(UserHandler.getUser().getId());
		
		int res = categoryService.update(categoryDO, false);
		
		return new ResultMessage();
	}
	
	public ResultMessage updateCategoryBatch(List<CategoryDO> list){
		if(CollectionUtils.isEmpty(list)){
			return ResultMessage.validParamResult();
		}
		categoryService.updateBatch(list);
		return new ResultMessage();
	}
	
	/**
	 * <pre>
	 * 获取同级别下最大的code值, 该返回值比最大code大1
	 * </pre>
	 *
	 * @param categoryDO
	 * @return
	 */
	public String selectMaxCode(CategoryDO categoryDO){
		return categoryService.selectMaxCodeDynamic(categoryDO);
	}
	
	public CategoryDO selectById(Long id){
		if(null == id){return null;}
		CategoryDO categoryDO = categoryService.selectById(id);
		return categoryDO;
	}
	
	/**
	 * <pre>
	 * 获取所有一级分类
	 * </pre>
	 *
	 * @return
	 */
	public List<CategoryDO> listFirst(){
		CategoryDO categoryDO = new CategoryDO();
		categoryDO.setLevel(CategoryConstant.LEVEL.LARGE);
		categoryDO.setParentId(0L);
		List<CategoryDO> listFir = selectList(categoryDO);
		return listFir;
	}
	
	/**
	 * <pre>
	 * 根据id获取该类别下所有子类
	 * </pre>
	 *
	 * @param id
	 * @return
	 */
	public List<CategoryDO> selectByParentId(Long id){
		if(null == id){
			logger.info("根据id获取所有子类异常，id不能为空");
			return null;
		}
		CategoryDO categoryDO = new CategoryDO();
		categoryDO.setParentId(id);
		List<CategoryDO> list = this.selectList(categoryDO);
		return list;
	}
	
}
