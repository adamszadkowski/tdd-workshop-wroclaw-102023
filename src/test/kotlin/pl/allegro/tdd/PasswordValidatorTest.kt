package pl.allegro.tdd

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import strikt.api.expectThat
import strikt.assertions.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PasswordValidatorTest {

    @Test
    fun `all failures`() {
        expectThat(describePassword("")).containsExactlyInAnyOrder(
            "no digit",
            "too short",
            "no big letter",
        )
    }

    @Test
    fun `fail on too short`() {
        expectThat(describePassword("12345")).contains("too short")
    }

    @Test
    fun `pass on correct length`() {
        expectThat(describePassword("123456")).doesNotContain("too short")
    }

    @ParameterizedTest
    @MethodSource("getDigits")
    fun `pass on existing digit`(password: String) {
        expectThat(describePassword(password)).doesNotContain("no digit")
    }

    val digits = (0..9).map { it.toString() }

    @ParameterizedTest
    @MethodSource("getBigLetters")
    fun `pass on existing big letter`(password: String) {
        expectThat(describePassword(password)).doesNotContain("no big letter")
    }

    val bigLetters = ('A'..'Z').map { it.toString() }

    @Test
    fun `validate password`() {
        expectThat(validatePassword("AB1234")).isTrue()
        expectThat(validatePassword("")).isFalse()
    }

    private fun validatePassword(password: String): Boolean {
        return describePassword(password).isEmpty()
    }

    private fun describePassword(password: String): List<String> {
        val errors = mutableListOf<String>()

        if (!(password.length > 5)) errors += "too short"
        if (!password.contains("[0-9]".toRegex())) errors += "no digit"
        if (!password.contains("[A-Z]".toRegex())) errors += "no big letter"

        return errors
    }
}