package ng.bayue.backend.ao.basedata;

import java.util.Date;
import java.util.List;

import ng.bayue.backend.util.Messages;
import ng.bayue.backend.util.ResultMessage;
import ng.bayue.base.domain.SpecDO;
import ng.bayue.base.service.SpecService;
import ng.bayue.common.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecAO {

	private static final Logger logger = LoggerFactory.getLogger(SpecAO.class);

	@Autowired
	private SpecService specService;

	public Page<SpecDO> queryPageList(SpecDO specDO, Integer pageNo, Integer pageSize) {
		Page<SpecDO> page = specService.queryPageListBySpecDOAndStartPageSize(specDO, pageNo,
				pageSize);
		return page;
	}

	public SpecDO selectById(Long id) {
		if (null == id) {
			return null;
		}
		return specService.selectById(id);
	}

	public ResultMessage saveSpecDO(SpecDO specDO) {
		if (null == specDO) {
			return ResultMessage.validParamResult();
		}
		if (validIsExists(specDO)) {
			return ResultMessage.validIsExist();
		}
		specDO.setCreateTime(new Date());
		specDO.setModifyTime(new Date());
		Long res = specService.insert(specDO);
		if (1 > res) {
			return new ResultMessage(ResultMessage.Failure, Messages.ServerInnerError);
		}
		
		/*String code = specDO.getCode().trim();
		Integer sort = specDO.getSort();
		
		SpecDO specDO1 = new SpecDO();
		specDO1.setId(res);
		if(StringUtils.isEmpty(code)){
			specDO1.setCode(String.valueOf(res));
		}
		if(null == sort){
			specDO1.setSort(res.intValue());
		}
		specService.update(specDO1, false);*/
		return new ResultMessage();
	}

	public ResultMessage updateSpecDO(SpecDO specDO, String oldSpec) {
		if (null == specDO) {
			return null;
		}
		String spec = specDO.getSpec().trim();
		if(StringUtils.isEmpty(spec)){
			return ResultMessage.validParameterNull("spec");
		}
		if(StringUtils.isEmpty(oldSpec)){
			logger.info("服务器异常,获取oldSpec异常");
			return new ResultMessage(ResultMessage.Failure,Messages.ServerInnerError);
		}
		if (!spec.equals(oldSpec)) {
			SpecDO validSpec = new SpecDO();
			validSpec.setSpec(oldSpec);
			if (validIsExists(validSpec)) {
				return ResultMessage.validIsExist();
			}
			return new ResultMessage(ResultMessage.Failure, Messages.ServerInnerError);
		} else {
			specDO.setModifyTime(new Date());
			int res = specService.update(specDO, false);
			if (res < 1) {
				return new ResultMessage(ResultMessage.Failure, Messages.ServerInnerError);
			}
			return new ResultMessage();
		}

	}

	public boolean validIsExists(SpecDO specDO) {
		if (StringUtils.isEmpty(specDO.getSpec())) {
			return false;
		}
		List<SpecDO> list = specService.selectDynamic(specDO);
		if (CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

}
