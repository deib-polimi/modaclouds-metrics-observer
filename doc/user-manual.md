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

* Create a result handler extending the ResultHandler class, and overriding method `getData` so to handle incoming data.
* Create a metrics obServer extending the MetricsObServer class and passing to the super constructor the listening port and your result handler class.

## Code Samples

Result handler class example:

```java
public class MyResultHandler extends ResultsHandler {

	@Override
	public void getData(List<String> varNames,
			List<Map<String, Variable>> bindings) {
		String value;
		for (Map<String, Variable> m : bindings) {
			String datum = "";
			int last = varNames.size();
			for (int i = 0; i < last; i++) {
				Variable var = m.get(varNames.get(i));
				if (var != null) {
					value = var.getValue();
					datum += value + (i == last - 1 ? "" : " ");
				}
			}
			System.out.println(datum);
		}
	}

}
```

Observer class example:
```java
public class ExampleObServer extends MetricsObServer {

	public ExampleObServer(int listeningPort) {
		super(listeningPort, MyResultHandler.class);
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