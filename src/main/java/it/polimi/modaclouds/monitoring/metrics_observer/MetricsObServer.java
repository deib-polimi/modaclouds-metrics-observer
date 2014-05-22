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

import org.restlet.Application;
import org.restlet.Component;
import org.restlet.Restlet;
import org.restlet.data.Protocol;
import org.restlet.routing.Router;
import org.restlet.routing.Template;

public abstract class MetricsObServer extends Component {

	private Class<? extends ResultsHandler> resultHandler;

	public MetricsObServer(int listeningPort, Class<? extends ResultsHandler> resultHandler) {
		super();
		getServers().add(Protocol.HTTP, listeningPort);
		getClients().add(Protocol.FILE);
		this.resultHandler = resultHandler;
		getDefaultHost().attach("", new ObServerApp());
	}
	
	public class ObServerApp extends Application {
		@Override
		public Restlet createInboundRoot() {
			Router router = new Router(getContext());
			router.setDefaultMatchingMode(Template.MODE_EQUALS);

			router.attach("/v1/results", resultHandler);

			return router;
		}
	}
	
	
}
