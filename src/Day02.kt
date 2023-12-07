fun main() {
    // test part 1
    val exampleInputPart1 = readInput("day02_example")
    check(Day02().solvePart1(exampleInputPart1) == 8)

    // test part 2
    val exampleInputPart2 = readInput("day02_example")
    check(Day02().solvePart2(exampleInputPart2) == 2286)

    val input = readInput("day02")
    Day02().solvePart1(input).println()
    Day02().solvePart2(input).println()
}

class Day02 {

    private val bagContents: Map<Cube, Int> = mapOf(
        Cube.RED to 12,
        Cube.GREEN to 13,
        Cube.BLUE to 14
    )

    fun solvePart1(input: List<String>): Int = input.sumOf { determineIfPossible(it) }

    fun solvePart2(input: List<String>): Int = input.sumOf { calculatePowerOfCubes(it) }

    private fun determineIfPossible(row: String): Int {
        val game = Game(row)
        for (set in game.sets) {
            for (cube in set.entries) {
                if (cube.value > bagContents[cube.key]!!) {
                    return 0
                }
            }
        }
        return game.id
    }

    private fun calculatePowerOfCubes(row: String): Int {
        val fewestNumberOfCubesMap = mutableMapOf(
            Cube.RED to 0,
            Cube.GREEN to 0,
            Cube.BLUE to 0
        )

        val game = Game(row)
        for (set in game.sets) {
            for (cube in set.entries) {
                if (fewestNumberOfCubesMap[cube.key]!! < cube.value) {
                    fewestNumberOfCubesMap[cube.key] = cube.value
                }
            }
        }
        return fewestNumberOfCubesMap[Cube.RED]!! * fewestNumberOfCubesMap[Cube.GREEN]!! * fewestNumberOfCubesMap[Cube.BLUE]!!
    }

}

enum class Cube {
    RED, GREEN, BLUE
}

class Game(row: String) {
    val id: Int
    val sets: List<Map<Cube, Int>>

    init {
        val (gameIdStr, setsStr) = row.split(":")
            .map {  it.trim() }

        id = gameIdStr.split(" ").last().toInt()

        sets = setsStr.split(";")
            .map { setStr ->
                setStr.split(",").associate { cubeStr ->
                    val (count, cube) = cubeStr.trim().split(" ")
                    Cube.valueOf(cube.uppercase()) to count.toInt()
                }
            }
    }
}