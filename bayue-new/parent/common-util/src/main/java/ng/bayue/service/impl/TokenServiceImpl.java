package ng.bayue.service.impl;

import javax.annotation.Resource;

import ng.bayue.common.model.TokenModel;
import ng.bayue.enums.RedisModelStatusEnum;
import ng.bayue.service.RedisCacheService;
import ng.bayue.service.TokenService;
import ng.bayue.util.SecurityUtil;
import ng.bayue.util.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//@Service(value = "tokenService")
public class TokenServiceImpl implements TokenService {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource(name = "redisCacheService1")
	RedisCacheService redisCacheService;

	@Override
	public void create(TokenModel model) {
		redisCacheService
				.setRedisCacheString(generateKey(model),
						SecurityUtil.hashToStr(model.getToken(), model.getKey(), 1024),
						model.getLiveTime());
	}

	@Override
	public RedisModelStatusEnum check(TokenModel model) {
		String key = generateKey(model);
		String tokenRd = redisCacheService.getRedisCacheString(key);
		if (StringUtils.isBlank(tokenRd)) {
			return RedisModelStatusEnum.OVERDUE;
		}
		// AESUtils aes = new AESUtils();
		// String decryptedKey = aes.decrypt(model.getKey());
		if (SecurityUtil.hashToStr(model.getToken(), model.getKey(), 1024).equals(tokenRd)) {
			redisCacheService.updateExpire(key, model.getLiveTime());
			return RedisModelStatusEnum.CORRECT;
		}
		return RedisModelStatusEnum.ERROR;
	}

	@Override
	public void remove(TokenModel model) {
		redisCacheService.deleteRedisCache(generateKey(model));
	}

	@Override
	public String generateKey(TokenModel model) {
//		return model.getTokenType() + UNDERLINE + model.getKey();
		return model.getKey();
	}

}
