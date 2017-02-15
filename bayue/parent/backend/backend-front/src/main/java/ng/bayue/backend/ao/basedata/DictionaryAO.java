package ng.bayue.backend.ao.basedata;

import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.DictionaryDO;
import ng.bayue.base.service.DictionaryService;
import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DictionaryAO {

	private static final Logger logger = LoggerFactory.getLogger(DictionaryAO.class);

	@Autowired
	private DictionaryService dictionaryService;

	public Page<DictionaryDO> pageQueryList(DictionaryDO dictionaryDO,Integer pageNo, Integer pageSize) {
		Page<DictionaryDO> page = dictionaryService
				.queryPageListByDictionaryDOAndStartPageSize(dictionaryDO,pageNo, pageSize);
		return page;
	}
	
	public DictionaryDO selectById(Long id){
		if(null == id){
			return null;
		}
		DictionaryDO dictionaryDO = dictionaryService.selectById(id);
		return dictionaryDO;
		
	}
	
	public List<DictionaryDO> selectDynamic(DictionaryDO dictionaryDO){
		return dictionaryService.selectDynamic(dictionaryDO);
	}
	
	/**
	 * <pre>
	 * 获取数据字典所有code类别
	 * </pre>
	 *
	 * @return
	 */
	public List<DictionaryDO> listAllCode(){
		return dictionaryService.listAllCode();
	}
	
	public ResultMessage addDictionary(DictionaryDO dictionaryDO){
		if(null == dictionaryDO){
			return ResultMessage.validParamResult();
		}
		String name = dictionaryDO.getName();
		String code = dictionaryDO.getCode();
		if(StringUtils.isEmpty(code) || StringUtils.isEmpty(name)){
			return ResultMessage.validParameterNull("name","code");
		}
		List<DictionaryDO> list = checkDuplication(name, code);
		
		if(CollectionUtils.isNotEmpty(list)){
			logger.info("数据字典添加失败,名称已经存在：{}",name);
			return ResultMessage.validIsExist();
		}
		
		dictionaryDO.setCreateTime(new Date());
		dictionaryDO.setModifyTime(new Date());
		Long id = dictionaryService.insert(dictionaryDO);
		if(null == id || id <= 0L){
			return new ResultMessage(ResultMessage.Failure,Messages.HandleFailure);
		}
		
		Integer sortNo = dictionaryDO.getSortNo();
		if(null == sortNo){
			dictionaryDO.setId(id);
			dictionaryDO.setSortNo(id.intValue());
			updateDictionary(dictionaryDO);
		}
		
		return new ResultMessage();
	}

	/**
	 * <pre>
	 * name，code重复性校验
	 * </pre>
	 *
	 * @param name
	 * @param code
	 * @return
	 */
	private List<DictionaryDO> checkDuplication(String name, String code) {
		DictionaryDO dictionaryDOCheck = new DictionaryDO();
		dictionaryDOCheck.setName(name);
		dictionaryDOCheck.setCode(code);
		List<DictionaryDO> list = selectDynamic(dictionaryDOCheck);
		return list;
	}
	
	public ResultMessage updateDictionary(DictionaryDO dictionaryDO){
		if(null == dictionaryDO){
			return ResultMessage.validParamResult();
		}
		Long id = dictionaryDO.getId();
		String name = dictionaryDO.getName();
		String code = dictionaryDO.getCode();
		if(StringUtils.isEmpty(name) || StringUtils.isEmpty(code)){
			return ResultMessage.validParameterNull("name","code");
		}
		
		DictionaryDO dicCheck = selectById(id);
		if(null == dicCheck){
			return new ResultMessage(ResultMessage.Failure,Messages.ServerInnerError);
		}
		if(!(name.equals(dicCheck.getName()) && code.equals(dicCheck.getCode()))){
			List<DictionaryDO> list = checkDuplication(name,code);
			if(CollectionUtils.isNotEmpty(list)){
				logger.info("数据字典添加失败,名称已经存在：{}",name);
				return ResultMessage.validIsExist();
			}
		}
		
		dictionaryDO.setModifyTime(new Date());
		int res = dictionaryService.update(dictionaryDO, false);
		if(res <= 0){
			return new ResultMessage(ResultMessage.Failure,Messages.HandleFailure);
		}
		
		return new ResultMessage();
	}
	

}
