package com.myseat.business;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.myseat.api.entrak.model.EntrakResponse;
import com.myseat.api.entrak.model.Token;
import com.myseat.api.entrak.model.WorkstationResponse;
import com.myseat.api.entrak.service.EnTrakService;
import com.myseat.api.myseat.model.Sensor;

@Service
public class SmartLightService {

	private static final Logger LOG =  Logger.getLogger(SmartLightService.class);
	
	@Value("${myseat.sensor.dba.value}")
	private double sensorDbaValue;
	
	@Value("${myseat.number.for.decision}")
	private int numberOfDecision;
	
	@Value("${entrak.api.username}")
	private String username;

	@Value("${entrak.api.password}")
	private String password;

	@Value("${entrak.api.client.id}")
	private String clientId;
	
	@Value("${myseat.sensor.dba.value}")
	private double noiseThreshold;
	
	@Autowired
	private EnTrakService enTrakService;

	private static int iterationDecisionToSwitchOff = 0;
	private static int iterationDecisionToSwitchOn = 0;
	
	
	/***********************
	 * 
	 * Implementation for Demo only
	 * 
	 **********************
	 */
	public void processToSwitchLightForOneSensor(List<Sensor> sensors, String workstationId, String action, int decision) {
		String token = getToken().getAccessToken();

		for (Sensor sensor : sensors) {
			LOG.info("sensor  " + sensor.getStatus());

			if (sensor.getStatus() == 0) {
				if (!verifyIfLightIsOff(token, sensor.getWorkStationId()))
					enTrakService.switchLight(token, sensor.getWorkStationId(), "off", 0);

			} else if (sensor.getStatus() == 1) {
				if (!verifyIfLightIsOn(token, sensor.getWorkStationId()))
					enTrakService.switchLight(token, sensor.getWorkStationId(), "on", 0);
			}
		}
	}
	
	public boolean verifyIfLightIsOff(String token, String workstationId) {
		boolean isLightOff = false;
		WorkstationResponse entrakResponse = enTrakService.getWorkStationById(token, workstationId);
		if (entrakResponse.getData() != null) {
			if (entrakResponse.getData().getLighting().getStatus().equals("OFF"))
				isLightOff = true;
		}
		return isLightOff;
	}
	
	public boolean verifyIfLightIsOn(String token, String workstationId) {
		boolean isLightOn = false;
		WorkstationResponse entrakResponse = enTrakService.getWorkStationById(token, workstationId);
		if (entrakResponse.getData() != null) {
			if (entrakResponse.getData().getLighting().getStatus().equals("ON"))
				isLightOn = true;
		}
		return isLightOn;
	}
	
	public int getDecisionFromSensorToSwitchOff(Sensor sensor) {
		if (sensor.getStatus() == 0)
			setIterationDecisionToSwitchOff(getIterationDecisionToSwitchOff()+1);
		
		LOG.info("Interation for Decision To Switch OFF " + getIterationDecisionToSwitchOff());
		return getIterationDecisionToSwitchOff();
	}
	
	
	public int getDecisionFromSensorToSwitchOn(Sensor sensor) {
		if (sensor.getStatus() == 1)
			setIterationDecisionToSwitchOff(getIterationDecisionToSwitchOn()+1);
		
		LOG.info("Interation for Decision To Switch ON " + getIterationDecisionToSwitchOn());
		return getIterationDecisionToSwitchOn();
	}
	
	public Token getToken() {
		return enTrakService.getToken(username, password, clientId);
	}
	
	public EntrakResponse getWorkstations(String companyId) {
		return enTrakService.getWorkStations(getToken().getAccessToken(), companyId);
	}
	
	public int verifyNoiseAndOccupancy(Sensor blackTable, Sensor sensorNoise) {
		int index = 0;
		if (blackTable.getStatus() == 0 && sensorNoise.getValue() < noiseThreshold)
			index = 0;
		if (blackTable.getStatus() == 1 && sensorNoise.getValue() < noiseThreshold)
			index = 1;
		if (blackTable.getStatus() == 0 && sensorNoise.getValue() > noiseThreshold)
			index = 1;
		if (blackTable.getStatus() == 1 && sensorNoise.getValue() > noiseThreshold)
			index = 1;
		
		return index;
	}

	/**********************
	 * 
	 * Implementation for 3 sensor to switch of (Table, Noise, Door) 
	 * 
	 * *********************
	 */
	public void processToTakeDecision(Sensor sensorTable, Sensor sensorSound, Sensor doorSensor) {
		int decision = getDecisionForAction(sensorTable, sensorSound, doorSensor);
		if (decision == numberOfDecision) {
			enTrakService.switchLight(getToken().getAccessToken(), "1079", "off", 0);
			iterationDecisionToSwitchOff = 0;		
		}
	}
	
	public int getDecisionForAction(Sensor sensorTable, Sensor sensorSound, Sensor doorSensor) {
		int index = createIndexKarnaugh(sensorTable.getStatus(), sensorSound.getValue(), doorSensor.getStatus());
		if (index == 0)
			setIterationDecisionToSwitchOff(getIterationDecisionToSwitchOff()+1);

		return getIterationDecisionToSwitchOff();
	}

	public int createIndexKarnaugh(int tableStatus, double soundStatus, int doorStatus) {
		int index = -1;
		if (tableStatus == 0 && soundStatus <= sensorDbaValue && doorStatus == 0)
			index = 0;
		if (tableStatus == 0 && soundStatus <= sensorDbaValue && doorStatus == 1)
			index = 0;
		if (tableStatus == 0 && soundStatus >= sensorDbaValue && doorStatus == 0)
			index = 0;
		if (tableStatus == 0 && soundStatus >= sensorDbaValue && doorStatus == 1)
			index = 1;
		if (tableStatus == 1 && soundStatus <= sensorDbaValue && doorStatus == 0)
			index = 1;
		if (tableStatus == 1 && soundStatus <= sensorDbaValue && doorStatus == 1)
			index = 1;
		if (tableStatus == 1 && soundStatus >= sensorDbaValue && doorStatus == 0)
			index = 1;
		if (tableStatus == 1 && soundStatus >= sensorDbaValue && doorStatus == 1)
			index = 1;

		return index;
	}
	

	public int getIterationDecisionToSwitchOff() {
		return iterationDecisionToSwitchOff;
	}

	public void setIterationDecisionToSwitchOff(int iterationDecisionToSwitchOff) {
		SmartLightService.iterationDecisionToSwitchOff = iterationDecisionToSwitchOff;
	}

	public int getIterationDecisionToSwitchOn() {
		return iterationDecisionToSwitchOn;
	}

	public void setIterationDecisionToSwitchOn(int iterationDecisionToSwitchOn) {
		SmartLightService.iterationDecisionToSwitchOn = iterationDecisionToSwitchOn;
	}

}
