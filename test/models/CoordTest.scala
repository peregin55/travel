package models
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class CoordTest extends FunSuite {
  val epsilon = 1.0
  test("Subtracting Coords should produce geodetic distance") {
    val c1 = Coord(50.066389, 5.714722)
    val c2 = Coord(58.643889, 3.07)
    (c1 - c2) should equal (969.0 +- epsilon)
  }
  test("Subtracting identical Coords should produce 0") {
    val c1 = Coord(50.1234, 92.1234)
    (c1 - c1) should equal (0.0)
  }
  test("Should produce approximate distance from San Francisco to NYC") {
    val sfo = Coord(37.733795, -122.446747)
    val nyc = Coord(40.730610, -73.935242)
    (sfo - nyc) should equal (4138.0 +- epsilon)
  }
}
