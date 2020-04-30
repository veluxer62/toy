package com.kh.hackerrank

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

internal class Test3 {

    @Test
    fun test() {
        val arr = arrayOf(5, 5, 2, 5, 8)
        val actual = countBalancingElements(arr)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun test2() {
        val arr = arrayOf(2, 1, 6, 4)
        val actual = countBalancingElements(arr)
        Assertions.assertEquals(1, actual)
    }
}

fun countBalancingElements(arr: Array<Int>): Int {

    var cnt = 0

    for (i in arr.indices) {
        val cp = arr.filterIndexed { index, _ -> index != i }
                .foldIndexed(mutableMapOf<String, Int>()) { index, acc, i ->
                    if (index % 2 == 0) {
                        acc["odd"] = i + acc.getOrDefault("odd", 0)
                    } else {
                        acc["even"] = i + acc.getOrDefault("even", 0)
                    }

                    acc
                }

        if (cp["odd"] == cp["even"])
            cnt ++
    }

    return cnt
}