package ng.bayue.snatch.dto.item;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import ng.bayue.snatch.domain.item.DetailSpecDO;
import ng.bayue.snatch.domain.item.ItemPicturesDO;

public class ItemDetailDTO implements Serializable {

	/**  */
	private static final long serialVersionUID = -6914019869680731771L;
	
	// -----------------------------------------------------------
	/** ########## spu级别信息  ######### */
	
	/** 商品ID */
//	@NotNull(message = "商品不能为空")
	private Long itemId;

	/** 大类ID */
//	@NotNull(message = "大类不能为空")
	private Long largeId;

	/** 小类ID */
//	@NotNull(message = "小类不能为空")
	private Long smallId;

	/** 单位 */
//	@NotNull(message = "单位不能为空")
	private Long unitId;

	/** 小类编号+4位流水码 冗余字段 */
//	@NotBlank(message = "spu不能为空")
	private String spu;
	
	/** 大类名称  */
	private String largeCateName;

	/** 小类名称  */
	private String smallCateName;
	
	/** 单位名称  */
	private String unitName;
	
	// -----------------------------------------------------------
	/** ########## detail级别信息  ######### */
	
	/** detial主键id */
	private Long id;
	
	/** 编号 spu+2位随机码 */
	private String prdid;

	/** 商品名称 */
//	@NotBlank(message = "商品名称不能为空")
	private String itemTitle;

	/** 产品前台展示名称 */
//	@NotBlank(message = "spu名称不能为空")
	private String mainTitle;

	/** 副标题 */
//	@NotBlank(message = "商品副标题不能为空")
	private String subTitle;

	/** 条码(全局唯一) */
	private String barcode;

	/** 商品类型：1-正常商品，2-服务商品，3-二手商品,4-报废商品 默认1 */
	private Integer itemType;
	
	/** 商品类型 -1 未设置(区别于 itemType) 保留字段*/
	private Boolean productType;

	/** 状态0-未上架 1-上架 2-作废 默认0 */
	private Integer status;
	
	/** 市场价 */
//	@NotNull(message = "市场价不能为空")
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
	
	/** detail信息备注 */
	private String remark;
	
	/** 关联规格组id列表 */
	private String specGroupStrs;
	
	List<DetailSpecDO> listSpecGroups;
	
	/** 关联图片id列表 */
	private String pictureIds;
	
	/** 关联图片地址,多个以逗号分隔 */
	private String picUrls;
	
	/**  关联图片列表 */
	private List<ItemPicturesDO> listPicUrls;
	
	/** 商品详情描述信息 */
//	@NotBlank(message = "商品描述信息不能为空")
	private String description;
	
	private Long createUserId;
	private Date createTime;
	private Date modifyTime;
	private Long modifyUserId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getProductType() {
		return productType;
	}

	public void setProductType(Boolean productType) {
		this.productType = productType;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getSpecGroupStrs() {
		return specGroupStrs;
	}

	public void setSpecGroupStrs(String specGroupStrs) {
		this.specGroupStrs = specGroupStrs;
	}

	public String getPictureIds() {
		return pictureIds;
	}

	public void setPictureIds(String pictureIds) {
		this.pictureIds = pictureIds;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
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

	public List<DetailSpecDO> getListSpecGroups() {
		return listSpecGroups;
	}

	public void setListSpecGroups(List<DetailSpecDO> listSpecGroups) {
		this.listSpecGroups = listSpecGroups;
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

}
