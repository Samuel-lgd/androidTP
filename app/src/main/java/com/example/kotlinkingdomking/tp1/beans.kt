package com.example.kotlinkingdomking.tp1

import java.util.Random

class CarBean(val brand: String, val model: String) {

    var color: String? = null

    override fun toString(): String {
        return "CarBean(brand='$brand', model='$model', color='$color')"
    }
}

class HouseBean(val surface: String, val color: String) {

    constructor(color: String, longueur: Double, largeur: Double) : this((longueur * largeur).toString(), color) {
        println("Construction d'une maison de $surface m2")
    }

}

class randomIntBean(max: Int) {

    private val random: Random = Random()

    init {
        println("Init")
        println(random.nextInt(max))
        println(random.nextInt(max))
        println(random.nextInt(max))
    }

    constructor() : this(100) {
        println("Constructor")
        println(random.nextInt(100))
        println(random.nextInt(100))
        println(random.nextInt(100))
        println(random.nextInt(100))
    }
}

data class PersonBean(var name:String, var note:Int)

const val LONG_TEXT = """Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500"""


data class PictureData(val url: String, val text: String, val longText: String, val id: Int)

//jeu de donnée
val pictureList = arrayListOf(PictureData("https://picsum.photos/200", "ABCD", LONG_TEXT, 0),
    PictureData("https://picsum.photos/201", "BCDE", LONG_TEXT, 1),
    PictureData("https://picsum.photos/202", "CDEF", LONG_TEXT, 2),
    PictureData("https://picsum.photos/203", "EFGH", LONG_TEXT, 3)
)