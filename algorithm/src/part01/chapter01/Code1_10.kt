package part01.chapter01

private fun maxSum(n: Int): Int {
    val arr = IntArray(100) {
        100 - it
    }

    var sum: Int
    var max = 0

    for (i in 0 until n) {
        for (j in i until n) {
            sum = 0

            for (k in i until j + 1) {
                sum += arr[k]

                if (sum > max)
                    max = sum
            }
        }
    }

    return max
}

fun main() {
    val ret = maxSum(10)
    println("ret : $ret")
}