package ng.bayue.backend.ao.basedata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.domain.SpecGroupDO;
import ng.bayue.base.domain.SpecGroupLinkDO;
import ng.bayue.base.service.SpecGroupLinkService;
import ng.bayue.base.service.SpecGroupService;
import ng.bayue.base.service.SpecService;
import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecGroupAO {
	
	private static final Logger logger = LoggerFactory.getLogger(SpecGroupAO.class);
	
	@Autowired
	private SpecGroupService specGroupService;
	@Autowired
	private SpecService specService;
	@Autowired
	private SpecGroupLinkService specGroupLinkService;
	
	public Page<SpecGroupDO> queryPageList(SpecGroupDO specGroupDO,Integer pageNo,Integer pageSize){
		Page<SpecGroupDO> page = specGroupService.queryPageListBySpecGroupDOAndStartPageSize(specGroupDO, pageNo, pageSize);
		
		return page;
	}
	
	public SpecGroupDO selectById(Long id){
		if(null == id){
			return null;
		}
		return specGroupService.selectById(id);
	}
	
	/**
	 * <pre>
	 * 根据规格组id获取该组下面关联的所有规格
	 * </pre>
	 *
	 * @param id
	 * @return
	 */
	public List<SpecDO> listSpecsByGroupIds(Long id){
		if(null == id){
			return null;
		}
		SpecGroupLinkDO specGroupLinkDO = new SpecGroupLinkDO();
		specGroupLinkDO.setGroupId(id);
		List<SpecGroupLinkDO> listLinks = specGroupLinkService.selectDynamic(specGroupLinkDO);
		if(CollectionUtils.isEmpty(listLinks)){
			logger.info("根据规格组id获取规格组规格关联信息失败!");
			return null;
		}
		List<Long> specIds = new ArrayList<Long>();
		for (SpecGroupLinkDO linkDO : listLinks) {
			specIds.add(linkDO.getSpecId());
		}
		List<SpecDO> listSpec = specService.selectByIds(specIds);
		return listSpec;
	}
	
	/**
	 * <pre>
	 * 解析specIds字符串为list
	 * </pre>
	 *
	 * @param specIds
	 * @return
	 */
	private List<Long> parseSpecIds(String specIds){
		if(StringUtils.isEmpty(specIds)){
			return null;
		}
		String[] temp = specIds.split(",");
		List<String> list = Arrays.asList(temp);
		List<Long> res = new ArrayList<Long>();
		for (String str : list) {
			res.add(Long.valueOf(str));
		}
		return res;
	}
	
	public ResultMessage saveSepcGroup(SpecGroupDO specGroupDO,String specIds){
		if(StringUtils.isEmpty(specIds)){
			return ResultMessage.validParameterNull("specIds");
		}
		String name = specGroupDO.getName();
		String alias = specGroupDO.getAlias();
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(alias)){
			return ResultMessage.validParameterNull("name","alias");
		}
		if(validSpecGroupAlias(specGroupDO)){
			return ResultMessage.validIsExist();
		}
		
		List<Long> temp = parseSpecIds(specIds);
		
		specGroupDO.setCreateTime(new Date());
		specGroupDO.setModifyTime(new Date());
		int res = specGroupService.insertSpecGroupAndLink(specGroupDO, temp);
		
		return new ResultMessage();
	}
	
	public ResultMessage updateSpecGroup(SpecGroupDO specGroupDO){
		
		return new ResultMessage();
	}
	
	/**
	 * <pre>
	 * 校验别名是否存在，true:存在，false：不存在
	 * </pre>
	 *
	 * @param specGroupDO
	 * @return
	 */
	public boolean validSpecGroupAlias(SpecGroupDO specGroupDO){
		String alias = specGroupDO.getAlias();
		if(StringUtils.isEmpty(alias)){
			return false;
		}
		SpecGroupDO specGroupValid = new SpecGroupDO();
		specGroupValid.setAlias(alias);
		List<SpecGroupDO> list = specGroupService.selectDynamic(specGroupValid);
		if(CollectionUtils.isNotEmpty(list)){
			return true;
		}
		return false;
	}
	
	public List<SpecGroupDO> listByGroupIds(List<Long> groupIds){
		if(null == groupIds){
			return null;
		}
		return specGroupService.selectByIds(groupIds);
	}

}
