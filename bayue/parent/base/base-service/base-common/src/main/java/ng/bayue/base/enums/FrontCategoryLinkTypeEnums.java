package ng.bayue.base.enums;

/**
 * <pre>
 * 前台分类跳转方式
 * </pre>
 *
 * @author lenovopc
 * @version $Id: FrontCategoryLinkTypeEnums.java, v 0.1 2017年4月13日 下午3:01:40 lenovopc Exp $
 */
public enum FrontCategoryLinkTypeEnums {

	// 跳转方式:1-后台分类,2-固定页面,3-商品,4-品牌,5-搜索词
	backendCate(1, "后台品类"), fixation(2, "固定页面"), item(3, "商品"), brand(4, "品牌"), searchWord(5, "搜索词");

	private Integer code;
	private String desc;

	private FrontCategoryLinkTypeEnums(Integer code, String desc) {
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

}
