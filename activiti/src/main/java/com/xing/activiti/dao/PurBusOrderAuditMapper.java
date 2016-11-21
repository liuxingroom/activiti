package com.xing.activiti.dao;

import com.xing.activiti.vo.OrderAuditCustom;

public interface PurBusOrderAuditMapper {

	/**
	 * 向审核记录表中插入一条数据
	 * @param orderAuditCustom
	 */
	public void insert(OrderAuditCustom orderAuditCustom);

}
