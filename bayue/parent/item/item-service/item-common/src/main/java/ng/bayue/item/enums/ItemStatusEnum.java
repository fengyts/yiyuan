package ng.bayue.item.enums;

public enum ItemStatusEnum {

	OFF_SALES(0, "未上架"), ON_SALES(1, "已上架"), CANCELLATION(2, "作废");

	private String desc;
	private Integer code;

	private ItemStatusEnum(Integer code, String desc) {
		this.desc = desc;
		this.code = code;
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
