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

import it.polimi.modaclouds.monitoring.dcfactory.DCVocabulary;
import it.polimi.modaclouds.monitoring.dcfactory.wrappers.DDAOntology;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.restlet.representation.Representation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class JSONMonitoringDataParser {
	
	private static final Logger logger = LoggerFactory
			.getLogger(JSONMonitoringDataParser.class);
	private static Gson gson = new Gson();
	
	private static List<Map<String, String>> nullable(List<Map<String, String>> list) {
		if (list != null)
			return list;
		else {
			List<Map<String, String>> emptyValueList = new ArrayList<Map<String, String>>();
			Map<String, String> emptyValueMap = new HashMap<String, String>();
			emptyValueMap.put("value", "");
			emptyValueList.add(emptyValueMap);
			return emptyValueList;
		}
	}
	
	public static List<MonitoringDatum> jsonToMonitoringDatum(String json) throws IOException {
		JsonReader reader = null;
		reader = new JsonReader(new StringReader(json));
		Type type = new TypeToken<Map<String, Map<String, List<Map<String, String>>>>>() {
		}.getType();
		Map<String, Map<String, List<Map<String, String>>>> jsonMonitoringData = gson
				.fromJson(reader, type);
		List<MonitoringDatum> monitoringData = new ArrayList<MonitoringDatum>();
		if (jsonMonitoringData.isEmpty()) {
			logger.warn("Empty monitoring data json object received");
			return monitoringData;
		}
		for (Map<String, List<Map<String, String>>> jsonMonitoringDatum: jsonMonitoringData
				.values()) {
			MonitoringDatum datum = new MonitoringDatum();
			datum.setMetric(nullable(
				jsonMonitoringDatum.get(DDAOntology.metric.toString())).get(0)
				.get("value"));
			datum.setTimestamp(nullable(
				jsonMonitoringDatum.get(DDAOntology.timestamp.toString())).get(0).get(
				"value"));
			datum
				.setValue(nullable(jsonMonitoringDatum.get(DDAOntology.value.toString()))
						.get(0).get("value"));
			datum.setResourceId(nullable(
				jsonMonitoringDatum.get(DDAOntology.resourceId.toString())).get(0).get(
				"value"));
			monitoringData.add(datum);
		}
		return monitoringData;
	}

	public static List<MonitoringDatum> fromJson(Representation json) throws IOException {
		String jsonText = json.getText();
		return jsonToMonitoringDatum(jsonText);
	}

}
