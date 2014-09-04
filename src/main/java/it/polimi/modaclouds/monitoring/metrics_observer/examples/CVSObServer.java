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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.polimi.modaclouds.monitoring.metrics_observer.MetricsObServer;

public class CVSObServer extends MetricsObServer {
	
	private static final Logger logger = LoggerFactory.getLogger(CVSObServer.class);

	public CVSObServer(int listeningPort) {
		super(listeningPort, "/v1/results", CVSResultHandler.class);
	}

	public static void main(String[] args) {
		int port = (args.length > 0) ? Integer.parseInt(args[0]) : 8000;
		logger.debug("Using port {}", port);
		CVSObServer observer = new CVSObServer(port);
		try {
			observer.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
