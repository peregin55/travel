travel
=========
Service that will construct a travel plan based on places you would like to go.  For instance, if you would like to travel from Boston to San Francisco, Peru, New Dehli, and Paris for a duration of 6 months, enter the following URL:

    http://localhost:9000/itinerary?loc=Boston&loc=San+Francisco&loc=Peru&loc=New+Delhi&loc=Paris&months=6

The service will perform the following steps:
1. Use Google APIs to query for the latitude and longitude for the given locations
2. Generate a complete graph representing all of the locations, weighted by the geodetic distance
3. Construct a minimum spanning tree of locations
4. Iterate over the MST, starting at the first location entered, and iterate back for the return journey

## Sample Output
```
{
    "name": "Trip",
    "reservations": [
        {
            "canOverlap": false,
            "endDate": "2015-05-25T15:43:33.364-04:00",
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-05-25T15:43:33.364-04:00",
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
            "endDate": "2015-06-17T15:51:03.364-04:00",
            "name": "San Francisco",
            "price": 0.0,
            "startDate": "2015-06-17T15:51:03.364-04:00",
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
            "endDate": "2015-07-10T15:58:33.364-04:00",
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-07-10T15:58:33.364-04:00",
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
            "endDate": "2015-08-02T16:06:03.364-04:00",
            "name": "Paris",
            "price": 0.0,
            "startDate": "2015-08-02T16:06:03.364-04:00",
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
            "endDate": "2015-08-25T16:13:33.364-04:00",
            "name": "New Delhi",
            "price": 0.0,
            "startDate": "2015-08-25T16:13:33.364-04:00",
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
            "endDate": "2015-09-17T16:21:03.364-04:00",
            "name": "Paris",
            "price": 0.0,
            "startDate": "2015-09-17T16:21:03.364-04:00",
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
            "endDate": "2015-10-10T16:28:33.364-04:00",
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-10-10T16:28:33.364-04:00",
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
            "endDate": "2015-11-02T15:36:03.364-05:00",
            "name": "Peru",
            "price": 0.0,
            "startDate": "2015-11-02T15:36:03.364-05:00",
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
            "endDate": "2015-11-25T15:43:33.364-05:00",
            "name": "Boston",
            "price": 0.0,
            "startDate": "2015-11-25T15:43:33.364-05:00",
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

## build and run
After downloading the source, navigate to the root of the project and use the following command to build and start a local server:

    sbt run

In order to build the service you will need proper JVM/Scala environment with Scala Build Tool (SBT).
