package com.example.kotlinkingdomking.tp1

fun main(){
    println(removeLetter("e", "Je suis une tomate"))
    println(reverseSentence("Je suis une tomate"))
    println(isPalindrome("kayak"))
    println(removeFirstSpaces("   Je suis une tomate    ") + "a")

    println(lower("EDEDEDED"))

    exo4()


}

fun removeLetter(letter: String, sentence: String) : String {
    var phrase:String = ""
    for (l in sentence){
        if (l.toString() != letter) {
            phrase += l
        }
    }
    return phrase
}

fun reverseSentence(sentence: String) : String {
    var result: String = ""
    for (l in sentence){
        result = l + result
    }
    return result
}

fun isPalindrome(sentence: String) : Boolean {
    return sentence == reverseSentence(sentence)
}

fun removeFirstSpaces(sentence: String) : String {
    var phrase:String = sentence
    for (l in sentence){
        if (l == ' '){
            phrase = sentence.substring(1)
        } else {
            return phrase
        }
    }
    return phrase
}

val lower: (String) -> String = { a -> a.lowercase() }
val heure: (Int) -> Int = { a -> a / 60}
val max2 = { a: Int, b: Int -> Math.max(a, b) }
val reverse: (String) -> String = { a -> a.reversed() }

//Sans les boucles mtn on fait avec des lambdas et collections


fun exo4() {
    val list = arrayListOf(
        PersonBean("toto", 16),
        PersonBean("Tata", 20),
        PersonBean("Toto", 8),
        PersonBean("Charles", 14)
    )

    println("Afficher la sous liste de personne ayant 10 et +")
    println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >= 10 }.joinToString("\n") { "-${it.name} : ${it.note}" })

    //TODO
    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    println(list.filter { it.name.lowercase() == "toto" }.count())

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.filter { it.name.lowercase() == "toto" && it.note >= 10 }.count())

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val moyenne = list.map { it.note }.average()
    println(list.filter { it.name.lowercase() == "toto" && it.note > moyenne }.count())

    println("\n\nAfficher la list triée par nom sans doublon")
    println(list.distinctBy { it.name.lowercase() }.sortedBy { it.name })

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    list.forEach { if (it.note >= 10) println(it.name) else println(".")  }

    println("\n\nAjouter un point à tous les Toto")
    list.forEach { if (it.name.lowercase() != "toto") println(it.name) else println(".")}

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il faut une ArrayList)")

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
}