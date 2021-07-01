package com.example.lesson1

fun addNamToListFib(fibNam: Int): MutableList<Int> {
    val fibList: MutableList<Int> = mutableListOf<Int>()
    for(i in fibNam downTo 1){
        fibList.add(fibNam - i + fibNam - (i+1))
    }
    return fibList
}
fun getNamFromListFib(fibList: MutableList<Int>,position: Int): Int {
    var fibNam: Int =0
    fibList.let{
        fibNam =it.get(position)
    }
    return fibNam
}




