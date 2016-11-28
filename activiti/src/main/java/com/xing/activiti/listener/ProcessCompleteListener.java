package com.xing.activiti.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

import com.xing.activiti.dao.PurBusOrderMapper;
import com.xing.activiti.entity.PurBusOrder;
import com.xing.activiti.util.ApplicationContextUtils;

/**
 *  执行监听器的使用    
 *  该监听器监听的事件：
 *     1.流程实例的启动和结束
 *     2.节点的开始和结束
 *     3.网关的开始和结束
 *     4.中间事件的开始和结束
 *     5.开始时间结束或结束事件开始
 */
public class ProcessCompleteListener implements ExecutionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6414796179261516690L;
	//通过工具类获取spring容器
	private static ApplicationContext applicationContext=ApplicationContextUtils.
					getApplicationContext();

	@Override
	public void notify(DelegateExecution execution) throws Exception {
		//execution 是流程实例的代理对象
		
		//获取业务标识  ，即采购单id
		String businessKey=execution.getBusinessKey();
		//通过pur_bus_order 的mapper更新pur_bus_order
		
		//从spring容器重得到mapper
		PurBusOrderMapper busOrderMapper=(PurBusOrderMapper) applicationContext.getBean(PurBusOrder.class);
		
		//根据采购单的id更新status状态值为complete
		PurBusOrder purBusOrder=new PurBusOrder();
		purBusOrder.setId(businessKey);//更新记录的id
		purBusOrder.setStatus("complete");//更新status状态值为complete
		
		busOrderMapper.updatePurBusOrder(purBusOrder);
		}
		
		
	}
