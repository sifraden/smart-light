package com.myseat.api.myseat.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.myseat.api.myseat.model.Content;
import com.myseat.api.myseat.model.Sensor;

@Service
public class MyseatService {
	public static final Logger LOG = Logger.getLogger(MyseatService.class);

	@Value("${myseat.api.get.sensors.in.group}")
	private String uriGetSensorsInGroup;
	
	@Value("${myseat.api.get.sensor.type}")
	private String uriGetSensorTypeAndLastMessages;
	
	@Autowired
	private RestTemplate restTemplate;

	public Content getSensorsInGroup(String apiKey, String groupId) {
		LOG.debug("MyseatService - Get Sensors in group " + groupId);
		Map<String, String> params = new HashMap<String, String>();
		params.put("apiKey", apiKey);
		params.put("groupId", groupId);
		return restTemplate.getForObject(uriGetSensorsInGroup, Content.class, params);
	}
	
	public List<Sensor> getSensorInfo(String apiKey, String sensorId) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("apiKey", apiKey);
		params.put("sensorId", sensorId);
		ResponseEntity<List<Sensor>> responseEntity = restTemplate.exchange(uriGetSensorTypeAndLastMessages,
	            HttpMethod.GET, null, new ParameterizedTypeReference<List<Sensor>>() {}, params);
		return responseEntity.getBody();
	}
	
	public Sensor getSensorInfoByLastDate(String apiKey, String sensorId, int differenceTime) {
		List<Sensor> sensors = getSensorInfo(apiKey, sensorId);
/*		if (!sensors.isEmpty()) {
			sensors.sort((d2, d1) -> d1.getDate().compareTo(d2.getDate()));
			return sensors.stream().findFirst().get();
		}*/
		return sensors.get(0);
	}

}
