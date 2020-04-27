package part01.chapter01.section01

import java.util.*

fun main() {
    val data = IntArray(1000)

    for (i in 0..999) {
        data[i] = i + 1
    }

    print("찾을 값을 입력하세요 => ")
    val input = Scanner(System.`in`).nextInt()

    var min = 0
    var max = 1000
    var i = (max + min) / 2

    while (min <= max) {
        if (input == data[i]) {
            println("찾으려고 하는 값은 배열 data의 ${i}번째에 있군요")
            break
        }
        else if (input < data[i]) {
            max = (max + min) / 2 - 1
        }
        else {
            min = (max + min) / 2 + 1
        }

        i = (max + min) / 2
    }
}