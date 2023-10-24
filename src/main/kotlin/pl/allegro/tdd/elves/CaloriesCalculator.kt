package pl.allegro.tdd.elves

class CaloriesCalculator(
    private val contentProvider: ContentProvider,
) {
    fun calculate(): List<Int> =
        contentProvider.provide()
            .split("\n\n")
            .filter { it.isNotEmpty() }
            .map { singleElf ->
                singleElf
                    .split("\n")
                    .mapNotNull { it.toIntOrNull() }
                    .sumOf { it }
            }
            .sortedDescending()
}
