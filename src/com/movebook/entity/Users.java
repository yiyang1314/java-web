package com.movebook.entity;

import java.io.Serializable;

public class Users implements Serializable {

	private Integer id;
	private String name;

	private String password;

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String toString() {
		return "entity[id=" + id + ",name=" + name + ",password=" + password + "]";
	}

}