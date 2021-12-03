package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 01:")

    val input = Files.readAllLines(Path.of("./input/day-01.txt"))
    var current = -1
    var increases = 0
    for (i in input) {
        if (i.toInt() > current) {
            increases++
        }
        current = i.toInt()
    }
    println("Solution part 1: " + (increases - 1))

    var increases2 = 0
    for (i in input.indices) {
        if (i == 0 || i + 2 >= input.size) continue
        if (input[i+2].toInt() > input[i-1].toInt()) {
            increases2++
        }
    }
    println("Solution part 2: $increases2")
}

fun day01() = main()