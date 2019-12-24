package com.movebook.entity;

import java.io.Serializable;
import java.util.Date;

public class MoveBooking implements Serializable {

	private Integer id;

	private String area;

	private String cartype;

	private Date movedate;

	private String contact;
	private String phone;

	private String status;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public void setCartype(String cartype) {
		this.cartype = cartype;
	}

	public void setMovedate(Date movedate) {
		this.movedate = movedate;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getArea() {
		return area;
	}

	public String getCartype() {
		return cartype;
	}

	public Date getMovedate() {
		return movedate;
	}

	public String getContact() {
		return contact;
	}

	public String getPhone() {
		return phone;
	}

	public String getStatus() {
		return status;
	}

	public String toString() {
		return "entity[id=" + id + ",area=" + area + ",cartype=" + cartype + ",movedate=" + movedate + ",contact="
				+ contact + ",phone=" + phone + ",status=" + status + "]";
	}

}