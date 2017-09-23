/**
 * 
 */
package ng.bayue.snatch.dto;

import java.io.Serializable;

/**
 * 与前台交互的返回数据
 * 
 * @author 王朝峰
 * @version 2015年1月21日
 * 
 */
public class ReturnData implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8181125287320256617L;

	public ReturnData(int dateSize, Object data) {
		this.data = data;
		this.dateSize = dateSize;
	}

	/* 日期数 */
	private int dateSize;
	/** 附带数据 */
	private Object data;

	public int getDateSize() {
		return dateSize;
	}

	public Object getData() {
		return data;
	}

}
