package ng.bayue.item.service.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ng.bayue.item.constant.CodeConstant;
import ng.bayue.item.domain.CodeDO;
import ng.bayue.item.exception.DAOException;
import ng.bayue.item.exception.ServiceException;
import ng.bayue.item.persist.dao.CodeDAO;
import ng.bayue.item.service.ItemManagerService;

/**
 * <pre>
 * item公用接口
 * </pre>
 *
 * @author lenovopc
 * @version $Id: ItemManagerServiceImpl.java, v 0.1 2016年12月6日 上午10:21:31
 *          lenovopc Exp $
 */
@Service(value = "itemManagerService")
public class ItemManagerServiceImpl implements ItemManagerService {

	private static final Logger logger = LoggerFactory.getLogger(ItemManagerServiceImpl.class);

	@Autowired
	private CodeDAO codeDAO;

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
			List<CodeDO> list = codeDAO.selectDynamic(codeDO);
			if (CollectionUtils.isEmpty(list)) {
				codeDO.setMaxValue(CodeConstant.CODE_INIT_VALUE);
				codeDAO.insert(codeDO);
			} else {
				codeDAO.updateCode(code);
			}
			List<CodeDO> listCode = codeDAO.selectDynamic(codeDO);
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
