package ng.bayue.backend.enums;

public enum SysMenuTypeEnum {
	
	NAVIGATION(0,"导航"),MENUBAR(1,"主菜单"),CHILDMENU(2,"按钮");
	
	private Integer code;
	private String value;
	
	private SysMenuTypeEnum(Integer code,String value){
		this.code = code;
		this.value = value;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValueByCode(Integer code){
		if(null == code){return null;}
		for(SysMenuTypeEnum menuTypeEnum : SysMenuTypeEnum.values()){
			if(code.intValue() == menuTypeEnum.code){
				return menuTypeEnum.value;
			}
		}
		return null;
	}
	
	

}
