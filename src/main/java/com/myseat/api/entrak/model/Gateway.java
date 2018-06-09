package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gateway implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id;
	
	@JsonProperty("last_connected")
	private String lastConnected;
	
	@JsonProperty("last_connection_attempted")
	private String lastConnectionAttempted;
	
	private String serial;
	
	private String status;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastConnected() {
		return lastConnected;
	}

	public void setLastConnected(String lastConnected) {
		this.lastConnected = lastConnected;
	}

	public String getLastConnectionAttempted() {
		return lastConnectionAttempted;
	}

	public void setLastConnectionAttempted(String lastConnectionAttempted) {
		this.lastConnectionAttempted = lastConnectionAttempted;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Gateway [id=" + id + ", lastConnected=" + lastConnected + ", lastConnectionAttempted="
				+ lastConnectionAttempted + ", serial=" + serial + ", status=" + status + "]";
	}
}
