[Documentation table of contents](TOC.md) / User Manual

# User Manual

## Installation

You can download the jar from https://github.com/deib-polimi/modaclouds-metrics-observer/releases or add the dependency to your Maven project.

Releases repository:
```xml
<repositories>
	...
	<repository>
        <id>deib-polimi-releases</id>
        <url>https://github.com/deib-polimi/deib-polimi-mvn-repo/raw/master/releases</url>
	</repository>
	...
</repositories>
```

Snapshots repository:
```xml
<repositories>
	...
	<repository>
        <id>deib-polimi-snapshots</id>
        <url>https://github.com/deib-polimi/deib-polimi-mvn-repo/raw/master/snapshots</url>
	</repository>
	...
</repositories>
```

Dependency:
```xml
<dependencies>
	<dependency>
		<groupId>it.polimi.modaclouds.monitoring</groupId>
		<artifactId>metrics-observer</artifactId>
		<version>VERSION</version>
	</dependency>
</dependencies>
```

## Usage

* Create a monitoring datum handler extending the MonitoringDatumHandler class, and overriding method `getData` so to handle incoming data.
* Create a metrics obServer extending the MetricsObServer class and passing to the super constructor the listening port, the path and your monitoring datum handler class.

## Code Samples

Result handler class example:

```java
public class CVSResultHandler extends MonitoringDatumHandler {

	@Override
	public void getData(List<MonitoringDatum> monitoringData) {
		String observerTimestamp = Long.toString(new Date().getTime());
		for (MonitoringDatum monitoringDatum : monitoringData) {
			System.out.println(observerTimestamp + ","
					+ monitoringDatum.getResourceId() + ","
					+ monitoringDatum.getMetric() + ","
					+ monitoringDatum.getValue() + ","
					+ monitoringDatum.getTimestamp());
		}
	}

}
```

Observer class example:
```java
public class CVSObServer extends MetricsObServer {
	public CVSObServer(int listeningPort) {
		super(listeningPort, "/v1/results", CVSResultHandler.class);
	}
	
}
```

Start the obServer
```java	
CVSObServer observer = new CVSObServer(port);
try {
	observer.start();
} catch (Exception e) {
	e.printStackTrace();
}
```
