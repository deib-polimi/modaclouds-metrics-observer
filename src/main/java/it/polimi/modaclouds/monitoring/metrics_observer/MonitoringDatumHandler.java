/**
 * Copyright 2014 deib-polimi
 * Contact: deib-polimi <marco.miglierina@polimi.it>
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package it.polimi.modaclouds.monitoring.metrics_observer;

import java.util.List;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public abstract class MonitoringDatumHandler extends ServerResource {

	private static final Logger logger = LoggerFactory
			.getLogger(MonitoringDatumHandler.class.getName());
	private static Gson gson = new Gson();

	public abstract void getData(List<MonitoringDatum> monitoringData);

	@Post
	public void getData(Representation entity) {
		try {
			getData(JSONMonitoringDataParser.fromJson(entity));
			this.getResponse().setStatus(Status.SUCCESS_OK,
					"Monitoring datum succesfully received");
			this.getResponse().setEntity(
					gson.toJson("Monitoring datum succesfully received"),
					MediaType.APPLICATION_JSON);

		} catch (Exception e) {
			logger.error("Error while receiving monitoring data", e);
			this.getResponse().setStatus(Status.SERVER_ERROR_INTERNAL,
					"Error while receiving monitoring data");
			this.getResponse().setEntity(
					gson.toJson("Error while receiving monitoring data"),
					MediaType.APPLICATION_JSON);
		} finally {
			this.getResponse().commit();
			this.commit();
			this.release();
		}
	}
	
	
	

}
