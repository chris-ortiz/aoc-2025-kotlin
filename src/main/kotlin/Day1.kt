package de.porsche.sloffer

import java.nio.file.Files
import kotlin.io.path.Path
import kotlin.math.abs

fun main() {
    val dial = Dial()
    dial.parse("src/main/resources/day1.txt")

    println("Counted zeros: ${dial.timesEndingAtZero}")
    println("Counted zeros (with rotation): ${dial.zerosDuringRotation}")
}

interface Dial {
    val timesEndingAtZero: Int
    val zerosDuringRotation: Int
    val position: Int

    fun turnRight(steps: Int): Dial

    fun turnLeft(steps: Int): Dial

    fun parse(fileName: String) {
        val lines = Files.readAllLines(Path(fileName))
        println(position)

        lines.forEach {
            val direction = it.first()
            val steps = it.drop(1).toInt()

            if (direction == 'L') {
                turnLeft(steps)
            } else {
                turnRight(steps)
            }
            println("Current position: $position")
        }
    }

    companion object {
        operator fun invoke(initialPos: Int = 50): Dial {
            return DialImpl(initialPos)
        }
    }
}

class DialImpl(initialPos: Int) : Dial {
    private val dialSize = 100
    override var timesEndingAtZero = 0
        private set

    override var zerosDuringRotation = 0
        private set

    override var position: Int = initialPos
        private set(value) {
            if (value == 0) {
                timesEndingAtZero++
            }
            field = value
        }

    override fun turnRight(steps: Int): Dial {
        zerosDuringRotation += (steps + position) / dialSize

        val newPosition = position + steps

        position = if (newPosition > dialSize - 1) {
            newPosition % dialSize
        } else {
            newPosition
        }

        return this
    }

    override fun turnLeft(steps: Int): Dial {
        val newPosition = position - steps

        zerosDuringRotation += if (position != 0) {
            (if (newPosition <= 0) 1 else 0) + abs(newPosition) / dialSize
        } else {
            abs(newPosition) / dialSize
        }

        position = if (newPosition < 0) {
            val rest = abs(newPosition) % dialSize

            if (rest > 0) {
                dialSize - rest
            } else {
                0
            }
        } else {
            newPosition
        }

        return this
    }
}


