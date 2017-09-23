package ng.bayue.snatch.enums.item;

public enum ItemStatusEnum {

	OFF_SALES(0, "未上架"), ON_SALES(1, "已上架"), CANCELLATION(2, "作废");

	private Integer code;
	private String desc;

	private ItemStatusEnum(Integer code, String desc) {
		this.desc = desc;
		this.code = code;
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


	public String getDescByCode(Integer code) {
		if (null != code && code > 0) {
			ItemStatusEnum[] e = ItemStatusEnum.values();
			for (ItemStatusEnum ise : e) {
				if (code.intValue() == ise.code.intValue()) {
					return ise.desc;
				}
			}
		}
		return null;
	}

}
