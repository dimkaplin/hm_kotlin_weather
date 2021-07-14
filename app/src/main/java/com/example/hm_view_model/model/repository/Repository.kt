package com.example.hm_view_model.model.repository

import com.example.hm_view_model.model.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromServerLesson(x: Double, y:Double): Weather
    fun getLocalRusWeather(): List<Weather>
    fun getLocalAnotherWeather(): List<Weather>
}