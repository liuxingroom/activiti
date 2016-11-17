package com.xing.purchasing.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 采购单的订单表  pur_bus_order 
 */
public class PurBusOrder implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7049751810105753999L;
	
	/**采购单状态*/
	public static final String STATUS_TYPE_PUR_BUS_ORDER="purBusOrder";
	/**提交采购单*/
	public static final String STATUS_CODE_TJCGD="0";
	/**部门经理审核*/
	public static final String STATUS_CODE_BMJLSH="1";
	/**总经理审核*/
	public static final String STATUS_CODE_ZJLSH="2";
	/**财务审核*/
	public static final String STATUS_CODE_CWSH="3";
	/**结算*/
	public static final String STATUS_CODE_JS="4";
	/**入库*/
	public static final String STATUS_CODE_RK="5";
	
	
	/**采购单id*/
	private String id;
	/**采购单名称*/
	private String name;
	/**采购单金额*/
	private Double price;
	/**采购单创建时间*/
	private Date createTime;
	/**采购单状态*/
	private String status;
	/**创建人*/
	private String userId;
	/**采购单所对应的流程实例id*/
	private String processInstanceId;
	/**采购单结束时间*/
	private Date endTime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	
}
