package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path
import kotlin.math.abs

private fun main() {
    println("Day 7:")

    val crabs = Files.readAllLines(Path.of("./input/day-07.txt"))[0].split(",").map { it.toInt() }
    val solution = (crabs.minOf { it }..crabs.maxOf { it }).map { average ->
        crabs.map { abs(it - average) }.reduce { acc, i -> acc + i }
    }.minOf { it }
    val solution2 = (crabs.minOf { it }..crabs.maxOf { it }).map { average ->
        crabs.map { factorialButNotAsYouThink(abs(it - average)) }.reduce { acc, i -> acc + i }
    }.minOf { it }
    println("Solution part 1: $solution")
    println("Solution part 2: $solution2")
}

private fun factorialButNotAsYouThink(n: Int): Int = if (n > 0) n + factorialButNotAsYouThink(n - 1) else 0

fun day07() = main()
