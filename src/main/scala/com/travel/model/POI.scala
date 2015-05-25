package com.travel.model
import Category.Category
import scala.math._

/** Location
 *  @author Stephen Johnson
 */
object Location {
  implicit object LocationDistMeasurable extends DistMeasurable[Location] {
    def distance(first: Location, second: Location) = first.coord - second.coord
  }
}
trait Location {
  def coord: Coord
}

object Coord {
  private val EarthRadiusKm = 6371
}
case class Coord(lat: Double, lon: Double) {
  /** Return geodetic distance between two locations.
      Uses Haversine formula: http://www.movable-type.co.uk/scripts/latlong.html
  */
  def -(other: Coord) = {
    val lat1 = lat
    val lon1 = lon
    val lat2 = other.lat
    val lon2 = other.lon

    val dLat = toRadians(lat2-lat1)
    val dLon = toRadians(lon2-lon1)
    val a = sin(dLat/2.0) * sin(dLat/2.0) +
            cos(toRadians(lat1)) * cos(toRadians(lat2)) *
            sin(dLon/2.0) * sin(dLon/2.0)
    val c = 2.0 * atan2(sqrt(a), sqrt(1.0-a))
    Coord.EarthRadiusKm * c
  }
}

object POI {
  def apply(name: String, coord: Coord): POI = POI(name, coord, None, None)
}
case class POI(name: String,
               coord: Coord,
               description: Option[String],
               category: Option[Category]) extends Location
case class City(name: String, coord: Coord, pois: Seq[POI]) extends Location
case class Country(name: String, coord: Coord, cities: Seq[City]) extends Location

object Category extends Enumeration {
  type Category = Value
  val Museum, Natural, Architecture, Restaurant, Airport = Value
}


