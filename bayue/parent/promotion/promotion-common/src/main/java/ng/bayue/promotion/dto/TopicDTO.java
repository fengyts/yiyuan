package ng.bayue.promotion.dto;

import ng.bayue.promotion.domain.TopicDO;

public class TopicDTO extends TopicDO{

	/**  */
	private static final long serialVersionUID = -1708050295880577969L;
	
	private Integer countItems;

	public Integer getCountItems() {
		return countItems;
	}

	public void setCountItems(Integer countItems) {
		this.countItems = countItems;
	}
	
	

}
