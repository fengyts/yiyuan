package ng.bayue.base.dto;

import java.io.Serializable;
import java.util.List;

/**
 * @author fengyts
 *
 */
public class FrontCategoryDTO implements Serializable {

	private static final long serialVersionUID = 2732014096395170115L;

	private Long id;

	private String name;

	/** 是否突出展示:0-是,1-否 */
	private Boolean isHighlight;

	/** 类目级别:1-一级类目,2-二级类目,3-三级类目 */
	private Integer level;

	private String logoUrl;

	/** 子分类 **/
	private List<FrontCategoryDTO> childs;

	/** 是否是固定链接跳转 **/
	private Boolean isUrlLink;

	private String pcUrlLink;

	private String wapUrlLink;

	private String appUrlLink;

	private Integer seq;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsHighlight() {
		return isHighlight;
	}

	public void setIsHighlight(Boolean isHighlight) {
		this.isHighlight = isHighlight;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<FrontCategoryDTO> getChilds() {
		return childs;
	}

	public void setChilds(List<FrontCategoryDTO> childs) {
		this.childs = childs;
	}

	public Boolean getIsUrlLink() {
		return isUrlLink;
	}

	public void setIsUrlLink(Boolean isUrlLink) {
		this.isUrlLink = isUrlLink;
	}

	public String getPcUrlLink() {
		return pcUrlLink;
	}

	public void setPcUrlLink(String pcUrlLink) {
		this.pcUrlLink = pcUrlLink;
	}

	public String getWapUrlLink() {
		return wapUrlLink;
	}

	public void setWapUrlLink(String wapUrlLink) {
		this.wapUrlLink = wapUrlLink;
	}

	public String getAppUrlLink() {
		return appUrlLink;
	}

	public void setAppUrlLink(String appUrlLink) {
		this.appUrlLink = appUrlLink;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}

}
