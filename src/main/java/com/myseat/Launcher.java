package com.myseat;

import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import com.myseat.api.myseat.model.Sensor;
import com.myseat.api.myseat.service.MyseatService;
import com.myseat.business.SmartLightService;

@ComponentScan(basePackages = "com.myseat")
public class Launcher extends TimerTask {

	private static final Logger LOG = Logger.getLogger(Launcher.class);

	public static void main(String[] args) {
		Timer timer = new Timer();
		timer.schedule(new Launcher(), 0, 1000);
	}

	@Override
	public void run() {
		try {
			ApplicationContext context = new AnnotationConfigApplicationContext(Launcher.class);
			MyseatService myseatService = context.getBean(MyseatService.class);
			SmartLightService smartLightService = context.getBean(SmartLightService.class);

			Sensor sensorBrownTable = myseatService.getSensorInfoByLastDate("A0gkjrm6kjcZOSt931lWstlCuoogkwULm1Wwj8rt",
					"6066005655705708", 10);
			sensorBrownTable.setWorkStationId("1077");

			Sensor sensorFoamStairs = myseatService.getSensorInfoByLastDate("A0gkjrm6kjcZOSt931lWstlCuoogkwULm1Wwj8rt",
					"6066005655937708", 10);
			sensorFoamStairs.setWorkStationId("1079");

			Sensor sensorBlackTable = myseatService.getSensorInfoByLastDate("A0gkjrm6kjcZOSt931lWstlCuoogkwULm1Wwj8rt",
					"6066005655706079", 10);
			sensorBlackTable.setWorkStationId("1078");
			
			Sensor noiseSensor =  myseatService.getSensorInfoByLastDate("A0gkjrm6kjcZOSt931lWstlCuoogkwULm1Wwj8rt",
					"6066005668988672", 10);
			noiseSensor.setWorkStationId("1078");
			
			Sensor sensorCombinaison = new Sensor();
			sensorCombinaison.setStatus(smartLightService.verifyNoiseAndOccupancy(sensorBlackTable, noiseSensor));
			sensorCombinaison.setWorkStationId("1078");

			List<Sensor> sensors = Arrays.asList(sensorBrownTable, sensorFoamStairs, sensorBlackTable);

			LOG.info("sensorBrownTable " + sensorBrownTable.toString() + " - sensorFoamStairs "
					+ sensorFoamStairs.toString() + " - sensorBlackTable " + sensorBlackTable.toString());
			
			smartLightService.processToSwitchLightForOneSensor(sensors, "", "", 0);
			

		} catch (Throwable exception) {
			LOG.info("Something happen because of this exception " + exception.getMessage());
			LOG.info("The service will sleep for 30 seconds and come back again .. ");
			try {
				Thread.currentThread().sleep(1000 * 30);
				LOG.info("Service restarted ...");
			} catch (InterruptedException interruptedException) {
				LOG.info("Thread Sleep Exception " + interruptedException.getMessage());
			}
		}
		/*
		 * String.valueOf(Long.parseLong("158D00005515F1", 16))
		 * smartLightService.processToTakeDecision(
		 * myseatService.getSensorInfoByLastDate(
		 * "lyBRqiFyr53R2h7PbOhuC3XOmmp5S774uzMMHibZ", "6066005655705899", 10),
		 * myseatService.getSensorInfoByLastDate(
		 * "lyBRqiFyr53R2h7PbOhuC3XOmmp5S774uzMMHibZ", "6066005668999610", 3),
		 * myseatService.getSensorInfoByLastDate(
		 * "lyBRqiFyr53R2h7PbOhuC3XOmmp5S774uzMMHibZ", "6066005655940377", 2));
		 */

	}
}
