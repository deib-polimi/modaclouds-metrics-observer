[Documentation table of contents](../../TOC.md) / [API Reference](../../api.md) / POST-results

# Results

	POST /results

## Description
Send results to the metrics observer.

***

## URL Parameters

None

***

## Data Parameters

A json object with a list of monitoring data with information about the monitored resource.

***

## Response

**Status:** **200 OK**

**Body:** `{Result succesfully received}`

***

## Errors

None

***

## Example
**Request**

	POST /v1/results
	
``` json
{
  "head": {
    "vars": [ "target" , "metric" , "value" , "timestamp" ]
  } ,
  "results": {
    "bindings": [
      {
        "target": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/WinVM1" } ,
        "metric": { "type": "uri" , "value": "WinVMCPUUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.0994758059111746e0" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1404468767912" }
      } ,
      {
        "target": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/WinVM1" } ,
        "metric": { "type": "uri" , "value": "WinVMCPUUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.0875758059111746e0" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1404468769212" }
      } ,
      {
        "target": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/WinVM1" } ,
        "metric": { "type": "uri" , "value": "WinVMCPUUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.0775758059111746e0" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1404468772212" }
      }
    ]
  }
}
```