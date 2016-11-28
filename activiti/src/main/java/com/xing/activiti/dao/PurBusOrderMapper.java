package com.xing.activiti.dao;

import org.apache.ibatis.annotations.Param;

import com.xing.activiti.entity.PurBusOrder;
import com.xing.activiti.vo.OrderCustom;

public interface PurBusOrderMapper {

	/**
	 * 向数据库中插入一条采购单记录
	 * @param orderCustom
	 */
	public void insert(OrderCustom orderCustom);

	/**
	 * 根据订单id来获取订单信息
	 * @param orderId
	 * @return
	 */
	public PurBusOrder getPurBusOrderById(@Param("orderId")String orderId);

	/**
	 * 更新采购单的信息
	 * @param purBusOrder
	 */
	public void updatePurBusOrder(PurBusOrder purBusOrder);
	
}
