package com.myseat.api.entrak.model;

import java.io.Serializable;
import java.util.List;

public class Network implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Device> devices;
	private List<Gateway> gateways;
	
	
	public List<Device> getDevices() {
		return devices;
	}
	public void setDevices(List<Device> devices) {
		this.devices = devices;
	}
	public List<Gateway> getGateways() {
		return gateways;
	}
	public void setGateways(List<Gateway> gateways) {
		this.gateways = gateways;
	}
	@Override
	public String toString() {
		return "Network [devices=" + devices + ", gateways=" + gateways + "]";
	}
}
