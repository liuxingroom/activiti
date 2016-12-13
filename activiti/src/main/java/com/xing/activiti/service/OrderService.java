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

	/**
	 * 查看当前运行的流程
	 * 所谓当前运行的流程     即: 查看当前启动的流程实例信息
	 * @return
	 * @throws Exception 
	 */
	public List<OrderCustom> findActivityOrderList() throws Exception;

	/**
	 * 根据流程定义的id来获取历史任务信息
	 * @param processInstanceId
	 * @return
	 */
	public List<OrderCustom> findOrderTaskListByPid(String processInstanceId);
	
	/**
	 * 查询已结束的流程实例
	 * @return
	 * @throws Exception 
	 */
	public List<OrderCustom> findFinishedOrderList() throws Exception;

	/**
	 * 财务结算
	 * @param taskId
	 * @param userId
	 */
	public void saveSettlement(String taskId, String userId);

	/**
	 * 入库
	 * @param userId
	 * @param taskId
	 */
	public void storage(String userId, String taskId);

	
	/**
	 * 根据流程定义的key来激活改流程
	 * 如果流程定义暂停或激活，不能再启动该流程定义的流程实例了!!!
	 */
	public void suspendOrActivateProcessDefinition();
	
	
	/**
	 * 根据流程实例id来暂停或者激活该流程实例
	 */
	public void suspendOrActivateProcessInstance(String processInstanceId);
}
