import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.RuntimeException
import kotlin.test.assertFailsWith

internal class ImprovedPilotTest {


    val objectUnderTest = ImprovedPilot()

    @Test
    fun movesForward() {
        objectUnderTest.executeCommand("forward 3")

        assertEquals(0, objectUnderTest.depth)
        assertEquals(3, objectUnderTest.horizontalPosition)
    }

    @Test
    fun movesDown() {
        objectUnderTest.executeCommand("down 3")
        objectUnderTest.executeCommand("forward 1")

        assertEquals(3, objectUnderTest.depth)
        assertEquals(1, objectUnderTest.horizontalPosition)
    }

    @Test
    fun movesDownAndUp() {
        objectUnderTest.executeCommand("down 7")
        objectUnderTest.executeCommand("up 4")
        objectUnderTest.executeCommand("forward 1")

        assertEquals(3, objectUnderTest.depth)
        assertEquals(1, objectUnderTest.horizontalPosition)
    }

    @Test
    fun cannotHavePositiveAim() {
        assertFailsWith<RuntimeException> {
            objectUnderTest.executeCommand("up 4")
        }
    }

    @Test
    fun rejectsUnknownAction() {
        assertFailsWith<RuntimeException> {
            objectUnderTest.executeCommand("back 3")
        }
    }

    @Test
    fun rejectsGarbledCommand() {
        assertFailsWith<RuntimeException> {
            objectUnderTest.executeCommand(
                """forward 3
                back 3"""
            )
        }
    }

    @Test
    fun rejectsInvalidRange() {
        assertFailsWith<RuntimeException> {
            objectUnderTest.executeCommand("forward -2")
        }
    }

    @Test
    fun finalPositionOfCommands() {
        objectUnderTest.executeCommands(
            """
            forward 3 
            down 7
            up 4
            forward 4
        """
        )

        assertEquals((3 + 4) * (3 * 4), objectUnderTest.finalPosition)
    }

    @Test
    fun example() {
        objectUnderTest.executeCommands(
            """
            forward 5
            down 5
            forward 8
            up 3
            down 8
            forward 2
        """
        )

        assertEquals(900, objectUnderTest.finalPosition)
    }

}