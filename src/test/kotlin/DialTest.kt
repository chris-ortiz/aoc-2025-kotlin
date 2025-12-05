import de.porsche.sloffer.Dial
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class DialTest {
    val dial = Dial()

    @Test
    fun left() {
        dial.turnLeft(30)
        assertEquals(20, dial.position)

        dial.turnLeft(20)
        assertEquals(0, dial.position)

        dial.turnLeft(1)
        assertEquals(99, dial.position)

        assertEquals(1, dial.timesEndingAtZero)
    }

    @Test
    fun right() {
        dial.turnRight(49)
        assertEquals(99, dial.position)

        dial.turnRight(1)
        assertEquals(0, dial.position)
        assertEquals(1, dial.timesEndingAtZero)
    }

    @Test
    fun sampleInput() {
        dial.parse("src/main/resources/day1.sample.txt")
        assertEquals(3, dial.timesEndingAtZero)
        assertEquals(6, dial.zerosDuringRotation)
    }

    @Test
    fun sampleInputProgrammatically() {
        dial.turnLeft(68)
        assertEquals(1, dial.zerosDuringRotation)
        dial.turnLeft(30)
        assertEquals(1, dial.zerosDuringRotation)
        dial.turnRight(48)
        assertEquals(2, dial.zerosDuringRotation)
        dial.turnLeft(5)
        assertEquals(2, dial.zerosDuringRotation)
    }

    @Test
    fun zerosDuringRotation() {
        assertEquals(
            1,
            Dial(82)
                .turnRight(50)
                .zerosDuringRotation
        )

        assertEquals(
            8,
            Dial(82)
                .turnRight(750)
                .zerosDuringRotation
        )

        assertEquals(
            7,
            Dial(82)
                .turnLeft(750)
                .zerosDuringRotation
        )
    }

    @Test
    fun testCasesInText() {
        assertEquals(
            19, Dial(11)
                .turnRight(8)
                .position
        )
        assertEquals(
            99, Dial(0)
                .turnLeft(1)
                .position
        )

        assertEquals(
            0, Dial(99)
                .turnRight(1)
                .position
        )

        assertEquals(
            95, Dial(5)
                .turnLeft(10)
                .position
        )
    }

    @Test
    fun susCases() {
        assertEquals(
            62, Dial(91)
                .turnLeft(829)
                .position
        )

        assertEquals(
            0, Dial(3)
                .turnLeft(403)
                .position
        )
    }
}