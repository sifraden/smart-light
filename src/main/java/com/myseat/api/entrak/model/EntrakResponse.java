package com.myseat.api.entrak.model;

import java.io.Serializable;
import java.util.List;

public class EntrakResponse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Data> data;

	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "EntrakResponse [data=" + data + "]";
	}
	
}
