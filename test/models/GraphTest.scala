package models
import org.scalatest.FunSuite
import org.scalatest.Matchers._

class GraphTest extends FunSuite {
  case class Elem(name: String)
  case class Pair(one: Elem, two: Elem) {
    override def equals(other: Any): Boolean = other match {
      case Pair(otherOne, otherTwo) => (otherOne == one && otherTwo == two) || (otherOne == two && otherTwo == one)
      case _ => false
    }
  }
  // see Cormen et al, Intro to Algorithms p625 for graph diagram
  val graph = Graph(Map(Elem("a") -> Seq(Elem("b"), Elem("h")),
                        Elem("b") -> Seq(Elem("a"), Elem("c"), Elem("h")),
                        Elem("c") -> Seq(Elem("b"), Elem("d"), Elem("f"), Elem("i")),
                        Elem("d") -> Seq(Elem("c"), Elem("e"), Elem("f")),
                        Elem("e") -> Seq(Elem("d"), Elem("f")),
                        Elem("f") -> Seq(Elem("e"), Elem("d"), Elem("c"), Elem("g")),
                        Elem("g") -> Seq(Elem("f"), Elem("h"), Elem("i")),
                        Elem("h") -> Seq(Elem("g"), Elem("i"), Elem("a"), Elem("b")),
                        Elem("i") -> Seq(Elem("h"), Elem("g"), Elem("c"))))
  val mst = Graph(Map(
    Elem("a") -> Vector(Elem("b")),
    Elem("b") -> Vector(Elem("c"), Elem("a")),
    Elem("c") -> Vector(Elem("b"), Elem("i"), Elem("d"), Elem("f")),
    Elem("d") -> Vector(Elem("c"), Elem("e")),
    Elem("e") -> Vector(Elem("d")),
    Elem("f") -> Vector(Elem("c"), Elem("g")),
    Elem("g") -> Vector(Elem("h"), Elem("f")),
    Elem("h") -> Vector(Elem("g")),
    Elem("i") -> Vector(Elem("c"))))

  test("Tree should provide depth-first traversal") {
    val expected = Vector(Elem("a"), Elem("b"), Elem("c"), Elem("i"), Elem("c"), Elem("d"),
                          Elem("e"), Elem("d"), Elem("c"), Elem("f"), Elem("g"), Elem("h"),
                          Elem("g"), Elem("f"), Elem("c"), Elem("b"), Elem("a"))
    mst.depthFirstTraversal(Elem("a")) should equal (expected)
  }

  test("Graph should provide depth-first traversal") {
    val expected = Vector(Elem("a"), Elem("b"), Elem("c"), Elem("d"), Elem("e"), Elem("f"), Elem("g"),
                          Elem("h"), Elem("i"), Elem("h"), Elem("g"), Elem("f"), Elem("e"), Elem("d"),
                          Elem("c"), Elem("b"), Elem("a"))
    graph.depthFirstTraversal(Elem("a")) should equal (expected)
  }

  test("Graph should provide minimum spanning tree") {
    implicit object ElemDistMeasurable extends DistMeasurable[Elem] {
      def distance(first: Elem, second: Elem) = {
        val p = Pair(first, second)
        if (p == Pair(Elem("a"), Elem("b"))) 4
        else if (p == Pair(Elem("b"), Elem("c"))) 8
        else if (p == Pair(Elem("c"), Elem("d"))) 7
        else if (p == Pair(Elem("d"), Elem("e"))) 9
        else if (p == Pair(Elem("e"), Elem("f"))) 10
        else if (p == Pair(Elem("f"), Elem("g"))) 2
        else if (p == Pair(Elem("g"), Elem("h"))) 1
        else if (p == Pair(Elem("h"), Elem("i"))) 7
        else if (p == Pair(Elem("a"), Elem("h"))) 8
        else if (p == Pair(Elem("b"), Elem("h"))) 11
        else if (p == Pair(Elem("c"), Elem("i"))) 2
        else if (p == Pair(Elem("c"), Elem("f"))) 4
        else if (p == Pair(Elem("d"), Elem("f"))) 14
        else if (p == Pair(Elem("g"), Elem("i"))) 6
        else throw new IllegalArgumentException(s"Illegal pair $p")
      }
    }
    graph.minSpanningTree should equal(mst)
  }
}
