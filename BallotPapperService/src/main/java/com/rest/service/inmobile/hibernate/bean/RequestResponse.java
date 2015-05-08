package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_request_response database table.
 * 
 */
@Entity
@Table(name="tb_request_response")
public class RequestResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="date_created")
	private Timestamp dateCreated;

	private int idUser;

	@Lob
	private String request;

	@Lob
	private String response;

	private int status;

	private String typeOperation;

	@Column(name="user_created")
	private int userCreated;

	public RequestResponse() {
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

	public int getIdUser() {
		return this.idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getRequest() {
		return this.request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public String getResponse() {
		return this.response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getTypeOperation() {
		return this.typeOperation;
	}

	public void setTypeOperation(String typeOperation) {
		this.typeOperation = typeOperation;
	}

	public int getUserCreated() {
		return this.userCreated;
	}

	public void setUserCreated(int userCreated) {
		this.userCreated = userCreated;
	}

}