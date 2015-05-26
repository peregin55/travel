travel
=========
A service that will construct a travel plan based on the locations provided.  For instance, to create an itinerary starting in Boston and continuing to San Francisco, Peru, New Delhi, and Paris for a duration of 6 months, enter the following URL:

    http://localhost:9000/itinerary?loc=Boston&loc=San+Francisco&loc=Peru&loc=New+Delhi&loc=Paris&months=6

The itinerary produced will contain an optimal path through all locations, starting from the first location listed, with the following assumptions:
  * Direct flights only exist between locations that are located closest to each other
  * The user will only visit the locations provided and no other

The service will perform the following steps to create an itinerary:
  1. Query Google Geocoding API for the latitude and longitude of the given locations
  2. Generate a complete graph representing all locations, weighted by the distance
  3. Construct a minimum spanning tree of locations
  4. Iterate over the MST starting at the first location entered
  5. Build Itinerary instance of the locations visted, including the return journey

## build and run
After downloading the source, navigate to the root of the project and use the following command to build and start a local server:

    sbt run

In order to build the service you will need proper JVM/Scala environment with Scala Build Tool (SBT).

## Sample Response 
The URL above produces the following:

```
{
    "name": "Trip",
    "reservations": [
        {
            "canOverlap": false,
            "endDate": "2015-05-25T16:03:26.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            },
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-05-25T16:03:26.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-06-17T16:10:56.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 37.7749295,
                    "lon": -122.4194155
                },
                "description": null,
                "name": "San Francisco"
            },
            "name": "San Francisco",
            "price": 0.0,
            "startDate": "2015-06-17T16:10:56.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 37.7749295,
                    "lon": -122.4194155
                },
                "description": null,
                "name": "San Francisco"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-07-10T16:18:26.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            },
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-07-10T16:18:26.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-08-02T16:25:56.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 48.856614,
                    "lon": 2.3522219
                },
                "description": null,
                "name": "Paris"
            },
            "name": "Paris",
            "price": 0.0,
            "startDate": "2015-08-02T16:25:56.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 48.856614,
                    "lon": 2.3522219
                },
                "description": null,
                "name": "Paris"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-08-25T16:33:26.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 28.6139391,
                    "lon": 77.2090212
                },
                "description": null,
                "name": "New Delhi"
            },
            "name": "New Delhi",
            "price": 0.0,
            "startDate": "2015-08-25T16:33:26.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 28.6139391,
                    "lon": 77.2090212
                },
                "description": null,
                "name": "New Delhi"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-09-17T16:40:56.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 48.856614,
                    "lon": 2.3522219
                },
                "description": null,
                "name": "Paris"
            },
            "name": "Paris",
            "price": 0.0,
            "startDate": "2015-09-17T16:40:56.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 48.856614,
                    "lon": 2.3522219
                },
                "description": null,
                "name": "Paris"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-10-10T16:48:26.790-04:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            },
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-10-10T16:48:26.790-04:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-11-02T15:55:56.790-05:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": -9.189967,
                    "lon": -75.015152
                },
                "description": null,
                "name": "Peru"
            },
            "name": "Peru",
            "price": 0.0,
            "startDate": "2015-11-02T15:55:56.790-05:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": -9.189967,
                    "lon": -75.015152
                },
                "description": null,
                "name": "Peru"
            }
        },
        {
            "canOverlap": false,
            "endDate": "2015-11-25T16:03:26.790-05:00",
            "endPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            },
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-11-25T16:03:26.790-05:00",
            "startPoi": {
                "category": null,
                "coord": {
                    "lat": 42.3600825,
                    "lon": -71.0588801
                },
                "description": null,
                "name": "Boston"
            }
        }
    ]
}
```
