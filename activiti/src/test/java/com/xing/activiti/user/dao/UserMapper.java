package com.xing.activiti.user.dao;

import org.apache.ibatis.annotations.Param;
import com.xing.activiti.user.entity.User;

public interface UserMapper {

	User findUserById(@Param("id")String id);

}
