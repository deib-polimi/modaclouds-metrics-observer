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

import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public abstract class ConstructHandler extends ServerResource {

	private Logger logger = LoggerFactory.getLogger(ConstructHandler.class
			.getName());
	
//	public abstract void getData(List<String> varNames, List<Map<String, Variable>> bindings);
	public abstract void getData(Map<String,Map<String,List<Map<String,String>>>> monitoringDatum);

	@Post
	public void getData(Representation entity) {
		String results = null;
		JsonReader reader = null;
		Gson gson = new Gson();
		try {
			results = entity.getText();
			reader = new JsonReader(new StringReader(results));
			Type type = new TypeToken<Map<String,Map<String,List<Map<String,String>>>>>(){}.getType();
			Map<String,Map<String,List<Map<String,String>>>> monitoringDatum = gson.fromJson(reader, type);
			getData(monitoringDatum);
			this.getResponse().setStatus(Status.SUCCESS_OK,
					"Result succesfully received");
			this.getResponse().setEntity(
					gson.toJson("Result succesfully received"),
					MediaType.APPLICATION_JSON);

		} catch (Exception e) {
			logger.error("Error while receiving results: " + results, e);
			this.getResponse().setStatus(Status.SERVER_ERROR_INTERNAL,
					"Error while receiving data");
			this.getResponse().setEntity(
					gson.toJson("Error while receiving data"),
					MediaType.APPLICATION_JSON);
		} finally {
			this.getResponse().commit();
			this.commit();
			this.release();
		}
	}

	
}
