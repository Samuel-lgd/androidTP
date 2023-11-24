package com.example.kotlinkingdomking

sealed class Routes(val route: String) {
    //Route 1
    object SearchScreen : Routes("homeScreen")

    //Route 2 avec paramètre
    object DetailScreen : Routes("detailScreen/{data}") {
        fun addParam(position: Int) = "detailScreen/$position"
    }
}