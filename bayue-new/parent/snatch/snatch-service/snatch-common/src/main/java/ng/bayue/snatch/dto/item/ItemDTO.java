package ng.bayue.snatch.dto.item;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ng.bayue.snatch.domain.item.ItemPicturesDO;


public class ItemDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -4223272635642333478L;

	/** 小类编号+4位流水码 */
	private String spu;

	/** spu名称(产品前台展示名称) */
	private String mainTitle;

	/** 大类ID */
	private Long largeId;

	/** 小类ID */
	private Long smallId;

	/** 单位 */
	private Long unitId;
	
	/** 大类名称  */
	private String largeCateName;

	/** 小类名称  */
	private String smallCateName;
	
	/** 单位名称  */
	private String unitName;
	
	/** spu信息备注 */
	private String spuRemark;

	// ------------------------------------------------

	/** detail的主键 */
	private Long id;

	/** 商品ID */
	private Long itemId;

	/** 编号 spu+2位随机码 */
	private String prdid;

	/** 商品名称(即mainTitle,冗余) */
	private String itemTitle;

	/** 副标题 */
	private String subTitle;

	/** 条码(全局唯一) */
	private String barcode;

	/** 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1 */
	private Integer itemType;
	
	/** 商品类型 -1 未设置(区别于 itemType) 保留字段 */
	private Boolean productType;

	/** 市场价 */
	private Double basicPrice;

	/** 无理由退货期限 单位 天 */
	private Integer returnDays;

	/** 毛重 单位g */
	private Double weight;

	/** 净重 单位g */
	private Double weightNet;

	/** 体积-宽度 */
	private Integer volumeWidth;

	/** 体积-长度 */
	private Integer volumeLength;

	/** 体积-高度 */
	private Integer volumeHigh;

	/** 品类三级code 冗余字段 */
	private String categoryCode;

	/** 规格 */
	private String specifications;

	/** 状态0-未上架 1-上架 2-作废 默认0 */
	private Integer status;

	/** detail信息备注 */
	private String remark;
	
	/** 关联规格组id列表 */
	private String specGroupIds;
	
	/** 关联图片id列表 */
	private String pictureIds;
	
	/** 关联图片地址,多个以逗号分隔 */
	private String picUrls;
	/**  关联图片列表 */
	private List<ItemPicturesDO> listPicUrls;
	
	/** 商品详情描述信息 */
	private String description;
	
	private Long createUserId;
	private Date createTime;
	private Date modifyTime;

	public String getSpu() {
		return spu;
	}

	public void setSpu(String spu) {
		this.spu = spu;
	}

	public String getMainTitle() {
		return mainTitle;
	}

	public void setMainTitle(String mainTitle) {
		this.mainTitle = mainTitle;
	}

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

	public String getSpuRemark() {
		return spuRemark;
	}

	public void setSpuRemark(String spuRemark) {
		this.spuRemark = spuRemark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Double getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(Double basicPrice) {
		this.basicPrice = basicPrice;
	}

	public Integer getReturnDays() {
		return returnDays;
	}

	public void setReturnDays(Integer returnDays) {
		this.returnDays = returnDays;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getWeightNet() {
		return weightNet;
	}

	public void setWeightNet(Double weightNet) {
		this.weightNet = weightNet;
	}

	public Integer getVolumeWidth() {
		return volumeWidth;
	}

	public void setVolumeWidth(Integer volumeWidth) {
		this.volumeWidth = volumeWidth;
	}

	public Integer getVolumeLength() {
		return volumeLength;
	}

	public void setVolumeLength(Integer volumeLength) {
		this.volumeLength = volumeLength;
	}

	public Integer getVolumeHigh() {
		return volumeHigh;
	}

	public void setVolumeHigh(Integer volumeHigh) {
		this.volumeHigh = volumeHigh;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Boolean getProductType() {
		return productType;
	}

	public void setProductType(Boolean productType) {
		this.productType = productType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getSpecGroupIds() {
		return specGroupIds;
	}

	public void setSpecGroupIds(String specGroupIds) {
		this.specGroupIds = specGroupIds;
	}

	public String getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(String pictureIds) {
		this.pictureIds = pictureIds;
	}
	
	public String getPicUrls() {
		return picUrls;
	}

	public void setPicUrls(String picUrls) {
		this.picUrls = picUrls;
	}

	public List<ItemPicturesDO> getListPicUrls() {
		return listPicUrls;
	}

	public void setListPicUrls(List<ItemPicturesDO> listPicUrls) {
		this.listPicUrls = listPicUrls;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
