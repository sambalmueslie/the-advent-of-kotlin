package de.sambalmueslie.adventofkotlin.week1.dijkstra

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test


internal class MapTest {

    private var map: Map = Map(0, 0)

    @BeforeEach
    fun setup() {
        map = Map(20, 5)
        map.setRow(0, ".......****.........".toCharArray())
        map.setRow(1, "......X...B*........".toCharArray())
        map.setRow(2, "..........B.*.......".toCharArray())
        map.setRow(3, "........BBB..**S....".toCharArray())
        map.setRow(4, "....................".toCharArray())
    }

    @Test
    fun get() {
        assertEquals('.', map[0, 0])
        assertEquals('*', map[8, 0])
        assertEquals('.', map[19, 0])
        assertEquals('.', map[0, 4])
        assertEquals('.', map[19, 4])
        assertEquals('X', map[6, 1])
        assertEquals('B', map[10, 2])
        assertEquals('S', map[15, 3])
    }

    @Test
    fun set() {
        assertEquals('*', map[8, 0])
        map[8, 0] = '.'
        assertEquals('.', map[8, 0])
    }

    @Test
    fun setRow() {
        assertEquals('*', map[7, 0])
        assertEquals('*', map[8, 0])
        assertEquals('*', map[9, 0])
        assertEquals('*', map[10, 0])
        map.setRow(0, ".......BBBB.........".toCharArray())
        assertEquals('B', map[7, 0])
        assertEquals('B', map[8, 0])
        assertEquals('B', map[9, 0])
        assertEquals('B', map[10, 0])
    }

    @Test
    fun find() {
        val start = map.findFirst('S')
        val end = map.findFirst('X')

        assertEquals(Pair(15, 3), start)
        assertEquals(Pair(6, 1), end)
    }

    @Test
    fun findAll() {
        val blocks = map.findAll('B')
        blocks.sortedWith(compareBy({ it.column }, { it.row }))
        assertEquals(5, blocks.size)
        assertEquals(Pair(10, 1), blocks[0])
        assertEquals(Pair(10, 2), blocks[1])
        assertEquals(Pair(8, 3), blocks[2])
        assertEquals(Pair(9, 3), blocks[3])
        assertEquals(Pair(10, 3), blocks[4])
    }
}
