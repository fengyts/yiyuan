package ng.bayue.service.impl;

import javax.annotation.Resource;

import ng.bayue.common.model.FrequencyModel;
import ng.bayue.service.FrequencyService;
import ng.bayue.service.RedisCacheService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FrequencyServiceImpl implements FrequencyService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "redisCacheService1")
	RedisCacheService redisCacheService1;

	@Override
	public Boolean overLoad(FrequencyModel model) {
		String key = generateKey(model);
		boolean flag = false;
		try {
			flag = redisCacheService1.watchMethodCall(key, model.getSeconds(), model.getTimes());
		} catch (Exception e) {
			logger.info("是否超载调用redis失败", e);
		}
		return flag;
	}

	private String generateKey(FrequencyModel model) {
		return model.getBinuessType() + "_" + model.getKey();
	}

}
