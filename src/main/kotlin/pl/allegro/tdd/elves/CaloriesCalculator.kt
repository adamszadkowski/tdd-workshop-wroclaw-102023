package pl.allegro.tdd.elves

class CaloriesCalculator(
    private val contentProvider: ContentProvider,
) {
    fun findTopCalories(): Int =
        contentProvider.provide()
            .split("\n\n")
            .filter { it.isNotEmpty() }
            .maxOfOrNull { singleElf ->
                singleElf
                    .split("\n")
                    .mapNotNull { it.toIntOrNull() }
                    .sumOf { it }
            } ?: 0

}
