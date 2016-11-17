package com.xing.activiti.controller;

import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
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
		
		return "";
	}
}
