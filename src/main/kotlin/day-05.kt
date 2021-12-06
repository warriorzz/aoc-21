package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 05:")

    val ventMap: VentMap = Files.readAllLines(Path.of("./input/day-05.txt")).map {
        val split = it.split(" ")
        Vent(
            split[0].split(",")[0].toInt(), split[0].split(",")[1].toInt(),
            split[2].split(",")[0].toInt(), split[2].split(",")[1].toInt()
        )
    }.toMutableList()

    val map = mutableMapOf<Pair<Int, Int>, Int>()
    ventMap.forEach {
        it.getAllLocations().forEach { location ->
            map[location.first to location.second] = map[location.first to location.second]?.plus(1) ?: 1
        }
    }
    val hotspots = map.map {
        if (it.value >= 2) 1 else 0
    }.reduce { acc, i -> acc + i }

    println("Solution part 1: $hotspots")

    val map2 = mutableMapOf<Pair<Int, Int>, Int>()
    ventMap.forEach {
        it.getAllLocations(true).forEach { location ->
            map2[location.first to location.second] = map2[location.first to location.second]?.plus(1) ?: 1
        }
    }
    val hotspots2 = map2.map {
        if (it.value >= 2) 1 else 0
    }.reduce { acc, i -> acc + i }

    println("Solution part 1: $hotspots2")
}

typealias VentMap = MutableList<Vent>

data class Vent(val x1: Int, val x2: Int, val y1: Int, val y2: Int) {
    fun getAllLocations(includeDiagonal: Boolean = false): List<Pair<Int, Int>> {
        val list = ArrayList<Pair<Int, Int>>()
        if (x1 == y1) {
            (if (x2 < y2) (x2..y2) else (y2..x2)).forEach { xy2 ->
                list.add(x1 to xy2)
            }
        } else if (x2 == y2) {
            (if (x1 < y1) (x1..y1) else (y1..x1)).forEach { xy1 ->
                list.add(xy1 to x2)
            }
        } else if (includeDiagonal) {
            if (x1 <= y1) {
                (x1..y1).forEachIndexed { index, i ->
                    list.add(i to if (x2 < y2) x2 + index else x2 - index)
                }
            } else {
                (y1..x1).forEachIndexed { index, i ->
                    list.add(i to if (y2 < x2) y2 + index else y2 - index)
                }
            }
        }
        return list
    }
}

fun day05() = main()
