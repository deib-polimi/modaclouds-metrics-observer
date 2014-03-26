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
package it.polimi.modaclouds.monitoring.metrics_observer.model;

import java.util.ArrayList;
import java.util.Map;

public class Result {
	
	private ArrayList<Map<String, Variable>> bindings;

	public ArrayList<Map<String, Variable>> getBindings() {
		return bindings;
	}

	public void setBindings(ArrayList<Map<String, Variable>> bindings) {
		this.bindings = bindings;
	}
	
}
