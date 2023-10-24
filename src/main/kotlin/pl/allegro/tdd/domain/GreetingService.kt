package pl.allegro.tdd.domain

import java.util.concurrent.atomic.AtomicReference

class GreetingService {
    private val message = AtomicReference("hello world")

    fun getGreeting(): String =
        message.get()

    fun updateGreeting(message: String): String =
        this.message.updateAndGet { message }
}
