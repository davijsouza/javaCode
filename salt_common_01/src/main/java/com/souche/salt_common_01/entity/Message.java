package com.souche.salt_common_01.entity;

import java.io.Serializable;

/**
 * @author shifengyuan
 * @date 创建时间：2018年12月3日下午4:50:29
 */
public class Message implements Serializable{

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String msg;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", msg=" + msg + "]";
	}
}
