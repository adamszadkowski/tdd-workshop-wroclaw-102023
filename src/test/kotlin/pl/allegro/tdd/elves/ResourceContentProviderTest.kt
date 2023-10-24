package pl.allegro.tdd.elves

import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ResourceContentProviderTest {
    private val provider: ContentProvider = ResourceContentProvider("sample.txt")

    @Test
    fun `load file`() {
        expectThat( provider.provide()).isEqualTo(
            """
                this is sample content
                of sample.txt
            """.trimIndent()
        )
    }
}
