package ng.bayue.service.common;

import ng.bayue.enums.RedisModelStatusEnum;

public interface GeneralRedisModelService<BaseRedisModel> {

	void create(BaseRedisModel model);

	RedisModelStatusEnum check(BaseRedisModel model);
	
	void remove(BaseRedisModel model);

}
