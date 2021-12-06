package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 06:")

    val lanternFishList = mutableMapOf<Long, Long>()
    Files.readAllLines(Path.of("./input/day-06.txt")).first().split(",").map { it.toLong() }.forEach {
        lanternFishList[it] = (lanternFishList[it] ?: 0).plus(1)
    }

    repeat(80) { _ ->
        val zero = lanternFishList[0] ?: 0
        (1L..8).forEach { int ->
            lanternFishList[int - 1] = lanternFishList[int] ?: 0
        }
        lanternFishList[6] = (lanternFishList[6] ?: 0) + zero
        lanternFishList[8] = zero
    }

    println("Solution part 1: ${lanternFishList.map { it.value }.reduce { acc, i -> acc + i }}")

    repeat(256 - 80) { _ ->
        val zero = lanternFishList[0] ?: 0
        (1L..8).forEach { int ->
            lanternFishList[int - 1] = lanternFishList[int] ?: 0
        }
        lanternFishList[6] = (lanternFishList[6] ?: 0) + zero
        lanternFishList[8] = zero
    }

    println("Solution part 2: ${lanternFishList.map { it.value }.reduce { acc, i -> acc + i }}")
}

fun day06() = main()
