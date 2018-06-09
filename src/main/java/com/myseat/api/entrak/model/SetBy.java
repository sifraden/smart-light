package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetBy implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	
	@JsonProperty("first_name")
	private String firstName;
	
	@JsonProperty("last_name")
	private String lastName;
	
	@JsonProperty("profile_id")
	private int profileId;

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getProfileId() {
		return profileId;
	}

	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}

	@Override
	public String toString() {
		return "SetBy [email=" + email + ", firstName=" + firstName + ", lastName=" + lastName + ", profileId="
				+ profileId + "]";
	}
}
