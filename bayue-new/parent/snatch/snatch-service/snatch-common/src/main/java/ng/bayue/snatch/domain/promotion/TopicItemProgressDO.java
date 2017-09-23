package ng.bayue.snatch.domain.promotion;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 商品夺宝进度
 * 
 * @author fengyts Thu Apr 27 09:43:12 CST 2017
 */

public class TopicItemProgressDO extends BaseDO {

	private static final long serialVersionUID = 3174598262486818861L;

	/** 主键 */
	private Long id;

	/** 专题商品id */
	private Long topicItemId;

	/** 本期已参与人数 */
	private Integer currentParticipant;

	/** 上一期结束时间 */
	private Date previousEndTime;

	/** 本期开始时间 */
	private Date currentStartTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 专题商品id
	 * 
	 * @param topicItemId
	 */
	public void setTopicItemId(Long topicItemId) {
		this.topicItemId = topicItemId;
	}

	/**
	 * 设置 本期已参与人数
	 * 
	 * @param currentParticipant
	 */
	public void setCurrentParticipant(Integer currentParticipant) {
		this.currentParticipant = currentParticipant;
	}

	/**
	 * 设置 上一期结束时间
	 * 
	 * @param previousEndTime
	 */
	public void setPreviousEndTime(Date previousEndTime) {
		this.previousEndTime = previousEndTime;
	}

	/**
	 * 设置 本期开始时间
	 * 
	 * @param currentStartTime
	 */
	public void setCurrentStartTime(Date currentStartTime) {
		this.currentStartTime = currentStartTime;
	}

	/**
	 * 获取 主键
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 获取 专题商品id
	 * 
	 * @return topicItemId
	 */
	public Long getTopicItemId() {
		return topicItemId;
	}

	/**
	 * 获取 本期已参与人数
	 * 
	 * @return currentParticipant
	 */
	public Integer getCurrentParticipant() {
		return currentParticipant;
	}

	/**
	 * 获取 上一期结束时间
	 * 
	 * @return previousEndTime
	 */
	public Date getPreviousEndTime() {
		return previousEndTime;
	}

	/**
	 * 获取 本期开始时间
	 * 
	 * @return currentStartTime
	 */
	public Date getCurrentStartTime() {
		return currentStartTime;
	}

}