package models

/** DistMeasurable 
 *  @author Stephen Johnson
 */
trait DistMeasurable[-A] {
  def distance(first: A, second: A): Double
}
