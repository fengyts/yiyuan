package ng.bayue.snatch.enums.item;

public enum ItemTypeEnum {

	NOMAL_ITEM(1, "正常商品"), SERVER_ITEM(2, "服务商品"), SECOND_HAND_ITEM(3, "二手商品");

	private Integer code;
	private String desc;

	private ItemTypeEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
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
		if (null != code) {
			ItemTypeEnum[] values = ItemTypeEnum.values();
			for (ItemTypeEnum type : values) {
				if (type.code.intValue() == code.intValue()) {
					return type.desc;
				}
			}
		}
		return null;
	}

}
