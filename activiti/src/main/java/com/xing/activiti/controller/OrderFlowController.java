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
	public String addOrderSave(HttpSession session,OrderVo orderVo){
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
	
}
