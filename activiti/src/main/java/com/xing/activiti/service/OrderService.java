package com.xing.activiti.service;

import java.util.List;

import com.xing.activiti.vo.OrderAuditCustom;
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

	/**
	 * 通过用户id和任务id来拾取组任务
	 * @param taskId
	 * @param userId
	 */
	public void saveClaimTask(String taskId, String userId);

	/**
	 * 查询组任务列表
	 * @param userId
	 * @return
	 * @throws Exception 
	 */
	public List<OrderCustom> findOrderGroupTaskList(String userId) throws Exception;

	/**
	 * 提交采购单
	 * @param userId
	 * @param taskId
	 * @throws Exception 
	 */
	public void saveOrderSubmitStauts(String userId, String taskId) throws Exception;

	/**
	 * 提交审核信息
	 * @param taskId
	 * @param userId
	 * @param orderId
	 * @param auditType
	 * @param orderAuditCustom
	 */
	public void saveOrderAuditStatus(String taskId, String userId,
			String orderId, String auditType, OrderAuditCustom orderAuditCustom);

}
