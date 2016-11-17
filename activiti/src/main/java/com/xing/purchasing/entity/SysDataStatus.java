package com.xing.purchasing.entity;

import java.io.Serializable;

/**
 *  数据状态表    sys_data_status
 */
public class SysDataStatus implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3813702997838640813L;
	/**数据状态id*/
	private String id;
	/**数据所属类型*/
	private String typeId;
	/**数据状态名称*/
	private String name;
	/**数据状态编码*/
	private String code;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
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
