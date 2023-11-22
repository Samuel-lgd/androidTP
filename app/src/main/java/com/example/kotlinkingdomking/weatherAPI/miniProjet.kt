package com.example.kotlinkingdomking.weatherAPI

fun main(){
    var city:String = "Toulouse"

    var weather:WeatherBean = WeatherAPI.loadWeather(city)
    println("Il fait ${weather.main.temp}°C à ${weather.name}")
}
