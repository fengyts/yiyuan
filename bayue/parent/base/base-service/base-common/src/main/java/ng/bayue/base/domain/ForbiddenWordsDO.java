package ng.bayue.base.domain;

import java.util.Date;

import ng.bayue.util.BaseDO;

/**
 * <pre>
 * 违禁词
 * </pre>
 *
 * @author fengyts
 * @version $Id: ForbiddenWords.java, v 0.1 2016年7月3日 下午5:45:30 fengyts Exp $
 */
public class ForbiddenWordsDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 4277212779841359110L;

	/** 主键 */
	private Long id;

	/** 违禁词编号 */
	private String code;

	/** 类型: 0-全网(默认此项), 1-其它 */
	private Integer type;

	/** 违禁词 */
	private String words;

	/** 状态:0-无效, 1-有效 */
	private Boolean status;

	/** 创建时间 */
	private Date createdTime;

	/** 更新时间 */
	private Date modifyTime;

	/** 备注 */
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getWords() {
		return words;
	}

	public void setWords(String words) {
		this.words = words;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
