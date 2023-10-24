package pl.allegro.tdd.domain

import org.junit.jupiter.api.Nested
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import pl.allegro.tdd.domain.model.InvalidMessageException
import strikt.api.expectCatching
import strikt.api.expectThat
import strikt.api.expectThrows
import strikt.assertions.isEqualTo
import strikt.assertions.isSuccess

class GreetingServiceTest {
    private val service = GreetingService()

    @Nested
    inner class `validate message` {

        @ParameterizedTest
        @ValueSource(ints = [0, 100, 101])
        fun `fail on incorrect size`(length: Int) {
            expectThrows<InvalidMessageException> {
                service.updateGreeting("a".repeat(length))
            }
            expectThat(service.getGreeting()).isEqualTo("hello world")
        }

        @ParameterizedTest
        @ValueSource(ints = [1, 2, 98, 99])
        fun `pass on correct length`(length: Int) {
            expectCatching {
                service.updateGreeting("a".repeat(length))
            }.isSuccess()
        }
    }
}
