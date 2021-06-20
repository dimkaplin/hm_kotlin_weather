package com.example.hm_view_model.model


data class Weather(
    val city: City = getDefCity(),
    val temperature: Int = 0,
    val feelsLike: Int = 0
)

fun getDefCity() = City("Moscow", 44.44,55.55)

