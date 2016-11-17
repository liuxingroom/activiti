package com.xing.activiti.dao;

import org.apache.ibatis.annotations.Param;

import com.xing.activiti.entity.PurSysUser;

public interface PurBusUserMapper {

	PurSysUser checkUser(@Param("username")String userId, @Param("id")String pwd);

}
