package com.xing.activiti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xing.activiti.dao.OrderMapper;
import com.xing.activiti.dao.PurBusOrderMapper;
import com.xing.activiti.entity.PurBusOrder;
import com.xing.activiti.util.ResourceUtil;
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

	@Override
	@Transactional
	public void saveOrder(String userId, OrderCustom orderCustom) {
		//生产采购单id
		String orderId=UUID.randomUUID().toString();
		
		//流程定义的key从配置文件中获取(流程定义的key)
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow", "purchasingProcessDefinitionKey");    //采购流程标识
		
		//业务标识
		String businessKey=orderId;
		//注意：设置流程发起人应该在启动流程之前
		//设置流程发起人
		identityService.setAuthenticatedUserId(userId);
		
		/*
		 * 1.启动流程实例(通过流程定义的key，和业务标识来启动一个流程实例)
		 */
		ProcessInstance processInstance=runtimeService.
				startProcessInstanceByKey(processDefinitionKey, businessKey);
		
		//获取流程实例id
		String processInstanceId=processInstance.getProcessDefinitionId();
	
		//获取流程实例执行id
		String executeID=processInstance.getId();
		
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
}

