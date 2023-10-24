package pl.allegro.tdd.elves

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class CaloriesCalculatorTest {
    private var content: String = ""
    private val calculator = CaloriesCalculator { content }

    @Test
    fun `no items`() {
        expectThat(calculator.findTopCalories()).isEqualTo(0)
    }

    @Nested
    inner class `single elf` {

        @Test
        fun `single item`() {
            content = "1"

            expectThat(calculator.findTopCalories()).isEqualTo(1)
        }

        @Test
        fun `two items`() {
            content = """
                1
                2
            """.trimIndent()

            expectThat(calculator.findTopCalories()).isEqualTo(3)
        }
    }

    @Nested
    inner class `two elves` {

        @Test
        fun `single item each`() {
            content = """
                1

                2
            """.trimIndent()

            expectThat(calculator.findTopCalories()).isEqualTo(2)
        }

        @Test
        fun `two items each`() {
            content = """
                1
                2

                3
                5
            """.trimIndent()

            expectThat(calculator.findTopCalories()).isEqualTo(8)
        }
    }
}
