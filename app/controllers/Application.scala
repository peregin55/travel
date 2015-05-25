package controllers

import org.joda.time.DateTime
import play.api._
import play.api.mvc._
import play.api.libs.ws._
import play.api.libs.json._
import scala.concurrent.Future
import play.api.Play.current
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import models._
import parse.JsonWrite._
import parse.GoogleJsonRead._


object Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def itinerary = Action.async { request =>
    val locations: Seq[String] = request.queryString.getOrElse("loc", Seq())
    val numMonths: Int = request.queryString.getOrElse("months", Seq("12")).head.toInt
    val locRequests: Seq[WSRequestHolder] = locations.map { location =>
      WS.url("http://maps.googleapis.com/maps/api/geocode/json")
        .withQueryString("address" -> location)
        .withRequestTimeout(10*1000)
    }
    val futureLocResponses: Future[Seq[WSResponse]] = Future.sequence(locRequests.map(_.get()))
    futureLocResponses.map { locResponses =>
      val latlons: Seq[Option[POI]] = locResponses.map { payload => 
        val coord: Option[Coord] = ((payload.json \ "results")(0) \ "geometry" \ "location").asOpt[Coord]
        val name: Option[String] = (((payload.json \ "results")(0) \ "address_components")(0) \ "long_name").asOpt[String]
        for {
          coordData <- coord
          nameData <- name
        } yield(POI(nameData, coordData))
      }
      val pois:Seq[POI] = latlons.flatten
      val (startDate, stopDate) = calcDates(numMonths)
      val itinerary = Itinerary("Trip", pois, startDate, stopDate)
      Ok(Json.toJson(itinerary))
    }
  }
  def calcDates(numMonths: Int): (DateTime, DateTime) = {
    val start = new DateTime()
    val stop = start.plusMonths(numMonths)
    (start, stop)
  }
}
