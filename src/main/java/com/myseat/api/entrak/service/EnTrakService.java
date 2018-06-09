package com.myseat.api.entrak.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.myseat.api.entrak.model.EntrakResponse;
import com.myseat.api.entrak.model.Token;
import com.myseat.api.entrak.model.WorkstationResponse;

@Service
public class EnTrakService {

	private static final Logger LOG = Logger.getLogger(EnTrakService.class);
	
	@Value("${entrak.api.get.token}")
	private String uriGetToken;
	
	@Value("${entrak.api.put.switch.light}")
	private String uriSwitchLight;
	
	@Value("${entrak.api.get.workstations}")
	private String uriGetWorkstations;
	
	@Value("${entrak.api.get.workstation.by.id}")
	private String uriGetWorkstationById;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public Token getToken(String username, String password, String clientId) {
		LOG.debug("EnTrak Service - Get Token for username: " + username);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
		params.add("username", username);
		params.add("password", password);
		params.add("client_id", clientId);
		params.add("grant_type", "password");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(params, httpHeaders);
		
		return restTemplate.postForObject(uriGetToken, request, Token.class);
	}
	
	public EntrakResponse getWorkStations(String token, String companyId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + token);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriGetWorkstations);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("companyId", companyId);
		HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(params, httpHeaders);
		
		return restTemplate.exchange(builder.buildAndExpand(params).toUri().toString(), HttpMethod.GET, request, EntrakResponse.class, params).getBody();
	}
	
	public WorkstationResponse getWorkStationById(String token, String workstationId) {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + token);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriGetWorkstationById);
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("workstationId", workstationId);
		HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(params, httpHeaders);
		
		return restTemplate.exchange(builder.buildAndExpand(params).toUri().toString(), HttpMethod.GET, request, WorkstationResponse.class, params).getBody();
	}
	
	public void switchLight(String token, String workStationId, String action, int duration) {
		LOG.debug("EnTrak Service - Switch Light " + action + " for workstation: " + workStationId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.set("Authorization", "Bearer " + token);
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uriSwitchLight);
		
		Map<String, String> params= new HashMap<String, String>();
		//params.add("duration", duration);
		params.put("workstationId", workStationId);
		params.put("action", action);	
		HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(params, httpHeaders);

		restTemplate.exchange(builder.buildAndExpand(params).toUri().toString(), HttpMethod.PUT, request, Object.class, params);
	}
}
