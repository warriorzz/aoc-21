package com.github.warriorzz.aoc

import kotlin.system.exitProcess

private val days = listOf({ day01() }, { day02() }, { day03() }, { day04() }, { day05() }, { day06() })

fun main(args: Array<String>) {
    println("Which day would you like to calculate? (or \"all\" or \"exit\" to exit)")
    val line = readLine()
    when {
        line == "all" -> {
            days.forEach { it.invoke() }
        }
        line?.matches("^\\d{1,2}$".toRegex()) == true -> {
            days[line.toInt() - 1].invoke()
        }
        line == "exit" -> exitProcess(0)
        else -> {
            println("Could not determine what you want me todo! >.<")
            println("Please try again!")
            println()
        }
    }
    main(args)
}
