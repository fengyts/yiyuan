package ng.bayue.backend.util;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 * @author 喻文杰 2014年11月28日14:29:52
 *
 */
public class AjaxBean implements Serializable{
	/**
	 * <pre>
	 * 
	 * </pre>
	 */
	private static final long serialVersionUID = 3057433573572633031L;

	private boolean state;
	private String title;
	private String message;
	private String url;
	private String mobile;
	private String documentKey;
	private Integer errorCode;
	
	private  Map<String,Object>  obj;
	public boolean getState() {
		return state;
	}
	
	public void success(){
		this.state = true;
	}
	
	public void success(Map<String,Object> obj){
		this.state = true;
		this.obj = obj;
	}
	
	public void error(){
		this.state = false;
	}
	
	public void error(String message){
		error();
		this.message = message;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}
	
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Map<String, Object> getObj() {
		return obj;
	}
	public void setObj(Map<String, Object> obj) {
		this.obj = obj;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getDocumentKey() {
		return documentKey;
	}
	public void setDocumentKey(String documentKey) {
		this.documentKey = documentKey;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AjaxBean [state=");
		builder.append(state);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
