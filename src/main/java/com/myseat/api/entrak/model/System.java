package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class System implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private int id;
	
	@JsonProperty("office_id")
	private String officeId;
	
	@JsonProperty("scheduler_id")
	private int schedulerId;

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}

	public int getSchedulerId() {
		return schedulerId;
	}

	public void setSchedulerId(int schedulerId) {
		this.schedulerId = schedulerId;
	}

	@Override
	public String toString() {
		return "System [name=" + name + ", id=" + id + ", officeId=" + officeId + ", schedulerId=" + schedulerId + "]";
	}
}
