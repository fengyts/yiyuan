package ng.bayue.glog;

import java.io.Serializable;
import java.util.Date;

public class OperationLogDO implements Serializable {

	/**  */
	private static final long serialVersionUID = -1101331637754445171L;

	private Long id;

	private String operationType;

	private Long operationUserId;

	private Long operationRecordId;

	private String operationFieldName;

	private String operationClassName;

	private String operationBefore;

	private String operationAfter;

	private Date operationTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public Long getOperationUserId() {
		return operationUserId;
	}

	public void setOperationUserId(Long operationUserId) {
		this.operationUserId = operationUserId;
	}

	public Long getOperationRecordId() {
		return operationRecordId;
	}

	public void setOperationRecordId(Long operationRecordId) {
		this.operationRecordId = operationRecordId;
	}

	public String getOperationFieldName() {
		return operationFieldName;
	}

	public void setOperationFieldName(String operationFieldName) {
		this.operationFieldName = operationFieldName;
	}

	public String getOperationClassName() {
		return operationClassName;
	}

	public void setOperationClassName(String operationClassName) {
		this.operationClassName = operationClassName;
	}

	public String getOperationBefore() {
		return operationBefore;
	}

	public void setOperationBefore(String operationBefore) {
		this.operationBefore = operationBefore;
	}

	public String getOperationAfter() {
		return operationAfter;
	}

	public void setOperationAfter(String operationAfter) {
		this.operationAfter = operationAfter;
	}

	public Date getOperationTime() {
		return operationTime;
	}

	public void setOperationTime(Date operationTime) {
		this.operationTime = operationTime;
	}

}
