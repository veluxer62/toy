package com.kh.hackerrank

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.math.abs

internal class Test {

    @Test
    fun test() {
        val given = "()))"
        val actual = getMin(given)
        Assertions.assertEquals(2, actual)
    }

    @Test
    fun test2() {
        val given = "()()"
        val actual = getMin(given)
        Assertions.assertEquals(0, actual)
    }

    @Test
    fun test3() {
        val given = "(()))"
        val actual = getMin(given)
        Assertions.assertEquals(1, actual)
    }

    @Test
    fun test4() {
        val given = "()(())))((()"
        val actual = getMin(given)
        Assertions.assertEquals(4, actual)
    }

    @Test
    fun test5() {
        val given = "()))))))))))))))))))))))()()))()))))))))()))))))()))()))))(()))))))))))))()))))))(()))))))))()()))))))))))))()))))(())()))))))(()))))()))))))()))()())))())))))))))))()))())(()()())()()())))))()))))())()))()))))))))))))))()())))()))))()))))))()))())()))())))(()))()))))))))())))())))(())()))))()((()))))))((((()())())())(())))))())())))))))())))))()(()))))()))))())))))()())())()))()))))))))()))))))))))()))))())))))(((()))))()))((())))())))))))())))()()())())))))())))())())))))(())())))))))())))()()))))))))))))(())())())))((()))))))(())))()())))()))))(())))(())))))))))))))(())))(())()))))(()))())())))))))()())(()(())())))))))))))))))))))))))((()())))())))())))((()())))()))())()))))())()())))))))))))(()))))))))))))))()))))))()))))))))))))))))(()(()))(()))()))))))()))()()))))))))))()))())()))))())))()()()))()))))(())))))))))))))()()))))(())))()))))))()))()())()))())()())())))()()(()())))))()())))))))())))())))(())))())))))))()))))))))()((()(())))))))))(())))())))())))))))))()())))()))))))))("
        val actual = getMin(given)
        Assertions.assertEquals(564, actual)
    }
}

fun getMin(s: String): Int {

    val stack = Stack<Char>()
    var cnt = 0;

    s.forEach {
        if (it == '(') {
            stack.push(it)
        }
        else {
            if (!stack.empty()) {
                stack.pop()
            } else {
                cnt++
            }
        }
    }

    cnt += stack.size

    return cnt
}