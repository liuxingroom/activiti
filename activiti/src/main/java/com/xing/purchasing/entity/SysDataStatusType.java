package com.xing.purchasing.entity;

import java.io.Serializable;

/**
 * 数据字典表     sys_data_status_type 
 */
public class SysDataStatusType implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8105863311249377427L;
	/**数据字典id*/
	private String id;
	/**数据名称*/
	private String name;
	/**数据编码*/
	private String code;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}
