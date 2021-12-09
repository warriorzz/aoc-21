package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path


private fun main() {
    println("Day 9:")

    val positions: List<List<Point>> = Files.readAllLines(Path.of("./input/day-09.txt"))
        .map { it.toCharArray().map { Point(it.digitToInt(), it.digitToInt() + 1) } }

    positions.forEachIndexed { y, points ->
        points.forEachIndexed point@{ x, point ->
            if (y != 0) if (positions[y - 1][x].height <= point.height) return@point
            if (y != positions.size - 1) if (positions[y + 1][x].height <= point.height) return@point
            if (x != 0) if (positions[y][x - 1].height <= point.height) return@point
            if (x != points.size - 1) if (positions[y][x + 1].height <= point.height) return@point
            point.isRisk = true
        }
    }

    val solution1 = positions.map { points ->
        points.map { if (it.isRisk) it.riskLevel else 0 }.reduce { acc, i -> acc + i }
    }.reduce { acc, i -> acc + i }
    println("Solution part 1: $solution1")

    val basins = mutableListOf<Int>()
    positions.forEachIndexed { y, points ->
        points.forEachIndexed { x, point ->
            if (point.isRisk) {
                basins.add(point.getBasinSize(positions, x, y))
            }
        }
    }

    val solution2 = basins.sortedDescending().first() * basins.sortedDescending()[1] * basins.sortedDescending()[2]
    println("Solution part 2: $solution2")
}

data class Point(val height: Int, val riskLevel: Int, var isRisk: Boolean = false) {
    private val alreadyUsed = mutableListOf<Pair<Int, Int>>()

    fun getBasinSize(positions: List<List<Point>>, x: Int, y: Int): Int {
        if (!isRisk) return 0
        return getSurroundingBasinSpots(x, y, positions.map { it.map { it.height } })
    }

    private fun getSurroundingBasinSpots(x: Int, y: Int, positions: List<List<Int>>): Int {
        alreadyUsed.add(x to y)
        var basinSpots = 0
        if (y != 0 && !alreadyUsed.contains(x to y - 1) && positions[y - 1][x] > positions[y][x] && positions[y - 1][x] != 9) basinSpots += getSurroundingBasinSpots(x, y - 1, positions)
        if (x != 0 && !alreadyUsed.contains(x - 1 to y) && positions[y][x - 1] > positions[y][x] && positions[y][x - 1] != 9) basinSpots += getSurroundingBasinSpots(x - 1, y, positions)
        if (y != positions.size - 1 && !alreadyUsed.contains(x to y + 1) && positions[y + 1][x] > positions[y][x] && positions[y + 1][x] != 9) basinSpots += getSurroundingBasinSpots(x, y + 1, positions)
        if (x != positions[0].size - 1 && !alreadyUsed.contains(x + 1 to y) && positions[y][x + 1] > positions[y][x] && positions[y][x + 1] != 9) basinSpots += getSurroundingBasinSpots(x + 1, y, positions)
        return basinSpots + 1
    }
}

fun day09() = main()
