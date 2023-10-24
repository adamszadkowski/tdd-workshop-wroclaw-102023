package pl.allegro.tdd.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import pl.allegro.tdd.domain.GreetingService

@Configuration
class GreetingConfiguration {

    @Bean
    fun greetingService(): GreetingService =
        GreetingService()
}
