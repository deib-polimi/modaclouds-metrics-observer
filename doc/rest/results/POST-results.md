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

A monitoring datum in [json rdf serialization format](https://dvcs.w3.org/hg/rdf/raw-file/default/rdf-json/index.html) of the following rdf model (here in ttl format for simplicity):

```
[] <http://www.modaclouds.eu/rdfs/1.0/monitoringdata#metric> ?metric;
<http://www.modaclouds.eu/rdfs/1.0/monitoringdata#timestamp> ?timestamp;
<http://www.modaclouds.eu/rdfs/1.0/monitoringdata#value> ?value;
<http://www.modaclouds.eu/rdfs/1.0/monitoringdata#resourceId> ?resourceId .
```


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
  "a.unique.id.for.the.datum" : { 
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#metric" : [ { 
      "type" : "literal" ,
      "value" : "FrontendCPUUtilization"
    }
     ] ,
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#timestamp" : [ { 
      "type" : "literal" ,
      "value" : "1409223851698" ,
      "datatype" : "http://www.w3.org/2001/XMLSchema#integer"
    }
     ] ,
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#value" : [ { 
      "type" : "literal" ,
      "value" : "0.11416644992590952e0" ,
      "datatype" : "http://www.w3.org/2001/XMLSchema#double"
    }
     ] ,
    "http://www.modaclouds.eu/rdfs/1.0/monitoringdata#resourceId" : [ { 
      "type" : "literal" ,
      "value" : "frontend1" ,
      "datatype" : "http://www.w3.org/2001/XMLSchema#string"
    }
     ]
  }
}
```
