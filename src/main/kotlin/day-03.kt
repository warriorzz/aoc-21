package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 03:")

    val lines = Files.readAllLines(Path.of("./input/day-03.txt"))
    val map = mutableMapOf<Int, Int>()
    lines.map { it ->
        it.toCharArray().map { it.digitToInt() }
    }.forEach { ints ->
        ints.forEachIndexed { index, i ->
            map[index] = map[index]?.plus(i) ?: i
        }
    }
    val gamma = map.map { if (it.value.div(1000.0) >= 0.5)  "1" else "0" }.reduce { acc, s -> acc + s }.toInt(2)
    val epsilon = map.map { if (it.value.div(1000.0) <= 0.5)  "1" else "0" }.reduce { acc, s -> acc + s }.toInt(2)
    println("Solution part 1: " + (gamma * epsilon))

    val oxygen = lines.toMutableList().filterFromCommons()
    val co2 = lines.toMutableList().filterFromCommons(least = true)
    println("Solution part 2: " + oxygen.toInt(2) * co2.toInt(2))
}

fun MutableList<String>.filterFromCommons(index: Int = 0, least: Boolean = false) : String {
    val map = mutableMapOf<Int, Int>()
    map { it ->
        it.toCharArray().map { it.digitToInt() }
    }.forEach { ints ->
        ints.forEachIndexed { index, i ->
            map[index] = map[index]?.plus(i) ?: i
        }
    }
    val commons = map.map { if (it.value / this@filterFromCommons.size.toDouble() >= 0.5) "1" else "0" }.reduce { acc, s -> acc + s }
    val list = filter { if (!least) it[index] == commons[index] else it[index] != commons[index] }
    return if (list.size == 1) list.first() else list.toMutableList().filterFromCommons(index + 1, least)
}

fun day03() = main()
