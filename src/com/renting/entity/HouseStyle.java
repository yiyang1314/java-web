package com.renting.entity;

import java.io.Serializable;

public class HouseStyle implements Serializable{
	private Integer	styleid;
	private String	styleName;
	public Integer getStyleid() {
		return styleid;
	}
	public void setStyleid(Integer styleid) {
		this.styleid = styleid;
	}
	public String getStyleName() {
		return styleName;
	}
	public void setStyleName(String styleName) {
		this.styleName = styleName;
	}
	@Override
	public String toString() {
		return "HouseStyle [styleid=" + styleid + ", styleName=" + styleName + "]";
	}



}