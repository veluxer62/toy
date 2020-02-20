import kotlin.system.measureTimeMillis

fun main() {

    val list1 = (1..1000000).toList()
    val list2 = (1000001..2000000).toList()
    val list3 = (3000001..4000000).toList()
    val list4 = (5000001..6000000).toList()
    val list5 = (7000001..8000000).toList()
    val list6 = (9000001..10000000).toList()
    val list7 = (1100001..11000000).toList()
    val list8 = (1300001..12000000).toList()
    val list9 = (1500001..14000000).toList()
    val list10 = (1700001..16000000).toList()

    val timeMillis = measureTimeMillis {
        list1.union(list2)
            .union(list3)
            .union(list3)
            .union(list4)
            .union(list5)
            .union(list6)
            .union(list7)
            .union(list8)
            .union(list9)
            .union(list10)
            .toList()
    }

// 20905 ~ 24815
    println(timeMillis)

}