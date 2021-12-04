package com.github.warriorzz.aoc

import java.nio.file.Files
import java.nio.file.Path

private fun main() {
    println("Day 04:")

    val lines = Files.readAllLines(Path.of("./input/day-04.txt"))

    val pickedNumbers = lines[0].split(",").map { it.toInt() }

    val boards = lines.subList(fromIndex = 2, lines.size).windowed(6, 6).map {
        Board(it.subList(0, 5).map { string ->
            string.split(" ").filter { filter -> filter != "" }.toMutableList().map { cell ->
                Cell(cell.toInt())
            }.toMutableList()
        })
    }

    val finalScores = boards.map {
        it.getFinalScore(pickedNumbers)
    }
    println("Solution part 1: ${finalScores.minByOrNull { it.first }!!.second}")
    println("Solution part 2: ${finalScores.maxByOrNull { it.first }!!.second}")
}

data class Board(
    val content: List<MutableList<Cell>>
) {
    fun getFinalScore(markedNumbers: List<Int>): Pair<Int, Int> {
        markedNumbers.forEachIndexed numbers@{ index, int ->
            content.forEach {
                it.forEach { cell ->
                    if (cell.number == int) {
                        cell.isMarked = true
                        if (checkWon()) {
                            return index to int * content.map { cellList ->
                                cellList.map { cell -> if (cell.isMarked) 0 else cell.number }.reduce { acc, i -> acc + i }
                            }.reduce { acc, i -> acc + i }
                        }
                    }
                }
            }
        }
        return -1 to -1
    }

    private fun checkWon(): Boolean {
        var won = false
        (0 until 5).forEach {
            if (content[it][0].isMarked && content[it][1].isMarked && content[it][2].isMarked && content[it][3].isMarked && content[it][4].isMarked) won =
                true
            if (content[0][it].isMarked && content[1][it].isMarked && content[2][it].isMarked && content[3][it].isMarked && content[4][it].isMarked) won =
                true
        }
        return won
    }
}

data class Cell(val number: Int, var isMarked: Boolean = false)

fun day04() = main()
