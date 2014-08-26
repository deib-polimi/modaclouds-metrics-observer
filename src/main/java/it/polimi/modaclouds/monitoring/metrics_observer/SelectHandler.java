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

import it.polimi.modaclouds.monitoring.metrics_observer.model.Sparql_json_results;
import it.polimi.modaclouds.monitoring.metrics_observer.model.Variable;

import java.io.StringReader;
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
import com.google.gson.stream.JsonReader;

@Deprecated
public abstract class SelectHandler extends ServerResource {

	private Logger logger = LoggerFactory.getLogger(SelectHandler.class
			.getName());
	
	public abstract void getData(List<String> varNames, List<Map<String, Variable>> bindings);

	@Post
	public void getData(Representation entity) {
		String results = null;
		JsonReader reader = null;
		Gson gson = new Gson();
		try {
			results = entity.getText();
			reader = new JsonReader(new StringReader(results));
			Sparql_json_results s = gson.fromJson(reader,
					Sparql_json_results.class);
			getData(s.getHead().getVars(), s.getResults().getBindings());
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
