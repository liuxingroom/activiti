package com.xing.activiti.entity;

import java.io.Serializable;

/**
 * 采购单审核信息  pur_bus_order_audit
 */
public class PurBusOrderAudit implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 602329200272889977L;
	
	/**审核类型*/
	public static final String STATUS_TYPE_AUDIT="auditType";
	/**一级审核*/
	public static final String STATUS_CODE_ONE_AUDIT="firstAudit";
	/**二级审核*/
	public static final String STATUS_CODE_TWO_AUDIT="secondAudit";
	/**三级审核*/
	public static final String STATUS_CODE_THREE_AUDIT="thirdAudit";
	
	/**审核状态*/
	public static final String STATUS_TYPE_AUDITSTATUS="auditStatus";
	/**不通过*/
	public static final String STATUS_CODE_NOT_THROUGH="0";
	/**通过*/
	public static final String STATUS_CODE_THROUGH="1";
	
	
	
	/**审核记录id*/
	private String id;
	/**审核人id*/
	private String userId;
	/**采购单id*/
	private String orderId;
	/**采购单id*/
	private String auditInfo;
	/**审核类型   
	 * 一、二、三级审核
	 * */
	private String auditType;
	/**审核结果
	 * 通过，不通过
	 * */
	private String status;
	/**审核时间*/
	private String createTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(String auditInfo) {
		this.auditInfo = auditInfo;
	}
	public String getAuditType() {
		return auditType;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
}
