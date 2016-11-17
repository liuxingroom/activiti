package com.xing.activiti.service;

import com.xing.activiti.entity.PurSysUser;

public interface PurBusUserService {


	PurSysUser checkUser(String userId, String pwd);

}
