package ng.bayue.item.domain.dto;

import java.io.Serializable;

public class ItemDetailDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -6914019869680731771L;

	/** 大类ID */
	private Long largeId;

	/** 小类ID */
	private Long smallId;

	/** 单位 */
	private Long unitId;

	/** 小类编号+4位流水码 冗余字段 */
	private String spu;

	/** 商品ID */
	private Long itemId;

	/** 编号 spu+2位随机码 */
	private String prdid;

	/** 商品名称 */
	private String itemTitle;

	/** 产品前台展示名称 */
	private String mainTitle;

	/** 副标题 */
	private String subTitle;

	/** 条码(全局唯一) */
	private String barcode;

	/** 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1 */
	private Integer itemType;

	/** 状态0-未上架 1-上架 2-作废 默认0 */
	private Integer status;

	public Long getLargeId() {
		return largeId;
	}

	public void setLargeId(Long largeId) {
		this.largeId = largeId;
	}

	public Long getSmallId() {
		return smallId;
	}

	public void setSmallId(Long smallId) {
		this.smallId = smallId;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	public String getSpu() {
		return spu;
	}

	public void setSpu(String spu) {
		this.spu = spu;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getPrdid() {
		return prdid;
	}

	public void setPrdid(String prdid) {
		this.prdid = prdid;
	}

	public String getItemTitle() {
		return itemTitle;
	}

	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Integer getItemType() {
		return itemType;
	}

	public void setItemType(Integer itemType) {
		this.itemType = itemType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
