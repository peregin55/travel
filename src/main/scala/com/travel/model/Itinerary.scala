package com.travel.model
import org.joda.time.DateTime
import org.joda.time.Duration
import com.travel.model.Location._

/** Itinerary
 *  @author Stephen Johnson
 */
object Itinerary {
  def apply(name: String, pois: Seq[POI], startDate: DateTime, endDate: DateTime): Itinerary = {
    if (pois.isEmpty) {
      return Itinerary(name, Vector.empty)
    }
    val poiTree = Graph(pois).minSpanningTree
    val completePois: Seq[POI] = poiTree.depthFirstTraversal(pois.head)
    val dateDuration = new Duration((endDate.getMillis - startDate.getMillis) / (completePois.size-1))
    def dateGenerator(date: DateTime): Stream[DateTime] = date #:: dateGenerator(date.plus(dateDuration))
    val reservations: Seq[Reservation] = completePois.zip(dateGenerator(startDate)).map{ case (poi, date) => Reservation(poi.name, poi, date, 0.0) }
    Itinerary(name, reservations)
  }
}

case class Itinerary(name: String, reservations: Seq[Reservation])
object Reservation {
  def apply(name: String,
            poi: POI,
            date: DateTime,
            price: Double): Reservation = Reservation(name, poi, poi, date, date, price, false)
}
case class Reservation(name: String,
                       startPoi: POI,
                       endPoi: POI,
                       startDate: DateTime,
                       endDate: DateTime,
                       price: Double,
                       canOverlap: Boolean)
