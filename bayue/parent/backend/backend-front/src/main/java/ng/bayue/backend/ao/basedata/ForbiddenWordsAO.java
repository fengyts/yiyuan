package ng.bayue.backend.ao.basedata;

import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.backend.util.UserHandler;
import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.service.ForbiddenWordsService;
import ng.bayue.util.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ForbiddenWordsAO {
	
	private static final Logger logger = LoggerFactory.getLogger(ForbiddenWordsAO.class);
	
	@Autowired
	private ForbiddenWordsService forbiddenWordsService;
	
	
	public Page<ForbiddenWordsDO> pageQueryList(ForbiddenWordsDO forbiddenWordsDO,Integer pageNo,Integer pageSize){
		Page<ForbiddenWordsDO> page = forbiddenWordsService.queryPageListByForbiddenWordsDOAndStartPageSize(forbiddenWordsDO, pageNo, pageSize);
		
		return page;
		
	}
	
	public ForbiddenWordsDO selectById(Long id){
		if(null == id){return null;}
		return forbiddenWordsService.selectById(id);
	}
	
	public ResultMessage addForbiddenWords(ForbiddenWordsDO forbiddenWordsDO){
		if(null == forbiddenWordsDO){
			return new ResultMessage(ResultMessage.Failure,Messages.ParameterNull);
		}
		String words = forbiddenWordsDO.getWords();
		if(StringUtils.isEmpty(words)){
			return new ResultMessage(ResultMessage.Failure,Messages.parameterErrMsgs("words"));
		}
		
		ForbiddenWordsDO fwTmp = new ForbiddenWordsDO();
		fwTmp.setWords(words);
		List<ForbiddenWordsDO> list = forbiddenWordsService.findSameForbiddenWords(fwTmp);
		if(CollectionUtils.isNotEmpty(list)){
			return new ResultMessage(ResultMessage.Failure,Messages.IsExist);
		}
		
		Date date = new Date();
		forbiddenWordsDO.setCreatedTime(date);
		forbiddenWordsDO.setModifyTime(date);
		forbiddenWordsDO.setType(1);
		Long userId = UserHandler.getUser().getId();
		Long id = forbiddenWordsService.insert(forbiddenWordsDO, userId);
		
		forbiddenWordsDO.setCode(id.toString());
		forbiddenWordsService.update(forbiddenWordsDO, false, userId);
		
		return new ResultMessage();
		
	}
	
	public ResultMessage updateForbiddenWords(ForbiddenWordsDO forbiddenWordsDO){
		if(null == forbiddenWordsDO){
			return new ResultMessage(ResultMessage.Failure,Messages.ParameterNull);
		}
		forbiddenWordsDO.setModifyTime(new Date());
		int res = forbiddenWordsService.update(forbiddenWordsDO, false, UserHandler.getUser().getId());
		
		if(1 == res){
			return new ResultMessage();
		}else{
			return new ResultMessage(ResultMessage.Failure,Messages.HandleFailure);
		}
		
	}
	
	
}
