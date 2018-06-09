package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Exemption implements Serializable {

	private static final long serialVersionUID = 1L;

	private String status;
	
	@JsonProperty("start_from")
	private String startFrom;
	
	@JsonProperty("end_at")
	private String endAt;

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStartFrom() {
		return startFrom;
	}

	public void setStartFrom(String startFrom) {
		this.startFrom = startFrom;
	}

	public String getEndAt() {
		return endAt;
	}

	public void setEndAt(String endAt) {
		this.endAt = endAt;
	}

	@Override
	public String toString() {
		return "Exemption [status=" + status + ", startFrom=" + startFrom + ", endAt=" + endAt + "]";
	}
}
