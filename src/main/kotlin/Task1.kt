fun main() {
    val inputSequence = generateSequence(::readLine)
    val numbersSequence = inputSequence.map(String::toInt)
    val pairsSequence = numbersSequence.windowSequence(2, transform = { pair -> pair[1] > pair[0] })
    val result = pairsSequence.count { it }
    println("res = $result")
}