package com.travel.model
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class ItineraryTest extends FunSuite {
  test("Itinerary can be built from list of POIs") {
    val pois = List(POI("Boston", Coord(42.360082, -71.058880)),
                    POI("Paris", Coord(48.856614, 2.352222)),
                    POI("San Francisco", Coord(37.774929, -122.419416)),
                    POI("NYC", Coord(40.712784, -74.005941)))
    println(Itinerary("My Trip", pois, new DateTime(2015, 1, 1, 0, 0, 0, DateTimeZone.UTC),
      new DateTime(2015, 5, 1, 0, 0, 0, DateTimeZone.UTC)))
  }
}
