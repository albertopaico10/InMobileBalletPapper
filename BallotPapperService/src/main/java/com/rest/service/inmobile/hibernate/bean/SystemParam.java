package com.rest.service.inmobile.hibernate.bean;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the tb_system_param database table.
 * 
 */
@Entity
@Table(name="tb_system_param")
public class SystemParam implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private int id;

	@Column(name="date_created")
	private Timestamp dateCreated;

	private String nameParam;

	private String reasonParam;

	private int status;

	private String valueParam;
	
	private String generalParam;

	public SystemParam() {
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

	public String getNameParam() {
		return this.nameParam;
	}

	public void setNameParam(String nameParam) {
		this.nameParam = nameParam;
	}

	public String getReasonParam() {
		return this.reasonParam;
	}

	public void setReasonParam(String reasonParam) {
		this.reasonParam = reasonParam;
	}

	public int getStatus() {
		return this.status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getValueParam() {
		return this.valueParam;
	}

	public void setValueParam(String valueParam) {
		this.valueParam = valueParam;
	}

	public String getGeneralParam() {
		return generalParam;
	}

	public void setGeneralParam(String generalParam) {
		this.generalParam = generalParam;
	}

}