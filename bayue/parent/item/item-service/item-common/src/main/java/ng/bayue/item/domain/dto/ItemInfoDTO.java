package ng.bayue.item.domain.dto;

import ng.bayue.item.domain.ItemInfoDO;

public class ItemInfoDTO extends ItemInfoDO{
	
	/**  */
	private static final long serialVersionUID = 2565184771377679700L;

	/** 大类名称  */
	private String largeCateName;

	/** 小类名称  */
	private String smallCateName;
	
	/** 单位名称  */
	private String unitName;

	public String getLargeCateName() {
		return largeCateName;
	}

	public void setLargeCateName(String largeCateName) {
		this.largeCateName = largeCateName;
	}

	public String getSmallCateName() {
		return smallCateName;
	}

	public void setSmallCateName(String smallCateName) {
		this.smallCateName = smallCateName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	
	

}
