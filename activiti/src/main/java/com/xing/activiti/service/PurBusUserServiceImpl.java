package com.xing.activiti.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xing.activiti.entity.PurSysUser;

@Service
public class PurBusUserServiceImpl implements PurBusUserService {

	@Resource
	PurBusUserService purBusUserService;
	
	@Override
	public PurSysUser checkUser(String userId, String pwd) {
		PurSysUser user=purBusUserService.checkUser(userId,pwd);
		return user;
	}
	
}
