package ng.bayue.common.model;

import java.io.Serializable;
import java.util.UUID;

public class BaseRedisModel implements Serializable {

	private static final long serialVersionUID = -9215299931400216532L;

	protected String baseKey = UUID.randomUUID().toString().replace("-", "");

	public String getBaseKey() {
		return baseKey;
	}

	public void setBaseKey(String baseKey) {
		this.baseKey = baseKey;
	}

}
