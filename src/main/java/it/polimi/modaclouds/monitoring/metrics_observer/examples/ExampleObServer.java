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

import it.polimi.modaclouds.monitoring.metrics_observer.MetricsObServer;
import it.polimi.modaclouds.monitoring.metrics_observer.model.Variable;

import java.util.List;
import java.util.Map;

public class ExampleObServer extends MetricsObServer {

	public ExampleObServer(int listeningPort) {
		super(listeningPort);
	}

	@Override
	public void getData(List<String> varNames,
			List<Map<String, Variable>> bindings) {
		String value;
		for (Map<String, Variable> m : bindings) {
			String metricPath = "";
			int last = varNames.size() - 2;
			for (int i = 0; i < last; i++) {
				Variable var = m.get(varNames.get(i));
				if (var != null) {
					value = var.getValue();
					metricPath += value.substring(value.indexOf('#') + 1)
							+ (i == last - 1 ? "" : ".");
				}
			}
			System.out.println(metricPath);
		}
	}
	
	public static void main(String[] args) {
		ExampleObServer observer = new ExampleObServer(8123);
		try {
			observer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
