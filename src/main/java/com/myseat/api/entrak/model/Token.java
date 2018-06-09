package com.myseat.api.entrak.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Token implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@JsonProperty("refresh_token")
	private String refreshToken;
	@JsonProperty("access_token")
	private String accessToken;
	private String scope;
	@JsonProperty("expires_in")
	private int expiresIn;
	private String tokenType;
	
	
	public String getRefreshToken() {
		return refreshToken;
	}
	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public int getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getTokenType() {
		return tokenType;
	}
	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}
	@Override
	public String toString() {
		return "Token [refreshToken=" + refreshToken + ", accessToken=" + accessToken + ", scope=" + scope
				+ ", expiresIn=" + expiresIn + ", tokenType=" + tokenType + "]";
	}
}
