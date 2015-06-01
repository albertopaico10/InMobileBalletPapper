package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_district database table.
 * 
 */
@Entity
@Table(name="tb_district")
public class District implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String districtName;

	private int provinceId;

	public District() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDistrictName() {
		return this.districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public int getProvinceId() {
		return this.provinceId;
	}

	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}

}