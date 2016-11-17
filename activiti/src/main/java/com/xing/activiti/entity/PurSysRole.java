package com.xing.activiti.entity;

import java.io.Serializable;

/**
 *  角色表 pur_sys_role
 */
public class PurSysRole implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5456653942218617547L;
	
	/**角色id*/
	private String id;
	/**角色名*/
	private String name;
	/***/
	private String detail;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	
	
}
