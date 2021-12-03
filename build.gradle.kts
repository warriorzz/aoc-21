plugins {
    kotlin("jvm") version "1.5.31"
    application
}

repositories {
    mavenCentral()
}

group = "com.github.warriorzz.aoc"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.github.warriorzz.aoc.Day_03Kt")
}
