package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_typecomplaint database table.
 * 
 */
@Entity
@Table(name="tb_type_complaint")
public class TypeComplaint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String categoryComplaint;

	@Column(name="date_created")
	private Timestamp dateCreated;

	private int status;

	@Column(name="user_created")
	private int userCreated;

	public TypeComplaint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryComplaint() {
		return this.categoryComplaint;
	}

	public void setCategoryComplaint(String categoryComplaint) {
		this.categoryComplaint = categoryComplaint;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(int userCreated) {
		this.userCreated = userCreated;
	}

}