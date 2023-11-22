package com.example.kotlinkingdomking.weatherAPI

data class WeatherBean (
    var name :String,
    var coord :CoordBean,
    var visibility :Int,
    var main:TempBean
)

data class CoordBean(var lon:Double, var lat:Double)

data class TempBean(var temp:Double, var feels_like:Double, var temp_min:Double, var temp_max:Double, var pressure:Int, var humidity:Int)

