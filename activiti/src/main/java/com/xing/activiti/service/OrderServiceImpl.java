package com.xing.activiti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xing.activiti.dao.OrderMapper;
import com.xing.activiti.dao.PurBusOrderAuditMapper;
import com.xing.activiti.dao.PurBusOrderMapper;
import com.xing.activiti.entity.PurBusOrder;
import com.xing.activiti.entity.PurBusOrderAudit;
import com.xing.activiti.util.ResourceUtil;
import com.xing.activiti.vo.OrderAuditCustom;
import com.xing.activiti.vo.OrderCustom;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Resource
	RuntimeService runtimeService;
	
	@Resource
	TaskService taskService;
	
	@Resource
	OrderMapper orderMapper;
	
	@Resource
	IdentityService identityService;
	
	@Resource
	PurBusOrderMapper purBusOrderMapper;
	
	@Resource
	PurBusOrderAuditMapper purBusOrderAuditMapper;
	
	@Resource
	HistoryService historyService;
	
	@Resource
	RepositoryService repositoryService;

	@Override
	@Transactional
	public void saveOrder(String userId, OrderCustom orderCustom) {
		//生产采购单id
		String orderId=UUID.randomUUID().toString();
		
		//流程定义的key从配置文件中获取(流程定义的key)
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey");    //采购流程标识
		
		//业务标识
		//业务标识就是改采购单的id
		String businessKey=orderId;
		//注意：设置流程发起人应该在启动流程之前
		//设置流程发起人
		identityService.setAuthenticatedUserId("zhangsan");
		
		/*
		 * 1.启动流程实例(通过流程定义的key，和业务标识来启动一个流程实例)
		 */
		ProcessInstance processInstance=runtimeService.
				startProcessInstanceByKey(processDefinitionKey, businessKey);
		
		//获取流程实例id
		String processInstanceId=processInstance.getProcessInstanceId();
	
		//获取流程实例执行id
		String executeID=processInstance.getId();
		
		//获取当前正在运行的节点信息
		String activityId=processInstance.getActivityId();
		
		//获取业务标识
		String businesskey=processInstance.getBusinessKey();
		
		/*
		 * 2.向采购单中插入一条记录
		 */
		//设置采购单id
		orderCustom.setId(orderId);
		//创建采购单的时间
		orderCustom.setCreatetime(new Date());
		//设置采购人
		orderCustom.setUserId(userId);
		
		//设置流程实例的id
		orderCustom.setProcessinstanceId(processInstanceId);
		
		purBusOrderMapper.insert(orderCustom);
	}

	@Override
	public List<OrderCustom> findOrderTaskList(String userId) throws Exception {
		
		//获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey"); 
		
		//创建查询对象
		TaskQuery taskQuery=taskService.createTaskQuery();
		//设置查询条件（查询当前登录人的任务）
		taskQuery.taskAssignee(userId);
		//设置查询条件（查询改流程实例的key）
		taskQuery.processDefinitionKey(processDefinitionKey);
		//查询任务列表
		List<Task> list=taskQuery.list();
		
		//需要返回的内容  ： 任务id、任务标识、任务名称、任务负责人、采购单名称、采购金额
		List<OrderCustom> orderList=new ArrayList<OrderCustom>();
		
		for(Task task:list){
			OrderCustom orderCustom=new OrderCustom();
			
			//流程实例id
			String processInstanceId=task.getProcessInstanceId();
			//根据流程实例id找到流程实例对象
			ProcessInstance processInstance=runtimeService.
					createProcessInstanceQuery().
					processInstanceId(processInstanceId).singleResult();
			//从流程实例中获取业务标识  businessKey
			String businessKey=processInstance.getBusinessKey();
			//根据businessKey查询业务系统
			//采购单id
			String orderId=businessKey;
			PurBusOrder purBusOrder=purBusOrderMapper.getPurBusOrderById(orderId);
			//获取采购单名称，采购金额，等采购信息
			//将purBusOrder 内容拷贝到OrderCustom中
			/**
			 * 对象拷贝 第一种方法
			 */
			//第一个对象是目标对象  第二个参数为源对象
			PropertyUtils.copyProperties(orderCustom, purBusOrder);
			/**
			 * 对象拷贝 第二种方法
			 */
			//第一个对象是目标对象  第二个参数为源对象
//			BeanUtils.copyProperties(orderCustom, purBusOrder);
		
			
			//下面向orderCustom开始设置任务信息
			//任务id、任务标识、任务名称、任务负责人
			
			//任务id
			orderCustom.setTaskId(task.getId());
			//任务标识
			orderCustom.setTaskDefinitionKey(task.getTaskDefinitionKey());
			//任务名称
			orderCustom.setTaskName(task.getName());
			//任务负责人
			orderCustom.setAssignee(task.getAssignee());
			
			orderList.add(orderCustom);
		}
		return orderList;
	}

	@Override
	public void saveClaimTask(String taskId, String userId) {
		//根据候选人和组任务id查询，如果有记录说明该候选人有资格拾取改任务
		Task task=taskService.createTaskQuery().
		                      taskId(taskId).
		                      taskCandidateUser(userId).
		                      singleResult();
		
		if(task!=null){
			//组任务拾取
			taskService.claim(taskId, userId);
			System.out.println("拾取成功");
		}
	}

	@Override
	public List<OrderCustom> findOrderGroupTaskList(String userId) throws Exception {
		//获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey");
		//根据候选人和流程定义的key来获取组任务信息
		List<Task> list=taskService.createTaskQuery().
				taskCandidateUser(userId).
				processDefinitionKey(processDefinitionKey).
				list();
		
		List<OrderCustom> orderList=new ArrayList<OrderCustom>();
		for(Task task:list){
			OrderCustom orderCustom=new OrderCustom();
			
			//获取流程实例id
			String processInstanceId=task.getProcessInstanceId();
			
			//根据流程实例id来获取流程实例的对象
			ProcessInstance instance=runtimeService.
					createProcessInstanceQuery().
					processInstanceId(processInstanceId).
					singleResult();
			
			//从流程实例对象中获取业务标识（businessKey）
			String businessKey=instance.getBusinessKey();
			
			//根据businessKey查询业务系统
			//采购单id
			String orderId=businessKey;
			//根据采购单id来获取采购信息
			PurBusOrder purBusOrder=purBusOrderMapper.getPurBusOrderById(orderId);
			//将purBusOrder内容拷贝到OrderCustom中
			BeanUtils.copyProperties(orderCustom, purBusOrder);
		
			
			//下边orderCustom开始设置组任务信息
			//组任务id、任务标识、任务名称
			//任务id
			orderCustom.setTaskId(task.getId());
			// 任务标识
			orderCustom.setActivityId(task.getTaskDefinitionKey());
			//任务名称
			orderCustom.setTaskName(task.getName());
			
			orderList.add(orderCustom);
		}
		return orderList;
	}

	@Override
	public void saveOrderSubmitStauts(String userId, String taskId) throws Exception {

		//根据任务id和assignee查询改任务
		Task task=taskService.createTaskQuery().
				             taskId(taskId).
				             taskAssignee(userId).
				             singleResult();
		if(task !=null){
			//说明当前用户该任务的办理人
			OrderCustom orderCustom=new OrderCustom();
			
			//采购单信息获取
			
			//根据任务对象  获取流程实例id
			String processInstanceId=task.getProcessInstanceId();
			//查询流程实例对象
			ProcessInstance processInstance=runtimeService.
								createProcessInstanceQuery().
								processInstanceId(processInstanceId).
								singleResult();
			
			//从流程实例对象中获取businessKey 
			String businessKey=processInstance.getBusinessKey();
			PurBusOrder purBusOrder=purBusOrderMapper.getPurBusOrderById(businessKey);
			BeanUtils.copyProperties(orderCustom, purBusOrder);
			
			//设置流程变量   值 为orderCustom即采购单信息
			Map<String , Object> variables=new HashMap<String, Object>();
			variables.put("order", orderCustom);
			
			taskService.complete(taskId,variables);
		}
	}

	@Override
	@Transactional
	public void saveOrderAuditStatus(String taskId, String userId,
			String orderId, String auditType, OrderAuditCustom orderAuditCustom) {
		// 提交审核向pur_bus_order_audit表插入一条记录
		orderAuditCustom.setId(UUID.randomUUID().toString());
		//设置审核用户信息
		orderAuditCustom.setUserId(userId);
		//设置用户审核类型
		orderAuditCustom.setAuditType(auditType);
		//审核所属的采购单
		orderAuditCustom.setOrderId(orderId);
		//审核时间
		orderAuditCustom.setCreateTime(new Date());
		//向数据库中插入一条数据
		purBusOrderAuditMapper.insert(orderAuditCustom);
		
		// 调用activiti的api执行任务完成操作，将流程向后推进一步

		// 根据任务id和assignee查询该任务
		
		Task task=taskService.createTaskQuery().
				              taskId(taskId).
				              taskAssignee(userId).
				              singleResult();
		
		if(task!=null){
			// 说明assignee是该任务的办理人，有权限完成
			Map<String , Object> variables=new HashMap<String, Object>();
			//根据auditType判断是几级审核
			if(auditType.equals(PurBusOrderAudit.STATUS_CODE_ONE_AUDIT)){//如果是一级审核
				variables.put(PurBusOrderAudit.STATUS_CODE_ONE_AUDIT, orderAuditCustom);
				
			}else if(auditType.equals(PurBusOrderAudit.STATUS_CODE_TWO_AUDIT)){ //如果是二级审核
				variables.put(PurBusOrderAudit.STATUS_CODE_TWO_AUDIT, orderAuditCustom);
				
			}else if(auditType.equals(PurBusOrderAudit.STATUS_CODE_THREE_AUDIT)){//如果是三级审核
				variables.put(PurBusOrderAudit.STATUS_CODE_THREE_AUDIT, orderAuditCustom);
			}
			
			//提交审核时，设置流程变量，变量就是审核信息
			taskService.complete(taskId, variables);
		}
	}

	@Override
	public List<OrderCustom> findActivityOrderList() throws Exception {
		//获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey");
		
		
		/*获取流程实例查询对象       并设置查询条件以及排序字段*/
		ProcessInstanceQuery processInstanceQuery=runtimeService.createProcessInstanceQuery().
				processDefinitionKey(processDefinitionKey).
				orderByProcessInstanceId().
				desc();
		
		//获取当前列表
		List<ProcessInstance> list=processInstanceQuery.list();
		//单独定义一个list，list中包括自定义的pojo(OrderCustom包括流程实例信息和业务系统信息)
		List<OrderCustom> orderList=new ArrayList<OrderCustom>();
		
		for(ProcessInstance instance:list){
			//定义一个包装对象
			OrderCustom orderCustom=new OrderCustom();
			
			// 比如key为采购流程，这个key就是采购单id
			String businessKey=instance.getBusinessKey();
			
			String orderId=businessKey;
			//根据业务标识businessKey来获取采购单信息
			PurBusOrder  purBusOrder=purBusOrderMapper.getPurBusOrderById(orderId);
			
			// 向orderCustom中填充流程实例 信息
			// 当前运行的结点
			BeanUtils.copyProperties(orderCustom, purBusOrder);
			orderCustom.setActivityId(instance.getActivityId());
			
			orderList.add(orderCustom);
		}
		
		return orderList;
	}

	@Override
	public List<OrderCustom> findOrderTaskListByPid(String processInstanceId) {
		//获取流程定义的key
		String processDefinitionKey= ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey");
		
		//创建历史任务查询对象
		HistoricTaskInstanceQuery historicTaskInstanceQuery =historyService.createHistoricTaskInstanceQuery();
		
		//设置查询对象（流程定义的key）
		historicTaskInstanceQuery.processDefinitionKey(processDefinitionKey);
		//设置查询对象（流程实例的id）
		historicTaskInstanceQuery.processInstanceId(processInstanceId);
		
		//添加排序，按照任务执行时间先后排序
		historicTaskInstanceQuery.orderByHistoricTaskInstanceStartTime().asc();
		
		//获取实例流程实例集合
		List<HistoricTaskInstance> list=historicTaskInstanceQuery.list();
		
		//即使这里只查询activiti中的数据,不关联查询业务的数据，也要单独定义
		//List<OrderCustom> 为了通过service和activiti和控制层隔离（达到解耦的效果）
		List<OrderCustom> orderList=new ArrayList<OrderCustom>();
		
		for(HistoricTaskInstance historicTaskInstance:list){
			OrderCustom orderCustom=new OrderCustom();
			
			orderCustom.setTaskId(historicTaskInstance.getId()); //任务id
			orderCustom.setTaskName(historicTaskInstance.getName());  //任务名称
			orderCustom.setAssignee(historicTaskInstance.getAssignee());  //任务负责人
			orderCustom.setTaskDefinitionKey(historicTaskInstance.getTaskDefinitionKey()); //任务标识
			orderCustom.setTask_startTime(historicTaskInstance.getStartTime());    //开始时间
			orderCustom.setTask_endTime(historicTaskInstance.getEndTime());        //结束时间
			
		    orderList.add(orderCustom);
		}
		return orderList;
	}

	@Override
	public List<OrderCustom> findFinishedOrderList() throws Exception {
		
		//获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue("diagram.purchasingflow", "purchasingProcessDefinitionKey");
		
		//创建历史流程实例 查询对象
		HistoricProcessInstanceQuery historicProcessInstanceQuery=historyService.createHistoricProcessInstanceQuery();
		
		//设置查询条件
		//指定流程定义key，只查询某个业务流程的实例
		historicProcessInstanceQuery.processDefinitionKey(processDefinitionKey);
		
		//设置只查询已完成的流程实例信息
		historicProcessInstanceQuery.finished();
		
		//添加排序，按结束时间降序
		historicProcessInstanceQuery.orderByProcessInstanceEndTime().desc();
		
		List<HistoricProcessInstance> list=historicProcessInstanceQuery.list();
		
		List<OrderCustom> orderList=new ArrayList<OrderCustom>();
		for(HistoricProcessInstance historicProcessInstance:list){
			OrderCustom orderCustom=new OrderCustom();
			//业务标识
			String businessKey=historicProcessInstance.getBusinessKey();
			//根据businessKey（业务标识）获取采购单信息
			PurBusOrder purBusOrder=purBusOrderMapper.getPurBusOrderById(businessKey);
			
			//将采购单信息拷贝到OrderCustom中
			BeanUtils.copyProperties(orderCustom, purBusOrder);
			
			//设置activiti的数据到ordercustom中
			//开始时间
			orderCustom.setProcessInstance_startTime(historicProcessInstance.getStartTime());
			
			//结束时间
			orderCustom.setProcessInstance_endTime(historicProcessInstance.getEndTime());
			
			orderList.add(orderCustom);
		}
		
		return orderList;
	}

	@Override
	public void saveSettlement(String taskId, String userId) {
		/*获取当前要办理的任务*/
		Task task=taskService.createTaskQuery().
				taskAssignee(userId).
				taskId(taskId).
				singleResult();
		if(task!=null){//如果当前任务 存在
			taskService.complete(taskId);
		}
		
	}
	

	@Override
	public void storage(String userId, String taskId) {
		/*获取当前要办理的任务*/
		Task task=taskService.createTaskQuery().
				taskAssignee(userId).
				taskId(taskId).
				singleResult();
		
		if(task!=null){//如果当前任务不为空
			taskService.complete(taskId);
		}
	}

	@Override
	public void suspendOrActivateProcessDefinition() {
		//获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue("diagram.purchasingflow", "purchasingProcessDefinitionKey");
		//获取流程定义查询对象
		ProcessDefinitionQuery definitionQuery= 
				repositoryService.createProcessDefinitionQuery();
		definitionQuery.processDefinitionKey(processDefinitionKey);
		
		ProcessDefinition processDefinition=definitionQuery.singleResult();
		
		//判断流程定义是否挂起
		boolean suspend=processDefinition.isSuspended();
		
		if(suspend){//如果该流程已经挂起
			//激活该流程定义所属的所有的流程实例
			repositoryService.activateProcessDefinitionByKey(processDefinitionKey);
		}else{
			//挂起改流程定义所属的所有的流程实例
			repositoryService.suspendProcessDefinitionByKey(processDefinitionKey);
		}
		
	}

	@Override
	public void suspendOrActivateProcessInstance(String processInstanceId) {
		//获取流程实例的查询对象
		ProcessInstanceQuery processInstanceQuery=
				runtimeService.createProcessInstanceQuery();
		
		//获取流程实例对象
		ProcessInstance processInstance=processInstanceQuery.
				processInstanceId(processInstanceId).
				singleResult();
		//判断该流程实例的状态（激活、挂起）
		boolean suspend=processInstance.isSuspended();
		
		if(suspend){//如果挂起
			//激活该流程实例
			runtimeService.activateProcessInstanceById(processInstanceId);
		}else{
			//挂起该流程实例
			runtimeService.suspendProcessInstanceById(processInstanceId);
		}
		
	}

	@Override
	public void setGolbalVariable() {
		String processDefinitionKey=ResourceUtil.getValue("diagram.purchasingflow", "purchasingProcessDefinitionKey");
		String businessKey="";
		String taskId="";
		String executionId="";
		String variableName="";
		String value="";
		/*
		 * 第一种设置   在启动流程时
		 */
		Map<String, Object> variables=new HashMap<String, Object>();
		runtimeService.startProcessInstanceByKey(processDefinitionKey, businessKey, variables);
		
		/*
		 * 第二种设置方式    在完成任务时
		 */
		taskService.complete(taskId, variables);
		
		/* 第三种
		 * 通过流程实例的id来设置流程变量(设置一个变量)
		 * executionId ：流程实例的执行id
		 * variableName  变量名
		 * value  变量值
		 */
		runtimeService.setVariable(executionId, variableName, value);
		//设置多个变量
		runtimeService.setVariables(executionId, variables);
	
		/*
		 * 第四种
		 * 通过当前代办任务的id 来设置流程变量  (设置一个变量)
		 * executionId ：流程实例的执行id
		 * variableName  变量名
		 * value  变量值
		 */
		taskService.setVariable(taskId, variableName, value);
		//设置多个变量
		runtimeService.setVariables(executionId, variables);

	}

	@Override
	public void setLocalVariable() {
	
		Map<String, Object> variables=new HashMap<String, Object>();
		String taskId="";
		String variableName="";
		String value="";
		
		//注意：当局部变量消失时在使用改变量  会报错
		/*
		 * 第一种局部变量设置方式   
		 *  通过当前任务id来设置局部变量   (设置一个局部变量)
		 *  variableName 局部变量的名字
		 *  value  局部变量的值
		 */
		taskService.setVariableLocal(taskId, variableName, value);
		//设置多个局部变量
		taskService.setVariablesLocal(taskId, variables);
		
	}	
}

