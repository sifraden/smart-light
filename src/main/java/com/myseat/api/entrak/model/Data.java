package com.myseat.api.entrak.model;

import java.io.Serializable;

public class Data implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Lighting lighting;
	private System system;
	private Network network;
	
	
	public Lighting getLighting() {
		return lighting;
	}
	public void setLighting(Lighting lighting) {
		this.lighting = lighting;
	}
	public System getSystem() {
		return system;
	}
	public void setSystem(System system) {
		this.system = system;
	}
	public Network getNetwork() {
		return network;
	}
	public void setNetwork(Network network) {
		this.network = network;
	}
	@Override
	public String toString() {
		return "Data [lighting=" + lighting + ", system=" + system + ", network=" + network + "]";
	}
}
