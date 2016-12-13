package com.xing.activiti.listener;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.springframework.context.ApplicationContext;

import com.xing.activiti.dao.PurBusOrderMapper;
import com.xing.activiti.entity.PurBusOrder;
import com.xing.activiti.util.ApplicationContextUtils;

/**
 *	任务监听
 */
public class ProcessCompleteTaskListener implements TaskListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8874380323053678570L;

	@Override
	public void notify(DelegateTask delegateTask) {
		//获取流程实例的id
		String processInstancdId=delegateTask.getProcessInstanceId();
		//获取web容器对象
		ApplicationContext applicationContext=ApplicationContextUtils.getApplicationContext();

		//获取流程实例查询对象
		ProcessInstanceQuery processInstanceQuery=applicationContext.getBean(ProcessInstanceQuery.class);
		processInstanceQuery.processInstanceId(processInstancdId);
		//获取流程实例对象
		ProcessInstance processInstance=processInstanceQuery.singleResult();
		
		//获取业务标识
		String bussinessKey=processInstance.getBusinessKey();
		
		//从spring容器重得到mapper
		PurBusOrderMapper busOrderMapper=applicationContext.getBean(PurBusOrderMapper.class);
		
		//根据采购单的id更新status状态值为complete
		PurBusOrder purBusOrder=new PurBusOrder();
		purBusOrder.setId(bussinessKey);//更新记录的id
		purBusOrder.setStatus("complete");//更新status状态值为complete
		
		busOrderMapper.updatePurBusOrder(purBusOrder);
		
	}
	
}
