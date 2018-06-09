package com.myseat.api.entrak.model;

import java.io.Serializable;

public class WorkstationResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EntrakResponse [data=" + data + "]";
	}
	
}
