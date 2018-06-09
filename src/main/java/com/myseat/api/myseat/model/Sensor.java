package com.myseat.api.myseat.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Sensor implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private double value;
	private String date;
	private int status;
	private String type;
	private boolean underAlert;
	
	@JsonIgnore
	private String workStationId;
	
	public Sensor() {
	}
	
	public Sensor(double value, String date, int status, String type, boolean underAlert, String workStationId) {
		this.value = value;
		this.date = date;
		this.status = status;
		this.type = type;
		this.underAlert = underAlert;
		this.workStationId = workStationId;
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean isUnderAlert() {
		return underAlert;
	}
	public void setUnderAlert(boolean underAlert) {
		this.underAlert = underAlert;
	}
	
	public String getWorkStationId() {
		return workStationId;
	}

	public void setWorkStationId(String workStationId) {
		this.workStationId = workStationId;
	}

	@Override
	public String toString() {
		return "Sensor [value=" + value + ", date=" + date + ", status=" + status + ", type=" + type + ", underAlert="
				+ underAlert + ", workStationId=" + workStationId + "]";
	}

}
