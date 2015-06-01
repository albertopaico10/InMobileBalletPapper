package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_complaint_image database table.
 * 
 */
@Entity
@Table(name="tb_Complaint_Image")
public class ComplaintImage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="date_created")
	private Timestamp dateCreated;

	private int idComplaint;

	private int idImage;

	private int idUser;

	@Column(name="user_created")
	private int userCreated;

	public ComplaintImage() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getIdComplaint() {
		return this.idComplaint;
	}

	public void setIdComplaint(int idComplaint) {
		this.idComplaint = idComplaint;
	}

	public int getIdImage() {
		return this.idImage;
	}

	public void setIdImage(int idImage) {
		this.idImage = idImage;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public int getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(int userCreated) {
		this.userCreated = userCreated;
	}

}