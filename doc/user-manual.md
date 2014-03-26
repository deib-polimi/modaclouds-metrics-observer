[Documentation table of contents](TOC.md)

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
		<artifactId>monitoring-metrics-observer</artifactId>
		<version>VERSION</version>
	</dependency>
</dependencies>
```

## Usage

* Create a metrics observer by extending class MetricsObServer.
* Override method `getData` so to handle incoming data.

## Code Samples

Observer class example:
```java
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
			for (String s: m.keySet()) {
				m.get(s);
			}
		}
	}
}
```

Start the obServer
```java	
ExampleObServer observer = new ExampleObServer(8123);
try {
	observer.start();
} catch (Exception e) {
	e.printStackTrace();
}
```