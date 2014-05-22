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
    "vars": [ "vmType", "vm" , "metric" , "value" , "timestamp" ]
  } ,
  "results": {
    "bindings": [
      {
      	"vmType": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/FrontendVM" } ,
        "vm": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/7e96d4d0-b852-11e3-a5e2-0800200c9a66" } ,
        "metric": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/CpuUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.9271605239215519" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1380900342555" }
      } ,
      {
      	"vmType": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/FrontendVM" } ,
        "vm": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/7e96d4d0-b852-11e3-a5e2-0800200c9a66" } ,
        "metric": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/CpuUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.9033974192121722" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1380900344593" }
      } ,
      {
      	"vmType": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/FrontendVM" } ,
        "vm": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/7e96d4d0-b852-11e3-a5e2-0800200c9a66" } ,
        "metric": { "type": "uri" , "value": "http://www.modaclouds.eu/rdfs/1.0/monitoring/CpuUtilization" } ,
        "value": { "datatype": "http://www.w3.org/2001/XMLSchema#double" , "type": "typed-literal" , "value": "0.8748697113737773" } ,
        "timestamp": { "datatype": "http://www.w3.org/2001/XMLSchema#integer" , "type": "typed-literal" , "value": "1380900338499" }
      }
    ]
  }
}
```