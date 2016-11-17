package com.xing.activiti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sun.tools.internal.ws.processor.model.Model;

@Controller
@RequestMapping("/flow")
public class FlowController {
	
	@RequestMapping("/deployProcess")
	public String deployProcess(Model model){
		return "flow/deployProcess";
	}
}
