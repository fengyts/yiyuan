package ng.bayue.service.common;

import ng.bayue.enums.RedisModelStatusEnum;

public interface GeneralRedisModelService<BaseRedisModel> {
	
	public static final String UNDERLINE = "_";

	void create(BaseRedisModel model);

	RedisModelStatusEnum check(BaseRedisModel model);

	void remove(BaseRedisModel model);

	String generateKey(BaseRedisModel model);

}
