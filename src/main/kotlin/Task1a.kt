fun main() {
    val inputSequence = generateSequence(::readLine)
    val numbersSequence = inputSequence.map(String::toInt)
    val tripletAvgSequence = numbersSequence.windowSequence(3, transform = Iterable<Int>::sum)
    val pairsSequence = tripletAvgSequence.windowSequence(2, transform = { pair -> pair[1] > pair[0] })
    val result = pairsSequence.count { it }
    println("res = $result")
}