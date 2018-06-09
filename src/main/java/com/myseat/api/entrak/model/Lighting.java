package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Lighting implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String status;
	
	@JsonProperty("scheduler_status")
	private String schedulerStatus;
	
	@JsonProperty("status_change_at")
	private String statusChangeAt;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSchedulerStatus() {
		return schedulerStatus;
	}

	public void setSchedulerStatus(String schedulerStatus) {
		this.schedulerStatus = schedulerStatus;
	}

	public String getStatusChangeAt() {
		return statusChangeAt;
	}

	public void setStatusChangeAt(String statusChangeAt) {
		this.statusChangeAt = statusChangeAt;
	}

	@Override
	public String toString() {
		return "Lighting [status=" + status + ", schedulerStatus=" + schedulerStatus + ", statusChangeAt="
				+ statusChangeAt + "]";
	}
}
