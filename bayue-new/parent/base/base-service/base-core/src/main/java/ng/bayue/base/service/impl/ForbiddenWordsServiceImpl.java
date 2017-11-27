package ng.bayue.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import ng.bayue.base.constant.BaseRedisCacheConstant;
import ng.bayue.base.domain.ForbiddenWordsDO;
import ng.bayue.base.exception.DAOException;
import ng.bayue.base.exception.ServiceException;
import ng.bayue.base.persist.dao.ForbiddenWordsDAO;
import ng.bayue.base.service.ForbiddenWordsService;
import ng.bayue.common.Page;
import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.redis.RedisCacheUtil;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "forbiddenWordsService")
public class ForbiddenWordsServiceImpl implements ForbiddenWordsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ForbiddenWordsServiceImpl.class);

	@Autowired
	private ForbiddenWordsDAO forbiddenWordsDAO;
//	@Autowired
//	private BaseRedisCacheService baseRedisCacheService;
//	@Autowired
//	private CommonLogService commonLogService;
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Long insert(ForbiddenWordsDO forbiddenWordsDO,Long userId)  throws ServiceException {
		
		 ForbiddenWordsDO queryForbiddenWords  = new ForbiddenWordsDO();
		 queryForbiddenWords.setStatus(true);
		try {
			 Long id =  forbiddenWordsDAO.insert(forbiddenWordsDO);		
			 forbiddenWordsDO.setId(id);
//			 commonLogService.logsOfInsert(forbiddenWordsDO, id,userId);
//			 baseRedisCacheService.setDatatoRedisCache(Config.FORBIDDEN_WORDS+id, forbiddenWordsDO);
			
			 List<ForbiddenWordsDO> list = forbiddenWordsDAO.selectDynamic(queryForbiddenWords);
//			 baseRedisCacheService.setDatatoRedisCache(Config.ALL_EFFECTIVE_FORBIDDEN_WORDS, list);
			 RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS, list, RedisCacheTimeConstant.HALF_HOUR);
             return id;
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public int update(ForbiddenWordsDO forbiddenWordsDO, boolean isAllField,Long userId)  throws ServiceException {
		 Integer resultID=null;
		 ForbiddenWordsDO forbiddenWords=null;
		 List<ForbiddenWordsDO> list =null;
		 ForbiddenWordsDO queryForbiddenWords  = new ForbiddenWordsDO();
		 queryForbiddenWords.setStatus(true);
		try {
			if (isAllField) {
//				  commonLogService.logsOfUpdate(forbiddenWordsDAO.selectById(forbiddenWordsDO.getId()), forbiddenWordsDO,forbiddenWordsDO.getId(),userId);
				  resultID = forbiddenWordsDAO.update(forbiddenWordsDO);
				  forbiddenWords = forbiddenWordsDAO.selectById(forbiddenWordsDO.getId());
//				  baseRedisCacheService.setDatatoRedisCache(Config.FORBIDDEN_WORDS+forbiddenWordsDO.getId(), forbiddenWords);
				  
				  list = forbiddenWordsDAO.selectDynamic(queryForbiddenWords);
//				  baseRedisCacheService.setDatatoRedisCache(Config.ALL_EFFECTIVE_FORBIDDEN_WORDS, list);
				  RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS, list, RedisCacheTimeConstant.HALF_HOUR);
				 return resultID;
			} else {
//				  commonLogService.logsOfUpdate(forbiddenWordsDAO.selectById(forbiddenWordsDO.getId()), forbiddenWordsDO,forbiddenWordsDO.getId(),userId);
				  resultID =  (Integer) forbiddenWordsDAO.updateDynamic(forbiddenWordsDO);
				  forbiddenWords = forbiddenWordsDAO.selectById(forbiddenWordsDO.getId());
//				  baseRedisCacheService.setDatatoRedisCache(Config.FORBIDDEN_WORDS+forbiddenWordsDO.getId(), forbiddenWords);
				  RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.FORBIDDENWORDS + forbiddenWordsDO.getId(), forbiddenWords, RedisCacheTimeConstant.HALF_HOUR);
				  
				  list = forbiddenWordsDAO.selectDynamic(queryForbiddenWords);
//			 	  baseRedisCacheService.setDatatoRedisCache(Config.ALL_EFFECTIVE_FORBIDDEN_WORDS, list);
			 	 RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS, list, RedisCacheTimeConstant.HALF_HOUR);
				 return resultID;
			}
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
//	@Transactional(propagation = Propagation.REQUIRED)
	public int deleteById(Long id) throws ServiceException {
		 ForbiddenWordsDO queryForbiddenWords  = new ForbiddenWordsDO();
		 queryForbiddenWords.setStatus(true);
		try {
			Integer resultID=(Integer) forbiddenWordsDAO.deleteById(id);
//			baseRedisCacheService.deleteRedisCacheByKey(Config.FORBIDDEN_WORDS+id);
			RedisCacheUtil.deleteRedisCache(BaseRedisCacheConstant.ForbiddenWords.FORBIDDENWORDS + id);
		    List<ForbiddenWordsDO> list = forbiddenWordsDAO.selectDynamic(queryForbiddenWords);
		    if(CollectionUtils.isNotEmpty(list)){
//		    	baseRedisCacheService.setDatatoRedisCache(Config.ALL_EFFECTIVE_FORBIDDEN_WORDS, list);
		    	RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS, list, RedisCacheTimeConstant.HALF_HOUR);
		    }		
			return resultID;
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public ForbiddenWordsDO selectById(Long id) throws ServiceException {
		if(null==id){
			return null;
		}
//		ForbiddenWordsDO cache = (ForbiddenWordsDO)baseRedisCacheService.getDataFromRedisCacheByKey(Config.FORBIDDEN_WORDS + id);
		ForbiddenWordsDO cache = (ForbiddenWordsDO)RedisCacheUtil.getRedisCache(BaseRedisCacheConstant.ForbiddenWords.FORBIDDENWORDS + id);
		if(null != cache){
			return cache;
		}
		try {
			cache =  forbiddenWordsDAO.selectById(id);
			if(null != cache){
//				baseRedisCacheService.setDatatoRedisCache(Config.FORBIDDEN_WORDS + id, cache);
				RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.FORBIDDENWORDS + id , cache, RedisCacheTimeConstant.HALF_HOUR);
			}	
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
		return cache;
	}

	@Override
	public Long selectCountDynamic(ForbiddenWordsDO forbiddenWordsDO)
			throws ServiceException {
		try {
			return forbiddenWordsDAO.selectCountDynamic(forbiddenWordsDO);
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ForbiddenWordsDO> selectDynamic(
			ForbiddenWordsDO forbiddenWordsDO) throws ServiceException {
		try {
			return forbiddenWordsDAO.selectDynamic(forbiddenWordsDO);
		} catch (DAOException e) {
			 LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	public Page<ForbiddenWordsDO> queryPageListByForbiddenWordsDO(
			ForbiddenWordsDO forbiddenWordsDO) {
		if (forbiddenWordsDO != null) {
			Long totalCount = this.selectCountDynamic(forbiddenWordsDO);
			List<ForbiddenWordsDO> resultList;
			try {
				resultList = forbiddenWordsDAO.selectDynamicPageQuery(forbiddenWordsDO);
				Page<ForbiddenWordsDO> page = new Page<ForbiddenWordsDO>();
				page.setPageNo(forbiddenWordsDO.getStartPage());
				page.setPageSize(forbiddenWordsDO.getPageSize());
				page.setTotalCount(totalCount.intValue());
				page.setList(resultList);
				return page;
			} catch (DAOException e) {
				 LOGGER.error(e.getMessage(), e);
			}		
		}
		return new Page<ForbiddenWordsDO>();
	}

	public Page<ForbiddenWordsDO> queryPageListByForbiddenWordsDOAndStartPageSize(
			ForbiddenWordsDO forbiddenWordsDO, int startPage, int pageSize) {
		if (forbiddenWordsDO != null && startPage > 0 && pageSize > 0) {
			forbiddenWordsDO.setStartPage(startPage);
			forbiddenWordsDO.setPageSize(pageSize);
			return this.queryPageListByForbiddenWordsDO(forbiddenWordsDO);
		}
		return new Page<ForbiddenWordsDO>();
	}

	@Override
	public List<ForbiddenWordsDO> selectListForbiddenWordsDO(List<Long> ids,Integer status) {
		if(CollectionUtils.isEmpty(ids)){
			return null;
		}
		List<ForbiddenWordsDO> forbiddenWordsDOs = new ArrayList<ForbiddenWordsDO>();
		List<ForbiddenWordsDO> listForbiddenWordsDO = new ArrayList<ForbiddenWordsDO>();
		for (int i = 0; i < ids.size(); i++) {
			ForbiddenWordsDO forbiddenWord = this.selectById(ids.get(i));
			if(forbiddenWord != null){
				listForbiddenWordsDO.add(forbiddenWord);
			}		
		}
		for (ForbiddenWordsDO forbiddenWordsDO : listForbiddenWordsDO) {
			Boolean bool = forbiddenWordsDO.getStatus();
			if (bool) {
				switch (status) {
				case 0:
					break;
				case 1:
					forbiddenWordsDOs.add(forbiddenWordsDO);
					break;
				case 2:
					forbiddenWordsDOs.add(forbiddenWordsDO);
					break;
				default:
					break;
				}
			} else {
				switch (status) {
				case 0:
					forbiddenWordsDOs.add(forbiddenWordsDO);
					break;
				case 1:
					break;
				case 2:
					forbiddenWordsDOs.add(forbiddenWordsDO);
					break;
				default:
					break;
				}
			}
		}
		return forbiddenWordsDOs;
	}

	@Override
	public Page<ForbiddenWordsDO> queryAllLikedForbiddenWordByPage(ForbiddenWordsDO forbiddenWordsDO, Integer pageNo, Integer pageSize) {
		if (forbiddenWordsDO != null && pageNo>0 && pageSize>0) {
			forbiddenWordsDO.setStartPage(pageNo);
			forbiddenWordsDO.setPageSize(pageSize);
			Long totalCount = this.selectCountByLikeOfForbiddenWord(forbiddenWordsDO);
			List<ForbiddenWordsDO> resultList;
			try {
				resultList = forbiddenWordsDAO.selectListOfByLikeForbiddenWordsPageQuery(forbiddenWordsDO);
				Page<ForbiddenWordsDO> page = new Page<ForbiddenWordsDO>();
				page.setPageNo(forbiddenWordsDO.getStartPage());
				page.setPageSize(forbiddenWordsDO.getPageSize());
				page.setTotalCount(totalCount.intValue());
				page.setList(resultList);
				return page;
			} catch (DAOException e) {
		        LOGGER.error(e.getMessage(), e);
				throw new ServiceException(e);
			}			
		}
		return new Page<ForbiddenWordsDO>();
	}

	private Long selectCountByLikeOfForbiddenWord(ForbiddenWordsDO forbiddenWordsDO) {
		try {
			return forbiddenWordsDAO.selectCountByLikeOfForbiddenWord(forbiddenWordsDO);
		} catch (DAOException e) {
	        LOGGER.error(e.getMessage(), e);
			throw new ServiceException(e);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void insertAndUpdate(ForbiddenWordsDO insertWords,Long userId) {
		Long id = this.insert(insertWords,userId);
		ForbiddenWordsDO updateWords=new ForbiddenWordsDO();
		updateWords.setId(id);
		updateWords.setCode(id.toString());
		this.update(updateWords, false,userId);
	}

	@Override
	public String checkForbiddenWordsField(String sourceField) {
		if(StringUtils.isBlank(sourceField)){
			return "";
		}
		List<ForbiddenWordsDO> fobiddenWords=this.getAllEffectiveForbiddenWords();
		StringBuffer sb = new StringBuffer();
		String res=null;
		boolean flag = false;
		sb.append("违禁词：[");
		if(CollectionUtils.isNotEmpty(fobiddenWords)){
			for(ForbiddenWordsDO forbiddenWordsDO :fobiddenWords ){
				String forbiddenWords = forbiddenWordsDO.getWords();
				if(StringUtils.isNotBlank(forbiddenWords)){
					int total = checkStrCount(sourceField,forbiddenWords);
					if(total>0){
						flag = true;
//						sb.append(": 违禁词[").append(forbiddenWords)
//						  .append("],总共出现").append(total).append("次。");
						sb.append(forbiddenWords).append("，");
					}
				}
			}
		}
		res=sb.substring(0, sb.length()-1);
		res+="]";
		if(flag){
			return res;
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * <pre>
	 *    获取违禁词列表
	 * </pre>
	 *
	 * @return
	 */
	@Override
	public  List<ForbiddenWordsDO> getAllEffectiveForbiddenWords(){
		@SuppressWarnings("unchecked")
		List<ForbiddenWordsDO> fobiddenWords= (List<ForbiddenWordsDO>)RedisCacheUtil.getRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS);
		if(CollectionUtils.isNotEmpty(fobiddenWords)){
			return fobiddenWords;
		}
		ForbiddenWordsDO queryForbiddenWords  = new ForbiddenWordsDO();
		queryForbiddenWords.setStatus(true);
		List<ForbiddenWordsDO> list=null;
		try {
			list = forbiddenWordsDAO.selectDynamic(queryForbiddenWords);
			if(CollectionUtils.isNotEmpty(list)){
//				baseRedisCacheService.setDatatoRedisCache(Config.ALL_EFFECTIVE_FORBIDDEN_WORDS, list);
				RedisCacheUtil.setRedisCache(BaseRedisCacheConstant.ForbiddenWords.ALL_EFFECTIVE_FORBIDDEN_WORDS, list, RedisCacheTimeConstant.HALF_HOUR);
			}
		} catch (DAOException e) {
			LOGGER.error(e.getMessage(), e);
		}	
		return list;
	}
	
	
	/**
	 * 
	 * <pre>
	 *  判断一个字符串出现另一个字符串的次数
	 * </pre>
	 *
	 * @param str
	 * @param checkStr
	 * @return
	 */
	private static  int checkStrCount(String str, String checkStr){
		int total = 0;
		for (String tmp = str; 	tmp!= null &&tmp.length()>=checkStr.length();){
		  if(tmp.indexOf(checkStr) == 0){
		    total ++;
		  }
		  tmp = tmp.substring(1);
		}
		return total;
	}

	@Override
	public List<ForbiddenWordsDO> findSameForbiddenWords(ForbiddenWordsDO forbiddenWordsDO) throws ServiceException {
		 List<ForbiddenWordsDO> list =new ArrayList<ForbiddenWordsDO>();
			 try {
				list=forbiddenWordsDAO.selectDynamic(forbiddenWordsDO);
			     } catch (DAOException e) {
				 LOGGER.error(e.getMessage(), e);
				 throw new ServiceException(e);
			}
		return list;
	}

}
