package part01.chapter01

private fun maxSum(n: Int): Int {
    val arr = IntArray(100) {
        100 - it
    }

    var sum: Int
    var max = 0

    for (i in 0 until n) {
        sum = 0

        for (j in i until n) {
            sum += arr[j]
            if (sum > max)
                max = sum
        }
    }

    return max
}

fun main() {
    val ret = maxSum(10)
    println("ret : $ret")
}