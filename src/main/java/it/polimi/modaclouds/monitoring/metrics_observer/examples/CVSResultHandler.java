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
package it.polimi.modaclouds.monitoring.metrics_observer.examples;

import it.polimi.modaclouds.monitoring.metrics_observer.ConstructHandler;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public class CVSResultHandler extends ConstructHandler {

	@Override
	public void getData(
			Map<String, Map<String, List<Map<String, String>>>> monitoringData) {
		for (String datum : monitoringData.keySet()) {
			String metric = nullable(
					monitoringData
							.get(datum)
							.get("http://www.modaclouds.eu/rdfs/1.0/monitoringdata#metric"))
					.get(0).get("value");
			String timestamp = nullable(
					monitoringData
							.get(datum)
							.get("http://www.modaclouds.eu/rdfs/1.0/monitoringdata#timestamp"))
					.get(0).get("value");
			String value = nullable(
					monitoringData
							.get(datum)
							.get("http://www.modaclouds.eu/rdfs/1.0/monitoringdata#value"))
					.get(0).get("value");
			String resourceId = nullable(
					monitoringData
							.get(datum)
							.get("http://www.modaclouds.eu/rdfs/1.0/monitoringdata#resourceId"))
					.get(0).get("value");
			System.out.println(new Date().getTime()+"," + resourceId + "," + metric + "," + value + ","
					+ timestamp);
		}
	}

	public static void main(String[] args) {
		System.out.println(new Date().getTime());
	}
	
	private List<Map<String, String>> nullable(List<Map<String, String>> list) {
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

}
