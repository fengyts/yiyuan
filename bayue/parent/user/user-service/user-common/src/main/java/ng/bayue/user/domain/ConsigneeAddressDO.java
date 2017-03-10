package ng.bayue.user.domain;

import ng.bayue.common.BaseDO;

import java.util.Date;

/**
 * 用户收货地址
 * 
 * @author fengyts Fri Mar 10 13:32:18 CST 2017
 */

public class ConsigneeAddressDO extends BaseDO {

	private static final long serialVersionUID = -5482663951417904597L;

	/** 主键 */
	private Long id;

	/** 用户id */
	private Long userId;

	/** 收货地址-省份id */
	private Long provinceId;

	/** 收货地址-省份(用于前端展示) */
	private String province;

	/** 收货地址-城市id */
	private Long cityId;

	/** 收货地址-城市(用于前端展示) */
	private String city;

	/** 收货地址-区县id */
	private Long countryId;

	/** 收货地址-区县(用于前端展示) */
	private String country;

	/** 收货地址-街道id */
	private Long streetId;

	/** 收货地址-街道(用于前端展示) */
	private String street;

	/** 收货详细地址 */
	private String address;

	/** 是否默认收货地址(1-是,0-否) */
	private Boolean isDefault;

	/** 是否删除(1-是,0-否) */
	private Boolean isDelete;

	/** 创建时间 */
	private Date createTime;

	/** 修改时间 */
	private Date modifyTime;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置 用户id
	 * 
	 * @param userId
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 设置 收货地址-省份id
	 * 
	 * @param provinceId
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 设置 收货地址-省份(用于前端展示)
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 设置 收货地址-城市id
	 * 
	 * @param cityId
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * 设置 收货地址-城市(用于前端展示)
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 设置 收货地址-区县id
	 * 
	 * @param countryId
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * 设置 收货地址-区县(用于前端展示)
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 设置 收货地址-街道id
	 * 
	 * @param streetId
	 */
	public void setStreetId(Long streetId) {
		this.streetId = streetId;
	}

	/**
	 * 设置 收货地址-街道(用于前端展示)
	 * 
	 * @param street
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * 设置 收货详细地址
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 设置 是否默认收货地址(1-是,0-否)
	 * 
	 * @param isDefault
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * 设置 是否删除(1-是,0-否)
	 * 
	 * @param isDelete
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}

	/**
	 * 设置 创建时间
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 设置 修改时间
	 * 
	 * @param modifyTime
	 */
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
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
	 * 获取 用户id
	 * 
	 * @return userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 获取 收货地址-省份id
	 * 
	 * @return provinceId
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 获取 收货地址-省份(用于前端展示)
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 获取 收货地址-城市id
	 * 
	 * @return cityId
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 获取 收货地址-城市(用于前端展示)
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 获取 收货地址-区县id
	 * 
	 * @return countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * 获取 收货地址-区县(用于前端展示)
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 获取 收货地址-街道id
	 * 
	 * @return streetId
	 */
	public Long getStreetId() {
		return streetId;
	}

	/**
	 * 获取 收货地址-街道(用于前端展示)
	 * 
	 * @return street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * 获取 收货详细地址
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 获取 是否默认收货地址(1-是,0-否)
	 * 
	 * @return isDefault
	 */
	public Boolean getIsDefault() {
		return isDefault;
	}

	/**
	 * 获取 是否删除(1-是,0-否)
	 * 
	 * @return isDelete
	 */
	public Boolean getIsDelete() {
		return isDelete;
	}

	/**
	 * 获取 创建时间
	 * 
	 * @return createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 获取 修改时间
	 * 
	 * @return modifyTime
	 */
	public Date getModifyTime() {
		return modifyTime;
	}

}