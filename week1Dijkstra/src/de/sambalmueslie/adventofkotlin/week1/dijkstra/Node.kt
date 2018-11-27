package de.sambalmueslie.adventofkotlin.week1.dijkstra


data class Node(
    val pos: Pair<Int, Int>,
    val distanceToSource: Double = Double.MAX_VALUE,
    val previousNode: Node? = null
) {}