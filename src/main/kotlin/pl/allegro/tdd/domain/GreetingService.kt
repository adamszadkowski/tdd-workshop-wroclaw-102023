package pl.allegro.tdd.domain

import pl.allegro.tdd.domain.model.InvalidMessageException
import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun getGreeting(): String =
        message.get()

    fun updateGreeting(message: String): String =
        when (message.length) {
            !in 1..99 -> throw InvalidMessageException()
            else -> this.message.updateAndGet { message }
        }
}
