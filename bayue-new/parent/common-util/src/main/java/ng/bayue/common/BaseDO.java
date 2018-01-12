package ng.bayue.common;

import java.io.Serializable;

public class BaseDO implements Serializable {

	private static final long serialVersionUID = -2860640785889815618L;

	private static final int DEFAULT_PAGENO = 1;
	private static final int DEFAULT_PAGESIZE = 10;

	/** 起始页 */
	private Integer startPage = DEFAULT_PAGENO;

	/** 每页记录数 */
	private Integer pageSize = DEFAULT_PAGESIZE;

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		return startPage <= 0 || pageSize <= 0 ? 0 : (startPage - 1) * pageSize;
	}

}
