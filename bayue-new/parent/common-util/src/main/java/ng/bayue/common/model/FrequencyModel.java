package ng.bayue.common.model;

public class FrequencyModel extends BaseRedisModel {

	private static final long serialVersionUID = -1665785519361315597L;

	/** redis key值,实际值为 (BinuessType + '_' + key) */
	private String key;
	/** 业务类型 */
	private String BinuessType;
	/** 访问次数限制 */
	private Integer times;
	/** 单位时间：秒 */
	private Integer seconds;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getBinuessType() {
		return BinuessType;
	}

	public void setBinuessType(String binuessType) {
		BinuessType = binuessType;
	}

	public Integer getTimes() {
		return times;
	}

	public void setTimes(Integer times) {
		this.times = times;
	}

	public Integer getSeconds() {
		return seconds;
	}

	public void setSeconds(Integer seconds) {
		this.seconds = seconds;
	}

}
