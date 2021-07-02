package com.example.lesson1

fun addNamToListFib(fibNam: Int): MutableList<Int> {
    val numbers = IntArray(fibNam)
    val fibList: MutableList<Int> = mutableListOf()
    var result: Int

    for (i in 0 until fibNam) {
        numbers[i] = i+1
    }
    for (i in numbers.indices) {
        result = if (i>1){
            numbers[i-1]+numbers[i-2]
        }else {i}
        numbers[i] = result
        fibList.add(result)
    }
    return fibList
}





