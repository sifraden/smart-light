package com.myseat.business;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.myseat.api.myseat.model.Sensor;

@RunWith(MockitoJUnitRunner.class)
public class SmartLightServiceTest {
	
	@InjectMocks
	private SmartLightService smartLightService;
	
	@Before
	public void setUp() {
		ReflectionTestUtils.setField(smartLightService, "sensorDbaValue", 45.5);
		ReflectionTestUtils.setField(smartLightService, "iterationDecisionToSwitchOff", 0);
	}
	
	@Test
	public void testCreateIndexKarnaugh_AllShutDown() {
		int index = smartLightService.createIndexKarnaugh(0, 30.5, 0);
		assertEquals(0, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_DoorClosed() {
		int index = smartLightService.createIndexKarnaugh(0, 30.5, 1);
		assertEquals(0, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_SoundActif() {
		int index = smartLightService.createIndexKarnaugh(0, 60, 0);
		assertEquals(0, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_SoundActifAndDoorClosed() {
		int index = smartLightService.createIndexKarnaugh(0, 60, 1);
		assertEquals(1, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_TableMovedAndDoorOpen() {
		int index = smartLightService.createIndexKarnaugh(1, 30, 0);
		assertEquals(1, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_TableMovedAndDoorClosed() {
		int index = smartLightService.createIndexKarnaugh(1, 30, 1);
		assertEquals(1, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_TableMovedAndSoundActif() {
		int index = smartLightService.createIndexKarnaugh(1, 50, 0);
		assertEquals(1, index);
	}
	
	@Test
	public void testCreateIndexKarnaugh_AllActif() {
		int index = smartLightService.createIndexKarnaugh(1, 50, 1);
		assertEquals(1, index);
	}

	@Test
	public void testHexToDecimal64() {
		System.out.println(Long.parseLong("158D00005515F1", 16));
/*	
	@Test
	public void testGetDecisionForAction() {
		int decision = smartLightService.getDecisionForAction(new Sensor(0.0, "2018-05-27 15:20:01", 0, "table", false),
				new Sensor(50.0, "2018-05-27 15:25:01", 1, "sound", false),
				new Sensor(0.0, "2018-05-27 15:26:01", 1, "door", false));

	}
	
	public List<Sensor> createSensorsList() {
		List<Sensor> sensors = Arrays.asList(new Sensor(0.0, "2018-05-27 15:20:01", 0, "table", false),
				new Sensor(0.0, "2018-05-27 15:21:01", 1, "table", false),
				new Sensor(0.0, "2018-05-27 15:24:01", 1, "table", false),
				new Sensor(50.0, "2018-05-27 15:25:01", 1, "sound", false),
				new Sensor(40.0, "2018-05-27 15:25:05", 1, "sound", false),
				new Sensor(0.0, "2018-05-27 15:26:01", 1, "door", false),
				new Sensor(0.0, "2018-05-27 15:20:01", 1, "door", false),
				new Sensor(0.0, "2018-05-27 15:29:00", 0, "door", false));
		
		return sensors;*/







	}
}
