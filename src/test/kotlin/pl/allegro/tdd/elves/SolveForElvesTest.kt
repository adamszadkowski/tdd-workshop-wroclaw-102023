package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.first
import strikt.assertions.isEqualTo

class SolveForElvesTest {

    @Test
    fun `solve part 1`() {
        val calculator = CaloriesCalculator(ResourceContentProvider("puzzle-input.txt"))

        expectThat(calculator.calculate()).first().isEqualTo(69177)
    }
}
