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

/*
균형 요소 찾기
배열에서 요소가 삭제 되면 그 요소보다 인덱스가 큰 요소는 빈자리를 메우기 위해 하나씩 옆으로 움직이게 됩니다.



어떤 요소 x를 삭제 했을 때, 남은 배열의 짝수 인덱스 요소의 합과 홀수 인덱스 요소의 합이 같으면 x를 "균형 요소"라고 합니다.



배열이 주어졌을 때, 균형요소가 몇개인지 찾으세요.



예시

n=5

arr =  [5, 5, 2, 5, 8]



첫번째나 두번째 5를 삭제 했을 때, 남은 배열은 [5, 2, 5, 8]이 됩니다. 이때, 짝수 인덱스 요소의 합은 5 + 5 = 10, 홀수 인덱스 요소의 합은 2 + 8 = 10으로 둘이 같습니다.



주어진 배열에 이런 특성을 갖는 다른 요소가 없으므로, 이 배열엔 arr[0]과 arr[1] 2개의 균형요소가 있습니다.



함수 설명

아래 편집기에서 countBalancingElements 함수를 구현하세요.



countBalancingElements 함수는 다음 인자를 받습니다:

    int arr[n]: 크기 n의 정수 배열



반환값:

    int: 주어진 배열에 균형요소가 몇개인지 나타내는 정수



제약 조건:

1 ≤ n ≤ 2*10^5

1 ≤ arr[i] ≤ 10^9
 */
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