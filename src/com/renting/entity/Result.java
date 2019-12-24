package com.renting.entity;

import java.util.Map;

public class Result {

	private String msg;
	private Object data;
	private Integer Status;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Integer getStatus() {
		return Status;
	}
	public void setStatus(Integer status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "Result [msg=" + msg + ", data=" + data + ", Status=" + Status + "]";
	}
	
	
}
