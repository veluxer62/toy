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

/*
괄호 완성하기
'('와 ')' 만으로 이루어진 문자열이 주어졌을 때, '('나 ')'을 추가해서 괄호의 짝을 맞추세요.



짝을 맞추기 위해 필요한 최소한의 문자 수를 구하세요.



예시:

s = '(()))'



s의 짝을 맞추려면 맨 앞에 '('를 붙여 "((()))"를 만들면 됩니다. 따라서 답은 1 입니다.



함수 설명:

아래 편집기에서 getMinOperations 함수를 구현하세요.



이 함수는 주어진 괄호 문자열의 짝을 맞추기 위해 필요한 최소한의 추가 문자 수를 반환해야 합니다.



getMinOperations 함수는 다음 인자를 받습니다:

    string s: 괄호 문자열



반환값:

    int: 괄호 짝을 맞추는데 필요한 최소한의 추가 문자 수



제약 조건:

1 ≤ s의 길이 ≤ 10^5
 */
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