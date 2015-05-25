package models
import scala.collection.mutable.MultiMap

/** Graph 
 *  @author Stephen Johnson
 */
object Graph {
  def apply[A](elems: Seq[A]): Graph[A] = {
    Graph(elems.map(_ -> elems).toMap)
  }
  def apply[A](adjacencyList: MultiMap[A,A]): Graph[A] = {
    Graph(adjacencyList.map(kv => (kv._1, kv._2.toVector)).toMap)
  }
}

case class Graph[A](adjacencyList: Map[A,Seq[A]]) {
  def nodes: Seq[A] = adjacencyList.keySet.toSeq
  def depthFirstTraversal(start: A): Seq[A] = {
    import scala.collection.mutable.Set
    if (!adjacencyList.contains(start)) {
      return Seq()
    }
    var seen: Set[A] = Set()
    def dfs(node: A): Seq[A] = {
      seen += node
      var traversal: Seq[A] = Vector(node)
      for (neighbor <- adjacencyList(node)) {
        if (!seen.contains(neighbor)) {
          traversal ++= dfs(neighbor) :+ node
        }
      }
      traversal
    }
    dfs(start)
  }

  def minSpanningTree(implicit ctx: DistMeasurable[A]): Graph[A] = {
    import scala.collection.mutable.{Map, Set, HashMap}
    if (adjacencyList.isEmpty) {
      return Graph(adjacencyList)
    }
    val treeNodes: Set[Node] = Set()
    val remainingMap: Map[A, Node] = Map()
    remainingMap ++= adjacencyList.keySet.map(x => (x, Node(x, Double.PositiveInfinity, None))).toMap
    remainingMap.head._2.distance = 0.0
    while (!remainingMap.isEmpty) {
      val node = remainingMap.values.min
      remainingMap -= node.elem
      treeNodes += node
      for (neighborElem <- adjacencyList(node.elem)) {
          remainingMap.get(neighborElem) match {
            case Some(neighbor) =>
              val newDistance = ctx.distance(node.elem, neighborElem)
              if (newDistance < neighbor.distance) {
                neighbor.distance = newDistance
                neighbor.parent = Option(node)
              }
            case None =>
          }
        }
      }
    // build graph representing minimum spanning tree
    val graph = new HashMap[A, Set[A]] with MultiMap[A, A]
    for (node <- treeNodes) {
      node.parent match {
        case Some(parent) =>
          graph.addBinding(node.elem, parent.elem)
          graph.addBinding(parent.elem, node.elem)
        case None =>
          if (!graph.contains(node.elem)) {
            // root won't have a parent.  Add if child did not already trigger an addition
            graph.put(node.elem, Set[A]())
          }
      }
    }
    Graph(graph)
  }
  private case class Node(elem: A, var distance: Double, var parent: Option[Node]) extends Ordered[Node] {
    override def compare(that: Node) = distance.compare(that.distance)
  }
}
