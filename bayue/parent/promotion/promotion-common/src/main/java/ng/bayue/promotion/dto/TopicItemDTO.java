package ng.bayue.promotion.dto;

import ng.bayue.promotion.domain.TopicItemDO;

public class TopicItemDTO extends TopicItemDO {

	private static final long serialVersionUID = -7757571919280590006L;

	/** 专题名称 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
