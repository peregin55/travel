package models
import org.scalatest.FunSuite
import org.scalatest.Matchers._
import org.joda.time.DateTime
import org.joda.time.DateTimeZone

class ItineraryTest extends FunSuite {
  test("Itinerary contain POIs") {
    val boston = POI("Boston", Coord(42.360082, -71.058880))
    val pois = List(boston,
                    POI("San Francisco", Coord(37.774929, -122.419416)),
                    POI("Paris", Coord(48.856614, 2.3522219)),
                    POI("NYC", Coord(40.712784, -74.005941)))
    val itinerary = Itinerary("My Trip", pois, new DateTime(2015, 1, 1, 0, 0, 0, DateTimeZone.UTC),
      new DateTime(2015, 5, 1, 0, 0, 0, DateTimeZone.UTC))
    // println(itinerary)
    itinerary.reservations.head.startPoi should equal (boston)
    itinerary.reservations.last.startPoi should equal (boston)
  }
}
