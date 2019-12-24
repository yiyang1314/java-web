package com.renting.entity;

import java.io.Serializable;
import java.util.Date;

public class House implements Serializable{
	private Integer	houseid;
	private String	picPath;
	private String	title;
	private Integer	houseArea;
	private Double	housePrice;
	private Date	houseDate;
	private String	phone;
	private String	summary;
	private Date	publicDate;
	private HouseStyle	houseStyle;
	private TRegion	region;	
	private String	street="";
	
	private Users user;

	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public Integer getHouseid() {
		return houseid;
	}
	public void setHouseid(Integer houseid) {
		this.houseid = houseid;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getHouseArea() {
		return houseArea;
	}
	public void setHouseArea(Integer houseArea) {
		this.houseArea = houseArea;
	}
	public Double getHousePrice() {
		return housePrice;
	}
	public void setHousePrice(Double housePrice) {
		this.housePrice = housePrice;
	}
	public Date getHouseDate() {
		return houseDate;
	}
	public void setHouseDate(Date houseDate) {
		this.houseDate = houseDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public Date getPublicDate() {
		return publicDate;
	}
	public void setPublicDate(Date publicDate) {
		this.publicDate = publicDate;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	

	public HouseStyle getHouseStyle() {
		return houseStyle;
	}
	public void setHouseStyle(HouseStyle houseStyle) {
		this.houseStyle = houseStyle;
	}
	public TRegion getRegion() {
		return region;
	}
	public void setRegion(TRegion region) {
		this.region = region;
	}
	@Override
	public String toString() {
		return "House [houseid=" + houseid + ", title=" + title + ", housePrice=" + housePrice + ", phone=" + phone
				+ ", region=" + region + ", street=" + street + "]";
	}




}