package com.xing.activiti.entity;

import java.io.Serializable;

/**
 *	用户信息表  Pur_sys_user
 */
public class PurSysUser implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7244457696477394776L;
	
	/**用户id*/
	private String id;
	/**用户名*/
	private String userName;
	/**密码*/
	private String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
