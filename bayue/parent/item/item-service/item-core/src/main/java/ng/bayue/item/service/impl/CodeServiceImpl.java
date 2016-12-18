package ng.bayue.item.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.item.constant.CodeConstant;
import ng.bayue.item.domain.CodeDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.CodeDAO;
import ng.bayue.item.service.CodeService;
import ng.bayue.util.Page;

@Service(value="codeService")
public class CodeServiceImpl  implements CodeService{

	private Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private CodeDAO codeDAO;

	@Override
	public Long insert(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.insert(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int update(CodeDO codeDO,boolean isAllField) throws ServiceException {
		try {
			if(isAllField){
				return (Integer) codeDAO.update(codeDO);
			}else{
				return (Integer) codeDAO.updateDynamic(codeDO);
			}
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public int deleteById(Long id) throws ServiceException {
		try {
			return (Integer) codeDAO.deleteById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public CodeDO selectById(Long id) throws ServiceException {
		try {
			return codeDAO.selectById(id);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public Long selectCountDynamic(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectCountDynamic(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	@Override
	public List<CodeDO> selectDynamic(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectDynamic(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	private List<CodeDO> selectDynamicPageQuery(CodeDO codeDO) throws ServiceException {
		try {
			return codeDAO.selectDynamicPageQuery(codeDO);
		}catch(DAOException e){
			logger.error(e);
            throw new ServiceException(e);
		}
	}

	public Page<CodeDO> queryPageListByCodeDO(CodeDO codeDO) {
		if (codeDO != null) {
			Long totalCount = this.selectCountDynamic(codeDO);
			List<CodeDO> resultList = this.selectDynamicPageQuery(codeDO);

			Page<CodeDO> page = new Page<CodeDO>();
			page.setPageNo(codeDO.getStartPage());
			page.setPageSize(codeDO.getPageSize());
			page.setTotalCount(totalCount.intValue());
			page.setList(resultList);
			return page;
		}
		return new Page<CodeDO>();
	}

	public Page<CodeDO> queryPageListByCodeDOAndStartPageSize(CodeDO codeDO,int startPage,int pageSize){
		if (codeDO != null && startPage>0 && pageSize>0) {
			codeDO.setStartPage(startPage);
			codeDO.setPageSize(pageSize);
			return this.queryPageListByCodeDO(codeDO);
		}
		return new Page<CodeDO>();
	}
	
	@Override
	public String getUniqueCode(String code, int type) throws ServiceException {

		String errorMsg = "";
		int bits = 0;// 流水码长度
		switch (type) {
		case CodeConstant.CodeType.SPU_CODE:
			errorMsg = "获取SPU编码出错";
			bits = CodeConstant.RunningWaterCode.SPU_CODE;
			break;
		case CodeConstant.CodeType.PRDID_CODE:
			errorMsg = "获取prdid编码出错";
			bits = CodeConstant.RunningWaterCode.PRDID_CODE;
			break;
		default:
			break;
		}

		if (StringUtils.isAnyEmpty(code)) {
			throw new ServiceException(errorMsg);
		}
		CodeDO codeDO = new CodeDO();
		codeDO.setCode(code);
		int value = 0;
		try {
			List<CodeDO> list = selectDynamic(codeDO);
			if (CollectionUtils.isEmpty(list)) {
				codeDO.setMaxValue(CodeConstant.CODE_INIT_VALUE);
				insert(codeDO);
			} else {
				codeDAO.updateCode(code);
			}
			List<CodeDO> listCode = selectDynamic(codeDO);
			if (CollectionUtils.isNotEmpty(listCode)) {
				value = listCode.get(0).getMaxValue();
			} else {
				throw new ServiceException("获取商品编码失败");
			}

		} catch (DAOException e) {
			logger.error(e.getMessage(), e);
		}
		return getCode(code, value, bits);
	}

	private String getCode(String code, int value, int bits) {
		String res = code;
		int length = (value + "").length();
		StringBuffer s = new StringBuffer();
		if (length <= bits) {
			for (int i = 0; i < bits - length; i++) {
				s.append(CodeConstant.ZERO_STR);
			}
			res += s.toString() + value;
		} else {
			res += value;
		}
		return res;
	}

}
