package parse

import models.Coord
import play.api.libs.functional.syntax._
import play.api.libs.json._

/** GoogleJsonRead
 *  @author Stephen Johnson
 */
object GoogleJsonRead {
  implicit val coordReads: Reads[Coord] =
    ((JsPath \ "lat").read[Double] and (JsPath \ "lng").read[Double]) (Coord.apply _)
}
