fun main() {
    val inputSequence = generateSequence(::readLine)
    val instructionSequence = inputSequence.map {
        val (direction, value) = it.split(' ')
        Instruction(direction, value.toInt())
    }
    var horizontalPosition = 0
    var depth = 0
    instructionSequence.forEach {
        when(it.direction) {
            "forward" -> horizontalPosition += it.value
            "down" -> depth += it.value
            "up" -> depth -= it.value
        }
    }
    println("res = ${horizontalPosition * depth}")
}