package ng.bayue.learn.spring_data_redis;

import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

public class Test {
	
	public void test(){
		RedisTemplate<String, Object> rt;
		ValueOperations<String, Object> vo;
		BoundValueOperations<String, Object> bvo;
	}

}
