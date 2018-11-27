package de.sambalmueslie.adventofkotlin.week1.dijkstra

/**
 * Date: 26.11.2018 22:57
 */

class DijkstraAlgorithm : BaseShortestPathAlgorithm() {

    override fun findPath(map: Map): Map {
        val source = findSource(map)?.toVertex()
            ?: throw IllegalArgumentException("Map without source cannot processed")
        val target = findEnd(map)?.toVertex()
            ?: throw IllegalArgumentException("Map without end cannot processed")
        val q = initVertexSet(map, source, target)

        while (q.isNotEmpty()) {
            val u = q.findMinDistance()

            q -= u

            val isTarget = u.pos == target.pos
            if (isTarget) break

            val neighbors = q.getNeighbours(map, u)
            for (neighbor in neighbors) {
                val alt = u.distanceToSource + distance(u, neighbor)
                if (alt < neighbor.distanceToSource) {
                    neighbor.distanceToSource = alt
                    neighbor.previous = u
                }
            }
        }

        val path = mutableListOf<Vertex>(target)
        var u: Vertex? = target
        while (u?.previous != null) {
            u = u.previous
            if (u != null) {
                path.add(0, u)
            }
        }

        for (p in path) {
            map[p.pos.column, p.pos.row] = WAY_MARKER
        }

        return map
    }

    private fun distance(u: Vertex, neighbor: Vertex): Double {
        return if (u.pos.row == neighbor.pos.row || u.pos.column == neighbor.pos.column) {
            1.0
        } else {
            1.5
        }
    }


    private fun initVertexSet(map: Map, source: Vertex, target: Vertex): MutableList<Vertex> {
        source.distanceToSource = 0.0
        val result = mutableListOf<Vertex>()
        for (r in 0 until map.height) {
            for (c in 0 until map.width) {
                val pos = Position(r, c)
                val isBlocked = isBlocked(map, pos)
                if (isBlocked) continue
                result += when (pos) {
                    source.pos -> source
                    target.pos -> target
                    else -> pos.toVertex()
                }
            }
        }
        return result
    }


}

private fun List<Vertex>.getNeighbours(map: Map, u: Vertex): List<Vertex> {
    val rowMin = Math.max(0, u.pos.row - 1)
    val rowMax = Math.min(map.height - 1, u.pos.row + 1)
    val colMin = Math.max(0, u.pos.column - 1)
    val colMax = Math.min(map.width - 1, u.pos.column + 1)

    val result = mutableListOf<Vertex>()
    for (r in rowMin..rowMax) {
        for (c in colMin..colMax) {
            result += this.find { it.pos == Position(r, c) } ?: continue
        }
    }
    return result
}

private fun List<Vertex>.findMinDistance(): Vertex {
    var result = this.first()
    for (v in this) {
        if (v.distanceToSource < result.distanceToSource) {
            result = v
        }
    }
    return result
}

private fun Position.toVertex() = Vertex(this)