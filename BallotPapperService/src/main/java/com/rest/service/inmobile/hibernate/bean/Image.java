package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_image database table.
 * 
 */
@Entity
@Table(name="tb_Image")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String categoryImage;

	@Column(name="date_created")
	private Timestamp dateCreated;

	@Column(name="date_updated")
	private Timestamp dateUpdated;
	
	private int status;

	@Lob
	private byte[] img;

	@Column(name="user_created")
	private int userCreated;

	@Column(name="user_updated")
	private int userUpdated;

	public Image() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoryImage() {
		return this.categoryImage;
	}

	public void setCategoryImage(String categoryImage) {
		this.categoryImage = categoryImage;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Timestamp getDateUpdated() {
		return this.dateUpdated;
	}

	public void setDateUpdated(Timestamp dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public byte[] getImg() {
		return this.img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

	public int getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(int userCreated) {
		this.userCreated = userCreated;
	}

	public int getUserUpdated() {
		return this.userUpdated;
	}

	public void setUserUpdated(int userUpdated) {
		this.userUpdated = userUpdated;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}