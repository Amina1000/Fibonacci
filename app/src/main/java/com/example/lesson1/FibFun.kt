package com.example.lesson1

fun addNamToListFib(fibNam: Int): MutableList<Int> {
    val numbers: IntArray = IntArray(fibNam)
    val fibList: MutableList<Int> = mutableListOf()
    var result:Int =0;

    for (i in 0..fibNam-1) {
        numbers.set(i,i+1)
    }
    for (i in numbers.indices) {
        if (i>1){
            result = numbers[i-1]+numbers[i-2]
        }else {
            result=i
        }
        numbers[i] = result
        fibList.add(result)
    }
    return fibList
}





