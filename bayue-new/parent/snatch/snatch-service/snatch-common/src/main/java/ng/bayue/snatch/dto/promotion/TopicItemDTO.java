package ng.bayue.snatch.dto.promotion;

import ng.bayue.snatch.domain.promotion.TopicItemDO;


public class TopicItemDTO extends TopicItemDO {

	private static final long serialVersionUID = -7757571919280590006L;

	/** 专题名称 */
	private String name;

	/** 商品标题 */
	private String itemTitle;

	/** 当前已参与人数 */
	private Integer currentParticipant;
	
	/** 商品图片地址  */
	private String picture;
	
	/** 夺宝完成比例 % */
	private String ratio;
	
	/** 剩余人数 */
	private String surplus;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public Integer getCurrentParticipant() {
		return currentParticipant;
	}

	public void setCurrentParticipant(Integer currentParticipant) {
		this.currentParticipant = currentParticipant;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getRatio() {
		return ratio;
	}

	public void setRatio(String ratio) {
		this.ratio = ratio;
	}

	public String getSurplus() {
		return surplus;
	}

	public void setSurplus(String surplus) {
		this.surplus = surplus;
	}

}
