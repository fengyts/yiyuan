package ng.bayue.snatch.domain.item;

import ng.bayue.common.BaseDO;

/**
 * spu,prdid编码生成
 * 
 * @author fengyts Tue Dec 06 09:07:19 CST 2016
 */

public class CodeDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 3033314600590767030L;

	/** 主键 */
	private Long id;

	/** 对应编码 */
	private String code;

	/** 编码对应最大值 */
	private Integer maxValue;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 对应编码
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 设置 编码对应最大值
	 * 
	 * @param maxValue
	 */
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
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
	 * 获取 对应编码
	 * 
	 * @return code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 获取 编码对应最大值
	 * 
	 * @return maxValue
	 */
	public Integer getMaxValue() {
		return maxValue;
	}

}