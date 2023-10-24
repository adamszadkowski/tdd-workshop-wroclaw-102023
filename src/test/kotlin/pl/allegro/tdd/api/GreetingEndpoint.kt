package pl.allegro.tdd.api

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.allegro.tdd.domain.GreetingService
import pl.allegro.tdd.domain.model.InvalidMessageException

@RestController
@RequestMapping("/greeting")
class GreetingEndpoint(
    private val greetingService: GreetingService,
) {

    @GetMapping
    fun getGreeting(): GreetingResponse = GreetingResponse(greetingService.getGreeting())

    @PutMapping
    fun updateGreeting(@RequestBody request: UpdateRequest): GreetingResponse =
        GreetingResponse(greetingService.updateGreeting(request.message))

    @ExceptionHandler(InvalidMessageException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun failToUpdate() {
    }
}

data class GreetingResponse(
    val message: String,
)

data class UpdateRequest(
    val message: String,
)
