package pl.allegro.tdd.elves

import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.*

class CaloriesCalculatorTest {
    private var content: String = ""
    private val calculator = CaloriesCalculator { content }

    @Test
    fun `no items`() {
        expectThat(calculator.calculate()).isEmpty()
    }

    @Nested
    inner class `single elf` {

        @Test
        fun `single item`() {
            content = "1"

            expectThat(calculator.calculate()).containsExactly(1)
        }

        @Test
        fun `two items`() {
            content = """
                1
                2
            """.trimIndent()

            expectThat(calculator.calculate()).containsExactly(3)
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

            expectThat(calculator.calculate()).containsExactlyInAnyOrder(1, 2)
        }

        @Test
        fun `two items each`() {
            content = """
                1
                2

                3
                5
            """.trimIndent()

            expectThat(calculator.calculate()).containsExactlyInAnyOrder(3, 8)
        }
    }

    @Nested
    inner class `calories ordering` {

        @Test
        fun `order descending by calories`() {
            content = """
                1
                2

                3
                5
            """.trimIndent()

            expectThat(calculator.calculate()).first().isEqualTo(8)
        }
    }
}
