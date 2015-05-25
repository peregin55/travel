package parse

import play.api.libs.functional.syntax._
import play.api.libs.json._
import models._
import models.Category.Category
import org.joda.time.DateTime

/** JsonWrite
 *  @author Stephen Johnson
 */
object JsonWrite {
  implicit val coordWrites: Writes[Coord] = Json.writes[Coord]
  implicit val categoryWrites: Writes[Category] = new Writes[Category] {
    def writes(c: Category): JsValue = JsString(c.toString)
  }
  implicit val dateTimeWrites: Writes[DateTime] = new Writes[DateTime] {
    def writes(d: DateTime): JsValue = JsString(d.toString())
  }
  implicit val poiWrites: Writes[POI] = new Writes[POI] {
    def writes(poi: POI) = Json.obj (
      "name" -> poi.name,
      "coord" -> poi.coord,
      "description" -> poi.description,
      "category" -> poi.category)
  }
  implicit val reservationWrites: Writes[Reservation] = new Writes[Reservation] {
    def writes(r: Reservation) = Json.obj(
      "name" -> r.name,
      "startPoi" -> r.startPoi,
      "startDate" -> r.startDate,
      "endDate" -> r.endDate,
      "price" -> r.price,
      "canOverlap" -> r.canOverlap)
  }
  implicit val itineraryWrites: Writes[Itinerary] = new Writes[Itinerary] {
    def writes(i: Itinerary) = Json.obj(
      "name" -> i.name,
      "reservations" -> i.reservations)
  }
}
