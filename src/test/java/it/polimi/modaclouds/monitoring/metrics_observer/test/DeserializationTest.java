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
package it.polimi.modaclouds.monitoring.metrics_observer.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;
import it.polimi.modaclouds.monitoring.dcfactory.wrappers.DDAOntology;
import it.polimi.modaclouds.monitoring.metrics_observer.JSONMonitoringDataParser;
import it.polimi.modaclouds.monitoring.metrics_observer.MonitoringDatum;
import it.polimi.modaclouds.monitoring.metrics_observer.MonitoringDatumHandler;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import java.util.UUID;

import org.junit.Test;

import com.hp.hpl.jena.datatypes.xsd.XSDDatatype;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.vocabulary.RDF;

public class DeserializationTest {

	@Test
	public void test() {
		String monDatumInstanceURI = DDAOntology.MonitoringDatum + "#"
				+ UUID.randomUUID().toString();
		Model m = ModelFactory.createDefaultModel();
		m.createResource(monDatumInstanceURI)
				.addProperty(RDF.type, DDAOntology.MonitoringDatum)
				.addProperty(
						DDAOntology.metric,
						m.createTypedLiteral("cpuutilization",
								XSDDatatype.XSDstring))
				.addProperty(
						DDAOntology.timestamp,
						m.createTypedLiteral("1409223851698",
								XSDDatatype.XSDstring))
				.addProperty(DDAOntology.value,
						m.createTypedLiteral("0.4", XSDDatatype.XSDdouble))
				.addProperty(
						DDAOntology.resourceId,
						m.createTypedLiteral("exampleResource",
								XSDDatatype.XSDstring));
		StringWriter w = new StringWriter();
		m.write(w, "RDF/JSON");
		try {
			new MonitoringDatumHandler() {
				@Override
				public void getData(List<MonitoringDatum> monitoringData) {
					assertFalse(monitoringData.isEmpty());
					MonitoringDatum monitoringDatum = monitoringData.iterator().next();
					assertEquals(monitoringDatum.getMetric(), "cpuutilization");
					assertEquals(monitoringDatum.getResourceId(), "exampleResource");
					assertEquals(monitoringDatum.getTimestamp(), "1409223851698");
					assertEquals(monitoringDatum.getValue(), "0.4");
				}
			}.getData(JSONMonitoringDataParser.jsonToMonitoringDatum(w.toString()));
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
	}

	
}
