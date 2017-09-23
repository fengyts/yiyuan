package ng.bayue.backend.common;

import java.io.Serializable;

public class BaseDO implements Serializable{

	private static final long serialVersionUID = -5501953392399206826L;

	/** 起始页 */
	private int startPage=1;

	/** 每页记录数 */
	private int pageSize=10;

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getStart() {
		if (startPage < 0 || pageSize < 0) {
			return 0;
		} else {
			return ((startPage - 1) * pageSize);
		}
	}


}
