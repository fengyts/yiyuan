package ng.bayue.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 * 分页工具类
 * </pre>
 *
 * @author fengyts
 * @version $Id: Page.java, v 0.1 2016年8月7日 下午1:54:20 fengyts Exp $
 */
public class Page<T> implements Serializable {

	private static final long serialVersionUID = -932774485663554790L;

	private static final int DEFAULT_PAGE = 1;// 默认起始页数
	private static final int DEFAULT_PAGESIZE = 10;// 默认每页数量

	private int pageNo = DEFAULT_PAGE;
	private int pageSize = DEFAULT_PAGESIZE;
	private int totalCount = -1;
	private List<T> list;

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalCount() {
		return totalCount < 0 ? 0 : totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<T> getList() {
		return totalCount == -1 ? new ArrayList<T>() : list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	/** 获取总页数 */
	public int getTotalPageCount() {
		int totalPageCount = totalCount / pageSize;
		return totalCount % pageSize > 0 ? ++totalPageCount : totalPageCount;
	}

	/** 是否有下一页 */
	public boolean hasNextPage() {
		return ++pageNo <= getTotalPageCount();
	}

	/** 是否有上一页 */
	public boolean hasPrePage() {
		return --pageNo >= 1;
	}

	/** 获取下一页 */
	public int getNextPage() {
		return hasNextPage() ? ++pageNo : pageNo;
	}

	/** 获取上一页 */
	public int getPrePage() {
		return hasPrePage() ? --pageNo : pageNo;
	}

}
