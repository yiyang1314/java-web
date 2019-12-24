package com.renting.entity;

import java.io.Serializable;

public class TRegion implements Serializable {
	private String code;
	private String countryCode;
	private String regionNameE;
	private String regionNameC;
	private String level;
	private String upperRegion;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getRegionNameE() {
		return regionNameE;
	}

	public void setRegionNameE(String regionNameE) {
		this.regionNameE = regionNameE;
	}

	public String getRegionNameC() {
		return regionNameC;
	}

	public void setRegionNameC(String regionNameC) {
		this.regionNameC = regionNameC;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getUpperRegion() {
		return upperRegion;
	}

	public void setUpperRegion(String upperRegion) {
		this.upperRegion = upperRegion;
	}

	@Override
	public String toString() {
		return "TRegion [code=" + code + ", countryCode=" + countryCode + ", regionNameE=" + regionNameE
				+ ", regionNameC=" + regionNameC + ", level=" + level + ", upperRegion=" + upperRegion + "]";
	}

}