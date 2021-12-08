package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 8:")

    val lines = Files.readAllLines(Path.of("./input/day-08.txt"))

    val part1 = lines.map { line ->
        line.split(" | ")[1].split(" ").map { it.length == 2 || it.length == 4 || it.length == 7 || it.length == 3 }
            .map { if (it) 1 else 0 }.reduce { acc, i -> acc + i }
    }.reduce { acc, i -> acc + i }
    println("Solution part 1: $part1")
    val part2 = lines.map { determineNumberSum(it.split(" | ")[0].split(" "), it.split(" | ")[1].split(" ")) }
        .reduce { acc, i -> acc + i }
    println("Solution part 2: $part2")
}

fun determineNumberSum(strings: List<String>, output: List<String>): Int {
    val map = mutableMapOf<Char, Char>()
    val seven = strings.first { it.length == 3 }
    val one = strings.first { it.length == 2 }
    map[seven.first { !one.contains(it) }] = 'a'
    map[charList.filter { one.contains(it) }.first { char -> strings.count { it.contains(char) } == 9 }] = 'f'
    map[charList.filter { one.contains(it) }.first { char -> strings.count { it.contains(char) } == 8 }] = 'c'
    map[charList.filter { map[it] != 'f' && map[it] != 'c' && map[it] != 'a' }.first { char ->
        strings.filter { it.length !in (2..4) }.all { it.contains(char) }
    }] = 'g'
    map[charList.filter { map[it] != 'f' && map[it] != 'c' && map[it] != 'a' }.first { char ->
        strings.filter { it.length !in (2..4) }.count { it.contains(char) } == 5
    }] = 'b'
    map[charList.filter { map[it] != 'f' && map[it] != 'c' && map[it] != 'a' }.first { char ->
        strings.filter { it.length !in (2..4) }.count { it.contains(char) } == 4
    }] = 'e'
    map[charList.filter { map[it] != 'f' && map[it] != 'c' && map[it] != 'a' }.first { char ->
        strings.filter { it.length !in (2..4) }.count { it.contains(char) } == 6
    }] = 'd'
    val solution = output.map {
        numbers[it.map { char -> map[char]!!
        }.toCharArray().sortedArray().map { it.toString() }
            .reduce { acc, s -> acc + s }]!!
    }.reduce { acc, i -> acc + i }.toInt()
    charList.forEach { t -> println("$t " + map[t]) }
    println(solution)
    println()
    return solution
}

private val charList = listOf('a', 'b', 'c', 'd', 'e', 'f', 'g')
private val numbers =
    mapOf(
        "abcefg" to "0",
        "cf" to "1",
        "acdeg" to "2",
        "acdfg" to "3",
        "bcdf" to "4",
        "abdfg" to "5",
        "abdefg" to "6",
        "acf" to "7",
        "abcdefg" to "8",
        "abcdfg" to "9"
    )

fun day08() = main()
