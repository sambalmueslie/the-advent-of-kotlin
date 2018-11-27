package de.sambalmueslie.adventofkotlin.week1.dijkstra


data class Vertex(
    val pos: Position,
    var distanceToSource: Double = Double.MAX_VALUE,
    var previous: Vertex? = null
) {
}