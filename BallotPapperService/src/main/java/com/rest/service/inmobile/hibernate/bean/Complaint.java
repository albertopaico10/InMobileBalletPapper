package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;


/**
 * The persistent class for the tb_complient database table.
 * 
 */
@Entity
@Table(name="tb_complaint_registration")
public class Complaint implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	private String comments;

	private String completeAddress;

	@Column(name="date_created")
	private Timestamp dateCreated;

	private int idUser;

	private String latitude;

	private String longitude;

	private int status;
	
	private int typeComplaint;
	
	private String distrinctName;
	
	@Column(name="number_plate")
	private String numberPlate;
	
	@Column(name="user_created")
	private int userCreated;

	private String address;
	
	private String country;
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Complaint() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCompleteAddress() {
		return this.completeAddress;
	}

	public void setCompleteAddress(String completeAddress) {
		this.completeAddress = completeAddress;
	}

	public Timestamp getDateCreated() {
		return this.dateCreated;
	}

	public void setDateCreated(Timestamp dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getLatitude() {
		return this.latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public int getStatus() {
		return status;
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

	public String getNumberPlate() {
		return numberPlate;
	}

	public void setNumberPlate(String numberPlate) {
		this.numberPlate = numberPlate;
	}

	public String getDistrinctName() {
		return distrinctName;
	}

	public void setDistrinctName(String distrinctName) {
		this.distrinctName = distrinctName;
	}

	public int getTypeComplaint() {
		return typeComplaint;
	}

	public void setTypeComplaint(int typeComplaint) {
		this.typeComplaint = typeComplaint;
	}
}