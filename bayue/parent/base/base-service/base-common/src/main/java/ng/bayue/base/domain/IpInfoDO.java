package ng.bayue.base.domain;

import ng.bayue.common.BaseDO;

/**
 * 
 * @author fengyts Fri Jul 15 11:38:40 CST 2016
 */

public class IpInfoDO extends BaseDO {

	/**  */
	private static final long serialVersionUID = 1680251308159998286L;

	/** 主键 */
	private Long id;

	/**  */
	private String ipstart;

	/**  */
	private String ipend;

	/** ipstart的int型表示 */
	private Integer ipstartInt;

	/** ipend的int型表示 */
	private Integer ipendInt;

	/**  */
	private Long countryId;

	/**  */
	private String country;

	/**  */
	private Long provinceId;

	/**  */
	private String province;

	/**  */
	private Long cityId;

	/**  */
	private String city;

	/**  */
	private String district;

	/**
	 * 设置 主键
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 设置
	 * 
	 * @param ipstart
	 */
	public void setIpstart(String ipstart) {
		this.ipstart = ipstart;
	}

	/**
	 * 设置
	 * 
	 * @param ipend
	 */
	public void setIpend(String ipend) {
		this.ipend = ipend;
	}

	/**
	 * 设置 ipstart的int型表示
	 * 
	 * @param ipstartInt
	 */
	public void setIpstartInt(Integer ipstartInt) {
		this.ipstartInt = ipstartInt;
	}

	/**
	 * 设置 ipend的int型表示
	 * 
	 * @param ipendInt
	 */
	public void setIpendInt(Integer ipendInt) {
		this.ipendInt = ipendInt;
	}

	/**
	 * 设置
	 * 
	 * @param countryId
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * 设置
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * 设置
	 * 
	 * @param provinceId
	 */
	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	/**
	 * 设置
	 * 
	 * @param province
	 */
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * 设置
	 * 
	 * @param cityId
	 */
	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	/**
	 * 设置
	 * 
	 * @param city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * 设置
	 * 
	 * @param district
	 */
	public void setDistrict(String district) {
		this.district = district;
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
	 * 获取
	 * 
	 * @return ipstart
	 */
	public String getIpstart() {
		return ipstart;
	}

	/**
	 * 获取
	 * 
	 * @return ipend
	 */
	public String getIpend() {
		return ipend;
	}

	/**
	 * 获取 ipstart的int型表示
	 * 
	 * @return ipstartInt
	 */
	public Integer getIpstartInt() {
		return ipstartInt;
	}

	/**
	 * 获取 ipend的int型表示
	 * 
	 * @return ipendInt
	 */
	public Integer getIpendInt() {
		return ipendInt;
	}

	/**
	 * 获取
	 * 
	 * @return countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * 获取
	 * 
	 * @return country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * 获取
	 * 
	 * @return provinceId
	 */
	public Long getProvinceId() {
		return provinceId;
	}

	/**
	 * 获取
	 * 
	 * @return province
	 */
	public String getProvince() {
		return province;
	}

	/**
	 * 获取
	 * 
	 * @return cityId
	 */
	public Long getCityId() {
		return cityId;
	}

	/**
	 * 获取
	 * 
	 * @return city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * 获取
	 * 
	 * @return district
	 */
	public String getDistrict() {
		return district;
	}

}