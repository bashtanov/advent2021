class WindowedSequence<T, R>(
    val src: Sequence<T>,
    val size: Int,
    val step: Int = 1,
    val partialWindows: Boolean = false,
    val transform: (List<T>) -> R
): Sequence<R> {
    override fun iterator(): Iterator<R> = object: AbstractIterator<R>() {
        private val iterator = if (size > 0) src.iterator() else emptyList<T>().iterator()
        private var buffer = listOf<T>()

        override fun computeNext() {
            var currentSlice = buffer.drop(step)
            val toTake = size - currentSlice.size
            val taken = iterator.asSequence().take(size - currentSlice.size).toList()
            if (taken.size < toTake && !partialWindows) {
                done()
                return
            }
            currentSlice += taken
            buffer = currentSlice
            setNext(transform(buffer))
        }
    }
}

fun <T, R> Sequence<T>.windowSequence(
    size: Int,
    step: Int = 1,
    partialWindows: Boolean = false,
    transform: (List<T>) -> R
): Sequence<R> = WindowedSequence(this, size, step, partialWindows, transform)
