class Node {
    val children = HashMap<Char, Node>()
    var count = 0

    fun insert(bits: String) {
        count++
        val key = bits[0]
        val remainder = bits.substring(1)
        val child = children.computeIfAbsent(key) { Node() }
        if (remainder.isNotEmpty()) {
            child.insert(remainder)
        }
    }
}

fun main() {
    val BITS = 12;
    val BIT_POSITIONS = 0 until BITS

    val tree = Node()

    val inputSequence = generateSequence(::readLine)
    inputSequence.forEach(tree::insert)

    var o2gen = 0
    var co2gen = 0
    var o2node = tree;
    var co2node = tree;
    for (position in BIT_POSITIONS) {
        o2gen = o2gen shl 1
        co2gen = co2gen shl 1

        if ((o2node.children['1']?.count ?: 0) >= (o2node.children['0']?.count ?: 0)) {
            o2gen++
            o2node = o2node.children['1']!!
        } else {
            o2node = o2node.children['0']!!
        }

        if ((co2node.children['1']?.count ?: Int.MAX_VALUE) < (co2node.children['0']?.count ?: Int.MAX_VALUE)) {
            co2gen++
            co2node = co2node.children['1']!!
        } else {
            co2node = co2node.children['0']!!
        }
    }

    println("res = ${o2gen * co2gen}")
}