package de.sambalmueslie.adventofkotlin.week1.dijkstra


/**
 * Date: 26.11.2018 22:59
 */
abstract class BaseShortestPathAlgorithm : ShortestPathAlgorithm {


    final override fun addPath(mapString: String) = findPath(parseMap(mapString)).toString()

    protected abstract fun findPath(map: Map): Map

    protected fun findSource(map: Map) = map.findFirst(START_MARKER)
    protected fun findEnd(map: Map) = map.findFirst(END_MARKER)
    protected fun isBlocked(map: Map, position: Position) = map[position.column, position.row] == BLOCK_MARKER

    private fun parseMap(mapString: String): Map {
        val rows = mapString.split("\n")
        if (rows.isEmpty()) throw IllegalArgumentException("Invalid map string $mapString")

        val width = rows[0].length
        val height = rows.size

        val map = Map(width, height)
        rows.map { it.toCharArray() }.forEachIndexed { row, values -> map.setRow(row, values) }

        return map
    }

    companion object {
        const val START_MARKER = 'S'
        const val END_MARKER = 'X'
        const val WAY_MARKER = '*'
        const val BLOCK_MARKER = 'B'
    }
}