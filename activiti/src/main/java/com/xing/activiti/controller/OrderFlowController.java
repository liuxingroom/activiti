package com.xing.activiti.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xing.activiti.service.OrderService;
import com.xing.activiti.vo.OrderCustom;
import com.xing.activiti.vo.OrderVo;

/**
 *	采购单的管理
 */
@Controller
@RequestMapping("/orderflow")
public class OrderFlowController {

	@Resource
	OrderService orderService;
	
	/**
	 * 跳转到采购单页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/addOrder.action")
	public String addOrder(Model model){
		return "order/addOrder";
	}
	
	/**
	 * 提交采购单
	 */
	@RequestMapping("/addOrderSave.action")
	public String addOrderSave(HttpSession session,OrderVo orderVo,Model model){
		//获取登录人的id
		String userId=(String) session.getAttribute("userId");
		
		//调用service保存采购单
		orderService.saveOrder(userId,orderVo.getOrderCustom());
		
		return "redirect:orderTaskList.action";
	}
	
	/**
	 * 采购单处理列表
	 * @throws Exception 
	 */
	@RequestMapping("/orderTaskList.action")
	public String orderTaskList(HttpSession session,Model model) throws Exception{
		//获取用户信息
		String userId=(String) session.getAttribute("userId");
		List<OrderCustom> list=orderService.findOrderTaskList(userId);
		model.addAttribute("list", list);
		return "order/orderTaskList";
	}
	
	/**
	 * 提交审核信息
	 */
	@RequestMapping("/submitOrderAudit.action")
	public String submitOrderAudit(HttpSession session,
			String taskId,String orderId,String auditType,OrderVo orderVo){
		
		//用户id
		String userId=(String) session.getAttribute("userId");
		orderService.saveOrderAuditStatus(taskId,userId,orderId,auditType,orderVo.getOrderAuditCustom());
		// 返回到采购单处理列表
		return "redirect:orderTaskList.action";
	}
	
	
	/**
	 * 提交采购单
	 * @throws Exception 
	 */
	@RequestMapping("/submitOrder.action")
	public String submitOrder(HttpSession session,String taskId) throws Exception{
		//获取当前登录对象
		String userId=(String) session.getAttribute("userId");
		orderService.saveOrderSubmitStauts(userId,taskId);
		
		// 返回到采购单处理列表
		return "redirect:orderTaskList.action";
	}
	
	
	/**
	 * 跳转到审核页面
	 */
	@RequestMapping("/orderAudit.action")
	public String orderAudit(Model model,String taskId,String orderId,String auditType){
		
		model.addAttribute("taskId", taskId);
		model.addAttribute("orderId", orderId);
		model.addAttribute("auditType", auditType);

		return "order/orderAudit";
	}
	
	/**
	 * 查看组任务列表
	 * @throws Exception 
	 */
	@RequestMapping("/orderGroupTaskList.action")
	public String orderGroupTaskList(HttpSession session,Model model) throws Exception{
		//获取当前登录的用户
		String userId=(String) session.getAttribute("userId");
		List<OrderCustom> list=orderService.findOrderGroupTaskList(userId);
		
		model.addAttribute("list", list);
		return "order/orderGroupTaskList";
	}
	
	
	/**
	 * 拾取组任务
	 */
	@RequestMapping("/claimTask.action")
	public String claimTask(HttpSession session,String taskId){
		//获取登录用户信息
		String userId=(String) session.getAttribute("userId");
		orderService.saveClaimTask(taskId,userId);
		//返回采购单组任务列表
		return "redirect:orderGroupTaskList.action";
	}
	
}
