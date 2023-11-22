package com.example.kotlinkingdomking.tp1

import okhttp3.OkHttpClient
import okhttp3.Request

fun main() {
    val v1:String = "toto"
    println(v1.uppercase())

    val v2:String = "toto"
    println(v2?.uppercase())

    val v3:String? = null
    println(v3?.uppercase())

    val v4 = v3 + v3
    println(v4)
    println(min(5, 10, 8))
    println(minExpression(5, 10, 8))
    println(pair(1))
    println(pair(2))
    myPrint("COUCOU")
    println(total(2,10,2));
//    println(scanNumber("nombre"))
//    println(scanText("texte"))

    val car = CarBean("Renault", "Clio")
    car.color = "red"
    println(car)
    val house = HouseBean("red", 10.0, 3.0)

    val randomIntBean = randomIntBean()
    val randomIntBean2 = randomIntBean(10)

}

fun min(a:Int, b:Int, c:Int) : Int {

    if (a < b && a < c){
        return a
    }
    else if (b < c && b < a) {
        return b
    }

    return c
}

fun minExpression(a:Int, b:Int, c:Int) : Int
        = if (a < b && a < c) a else if (b < c && b < a) b else c

fun pair(a:Int) : Boolean = if (a%2 == 0) true else false

fun myPrint(a:String) = println("#$a#")

fun total(croissants: Int = 0, sandwich: Int = 0, baguette: Int = 0) : Double
        = croissants * CROISSANT_PRICE + sandwich * SANDWICH_PRICE + baguette * BAGUETTE_PRICE

fun scanText(question: String) : String {
    println(question)
    val answer = readlnOrNull()
    return answer ?: "-"
}

fun scanNumber(question: String) : Int {
    println(question)
    val answer = readlnOrNull()
    return answer?.toIntOrNull() ?: -1

}