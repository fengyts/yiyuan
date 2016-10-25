package ng.bayue.base.enums;

/**
 * <pre>
 * 数据字典枚举类
 * </pre>
 *
 * @author fengyts
 * @version $Id: DictionaryEnums.java, v 0.1 2016年7月17日 下午5:36:20 fengyts Exp $
 */
public enum DictionaryEnums {
	
	c1001("c1001","单位");
	
	private String code;
	private String value;
	
	private DictionaryEnums(String code,String value){
		this.code = code;
		this.value = value;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	

}
