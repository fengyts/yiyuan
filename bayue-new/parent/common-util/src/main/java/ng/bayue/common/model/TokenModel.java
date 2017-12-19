package ng.bayue.common.model;

import ng.bayue.constants.RedisCacheTimeConstant;
import ng.bayue.util.SecurityUtil;
import ng.bayue.util.StringUtils;

public class TokenModel extends BaseRedisModel {

	private static final long serialVersionUID = -140506608775223161L;

	private static final int DEFAULT_LIVETIME = RedisCacheTimeConstant.HALF_HOUR;

	private String key;

	private String token;

	private String tokenType;

	private Integer liveTime;

	public TokenModel() {
		this(null, null, null);
	}

	public TokenModel(String key) {
		this(key, null, null);
	}
	
	public TokenModel(String key, String tokenType) {
		init(key, tokenType, null);
	}

	public TokenModel(String key, String tokenType, Integer liveTime) {
		init(key, tokenType, liveTime);
	}

	public void init(String key, String tokenType, Integer liveTime) {
		if (StringUtils.isBlank(key)) {
			key = generateTk();
		}
		if (null == liveTime || 0 >= liveTime) {
			liveTime = DEFAULT_LIVETIME;
		}
		String tokenTem = generateTk();
		this.token = SecurityUtil.encryptMD5(tokenTem + key);
		this.tokenType = tokenType;
		this.liveTime = liveTime;
		this.key = initKey(key);
	}

	private String generateTk() {
		return super.baseKey;
	}
	
	private String initKey(String key){
		return null == tokenType ? key : tokenType + "_" + key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public Integer getLiveTime() {
		return liveTime;
	}

	public void setLiveTime(Integer liveTime) {
		this.liveTime = liveTime;
	}

}
