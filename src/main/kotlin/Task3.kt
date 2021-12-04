fun main() {
    val BITS = 12;
    val BIT_POSITIONS = 0 until BITS
    val zeroCounts = IntArray(BITS)
    val oneCounts = IntArray(BITS)
    val countMap = mapOf('0' to oneCounts, '1' to zeroCounts)

    val inputSequence = generateSequence(::readLine)
    inputSequence.forEach {
        for (position in BIT_POSITIONS) {
            countMap[it[position]]!![position]++
        }
    }

    var gammaRate = 0
    for (position in BIT_POSITIONS) {
        if (oneCounts[position] < zeroCounts[position]) {
            gammaRate = gammaRate or (1 shl BITS - 1 - position)
        }
    }
    val epsilonRate = ((1 shl BITS) - 1) and gammaRate.inv()
    println("res = ${gammaRate * epsilonRate}")
}