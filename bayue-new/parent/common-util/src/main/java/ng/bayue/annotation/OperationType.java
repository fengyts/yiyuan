package ng.bayue.annotation;

import org.apache.commons.lang3.StringUtils;

public enum OperationType {

	ADD("01", "新增"), UPDATE("02", "修改"), DELETE("03", "删除");

	private String code;
	private String desc;

	private OperationType(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public static String getDescByCode(String code) {
		if (StringUtils.isEmpty(code)) {
			return null;
		}
		for (OperationType type : OperationType.values()) {
			if (type.code.equals(code)) {
				return type.desc;
			}
		}
		return null;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
