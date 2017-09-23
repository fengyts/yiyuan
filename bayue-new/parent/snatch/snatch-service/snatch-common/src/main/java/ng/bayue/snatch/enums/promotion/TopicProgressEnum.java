package ng.bayue.snatch.enums.promotion;

public enum TopicProgressEnum {

	// 0-未开始1-进行中 2已结束
	NotStarted(0, "未开始"), InProgress(1, "进行中"), End(2, "已结束");

	private Integer code;

	private String desc;

	private TopicProgressEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (TopicProgressEnum pe : TopicProgressEnum.values()) {
			if (code.intValue() == pe.code.intValue()) {
				return pe.desc;
			}
		}
		return null;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

}
