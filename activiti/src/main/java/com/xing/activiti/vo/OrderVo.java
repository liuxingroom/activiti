package com.xing.activiti.vo;

/**
 * 
 * <p>
 * Title: OrderVo
 * </p>
 * <p>
 * Description: 包装对象
 * </p>
 * <p>
 * Company: www.itcast.com
 * </p>
 * 
 * @author 传智.燕青
 * @date 2014-12-21下午4:15:06
 * @version 1.0
 */
public class OrderVo {
	// 采购单信息
	private OrderCustom orderCustom;
	
	//采购单审核信息
	private OrderAuditCustom orderAuditCustom;

	public OrderCustom getOrderCustom() {
		return orderCustom;
	}

	public void setOrderCustom(OrderCustom orderCustom) {
		this.orderCustom = orderCustom;
	}

	public OrderAuditCustom getOrderAuditCustom() {
		return orderAuditCustom;
	}

	public void setOrderAuditCustom(OrderAuditCustom orderAuditCustom) {
		this.orderAuditCustom = orderAuditCustom;
	}
	
	
}
