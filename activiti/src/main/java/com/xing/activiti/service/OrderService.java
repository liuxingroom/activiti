package com.xing.activiti.service;

import java.util.List;

import com.xing.activiti.vo.OrderCustom;

public interface OrderService {

	/**
	 * 保存采购单
	 * @param userId
	 * @param orderCustom
	 */
	public void saveOrder(String userId, OrderCustom orderCustom);

	/**
	 * 获取当前用户的 任务
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public List<OrderCustom> findOrderTaskList(String userId) throws Exception;

}
