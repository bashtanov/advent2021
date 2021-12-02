fun main() {
    val inputSequence = generateSequence(::readLine)
    val instructionSequence = inputSequence.map {
        val (direction, value) = it.split(' ')
        Instruction(direction, value.toInt())
    }
    var horizontalPosition = 0
    var depth = 0
    var aim = 0
    instructionSequence.forEach {
        when(it.direction) {
            "forward" -> {
                horizontalPosition += it.value
                depth += it.value * aim
            }
            "down" -> aim += it.value
            "up" -> aim -= it.value
        }
    }
    println("res = ${horizontalPosition * depth}")
}