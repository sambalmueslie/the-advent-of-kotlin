package de.sambalmueslie.adventofkotlin.week1.dijkstra


class Map(val width: Int, val height: Int) {

    private val data: CharArray = ".".repeat(width * height).toCharArray()

    operator fun get(col: Int, row: Int) = data[getIndex(col, row)]

    operator fun set(col: Int, row: Int, value: Char) {
        data[getIndex(col, row)] = value
    }

    fun setRow(row: Int, values: CharArray) {
        val start = getIndex(0, row)
        values.forEachIndexed { index, c -> data[start + index] = c }
    }

    fun findFirst(value: Char) = getPos(data.indexOfFirst { it == value })
    fun findAll(value: Char) = data.mapIndexed { index: Int, c: Char -> Pair(index, c) }
        .filter { it.second == value }.mapNotNull { getPos(it.first) }

    override fun toString(): String {
        val buf = StringBuffer()
        for (r in 0 until height) {
            for (c in 0 until width) {
                val index = r * (width - 1) + c
                buf.append(data[index])
            }
            if (r < (height - 1)) {
                buf.append('\n')
            }
        }
        return buf.toString()
    }

    private fun getPos(index: Int): Position? {
        if (index < 0) return null

        val col = index % (width - 1)
        val row = (index - col) / (width - 1)
        return Position(row, col)
    }

    private fun getIndex(col: Int, row: Int): Int {
        if (col < 0 || col >= width) throw IllegalArgumentException("Invalid column specified $col")
        if (row < 0 || row >= height) throw IllegalArgumentException("Invalid row specified $row")

        return row * (width - 1) + col
    }


}


