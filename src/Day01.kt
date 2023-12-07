fun main() {
    // test part 1
    val exampleInputPart1 = readInput("day01_part1_example")
    check(Day01().solvePart1(exampleInputPart1) == 142)

    // test part 2
    val exampleInputPart2 = readInput("day01_part2_example")
    check(Day01().solvePart2(exampleInputPart2) == 281)

    val input = readInput("day01")
    Day01().solvePart1(input).println()
    Day01().solvePart2(input).println()
}

class Day01 {

    private val numberMap: Map<String, String> = mapOf(
            "one" to "1",
            "two" to "2",
            "three" to "3",
            "four" to "4",
            "five" to "5",
            "six" to "6",
            "seven" to "7",
            "eight" to "8",
            "nine" to "9"
    )

    fun solvePart1(input: List<String>): Int = input.sumOf { calculateCalibrationValue(it) }
    
    fun solvePart2(input: List<String>): Int = input.sumOf { calculateCalibrationValuePart2(it) }

    private fun calculateCalibrationValue(row: String): Int {
        val firstDigit = row.first { it.isDigit() }
        val lastDigit = row.last { it.isDigit() }
        return "$firstDigit$lastDigit".toInt()
    }

    private fun calculateCalibrationValuePart2(row: String): Int {
        val firstDigit = findFirstDigit(row)
        val lastDigit = findLastDigit(row)
        return "$firstDigit$lastDigit".toInt()
    }

    private fun findFirstDigit(row: String): Int {
        var currentIndex = 0
        while (currentIndex < row.length) {
            for ((word, number) in numberMap) {
                // If the current index is a digit, then return digit
                if (row.indexOf(number, startIndex = currentIndex) == currentIndex)
                    return number.toInt()
                // If the current index is the start of a match
                if (row.indexOf(word, startIndex = currentIndex) == currentIndex) {
                    return number.toInt()
                }
            }
            currentIndex++
        }
        return 0
    }

    private fun findLastDigit(row: String): Int {
        var currentIndex = row.length - 1
        while (currentIndex >= 0) {
            for ((word, number) in numberMap) {
                // If the current index is a digit, then return digit
                if (row.indexOf(number, startIndex = currentIndex) == currentIndex)
                    return number.toInt()
                // If the current index is the start of a match
                if (row.indexOf(word, startIndex = currentIndex) == currentIndex) {
                    // return respective digit
                    return number.toInt()
                }
            }
            currentIndex--
        }
        return 0
    }
}
