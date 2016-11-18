package com.xing.activiti.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.xing.activiti.util.ResourceUtil;


@Controller
@RequestMapping("/flow")
public class FlowController {
	
	@Resource
	RepositoryService repositoryService;
	
	@Resource
	RuntimeService runtimeService;
	
	/**
	 * 跳转流程不部署界面
	 * @param model
	 * @return
	 */
	@RequestMapping("/deployProcess.action")
	public String deployProcess(Model model){
		return "flow/deployProcess";
	}
	
	/**
	 * 流程定义部署
	 */
	@RequestMapping("/deployProcessSubmit.action")
	public String deployProcessSubmit(MultipartFile resource_bpmn,MultipartFile resource_png)throws Exception{
		//获取bpmn文件信息
		String resourceName_bpmn=resource_bpmn.getOriginalFilename();
		InputStream inputStream_bpmn=resource_bpmn.getInputStream();
		
		//获取png文件信息
		String resourceName_png=resource_png.getOriginalFilename();
		InputStream inputStream_png=resource_png.getInputStream();
		
		/**
		 *	流程定义部署
		 */
		Deployment deployment=repositoryService.createDeployment()
												.addInputStream(resourceName_bpmn, inputStream_bpmn)
											    .addInputStream(resourceName_png, inputStream_png)
											    .deploy();
		
		//流程对象id
		System.out.println("部署id：  "+deployment.getId());
		System.out.println("部署时间 ：  "+deployment.getDeploymentTime());
		
		return "redirect:queryProcessDefinition.action";
	}
	
	@RequestMapping("/queryProcessDefinition.action")
	public String queryProcessDefinition(Model model,HttpServletRequest request){
		//虎获取流程定义的key
		String processDefinitionKey=ResourceUtil.getValue(
				"diagram.purchasingflow","purchasingProcessDefinitionKey");   //采购流程标识
		
		//创建查询对象
		ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery();
		
		//设置查询条件
		processDefinitionQuery.processDefinitionKey(processDefinitionKey);
		
		
		List<ProcessDefinition> list= processDefinitionQuery.list();
		model.addAttribute("list", list);
		return "flow/queryProcessDefinition";
	}
	
	//删除流程定义
	@RequestMapping("/deleteProcessDefinition.action")
	public String deleteProcessDefinition(String processDefinitionId){
		//格局流程定义查询流程定义对象
		ProcessDefinitionQuery processDefinitionQuery=repositoryService.createProcessDefinitionQuery();
		//获取流程定义对象
		ProcessDefinition definition=processDefinitionQuery.processDefinitionId(processDefinitionId).singleResult();
		
		//获取部署id
		String deploymentId=definition.getDeploymentId();
		// 级联删除(级联删除会   删除该流程定义的所有信息)
		repositoryService.deleteDeployment(deploymentId, true);
		return "redirect:queryProcessDefinition.action";
	}
	
	/**
	 * 流程定义资源查看
	 * @throws Exception 
	 */
	@RequestMapping("/queryProcessDefinitionResource.action")
	public void queryProcessDefinitionResource(HttpServletResponse response,
			String processDefinitionId,String resourceType) throws Exception{
		
		//根据流程定义的id 来获取流程实例对象
		ProcessDefinition processDefinition=repositoryService.
				createProcessDefinitionQuery().
				processDefinitionId(processDefinitionId).
				singleResult();
		//获取流程定义id
		String deploymentId=processDefinition.getDeploymentId();
		
		String resourceName="";
		if(resourceType.equals("bpmn")){//如果是bpmn文件
			resourceName=processDefinition.getResourceName();
		}else if(resourceType.equals("png")){
			resourceName=processDefinition.getDiagramResourceName();
		}
		//将文件读到io流中
		InputStream inputStream=repositoryService.getResourceAsStream(deploymentId, resourceName);
		
		int len=-1;
		byte [] b=new byte[1024];
		//遍历io流并将信息输出到浏览器上
		while((len=inputStream.read(b, 0, 1024))!=-1){
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	//动态图形
	//当前运行流程中当前节点图形
	@RequestMapping("/queryActivityMap.action")
	public String queryActivitiMap(Model model,String processInstanceId){
		//根据流程实例id查询出流程实例的对象，从对象中  获取流程定义id  processDefinitionId
		ProcessInstance processInstance=runtimeService.
				createProcessInstanceQuery().
				processInstanceId(processInstanceId).
				singleResult();
		
		//获取流程定义的id
		String processDefinitionId=processInstance.getProcessDefinitionId();
		
		//将流程定义的id传递到页面，用于图形显示
		model.addAttribute("processDefinitionId", processDefinitionId);
		
		//根据流程实例的id（processInstanceId）来获取当前节点
		String activityId=processInstance.getActivityId();
		
		//根据流程定义id查询           流程定义的实体对象
		ProcessDefinitionEntity processDefinitionEntity=(ProcessDefinitionEntity) repositoryService.
				getProcessDefinition(processDefinitionId);
		
		//从流程定义 实体对象查询结点的坐标和宽高
		ActivityImpl activityImpl=processDefinitionEntity.findActivity(activityId);
		int activity_x= activityImpl.getX();//坐标
		int activity_y = activityImpl.getY();//坐标
		int activity_width =  activityImpl.getWidth();//宽
		int activity_height = activityImpl.getHeight();//高
		
		model.addAttribute("activity_x",activity_x);
		model.addAttribute("activity_y", activity_y);
		model.addAttribute("activity_width", activity_width);
		model.addAttribute("activity_height", activity_height);
		
		return "flow/queryActivityMap";
	}
	
}
