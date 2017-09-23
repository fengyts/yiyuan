package ng.bayue.snatch.enums.promotion;

public enum TopicTypeEnum {

	// 1-单品团;2-主题团;3-品牌团
	Single(1, "单品团"), Theme(2, "主题团"), Brand(3, "品牌团");

	private Integer code;

	private String desc;

	private TopicTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getDescByCode(Integer code) {
		if (null == code) {
			return null;
		}
		for (TopicTypeEnum pe : TopicTypeEnum.values()) {
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
