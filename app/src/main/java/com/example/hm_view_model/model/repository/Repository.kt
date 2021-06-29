package com.example.hm_view_model.model.repository

import com.example.hm_view_model.model.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getLocalRusWeather(): List<Weather>
    fun getLocalAnotherWeather(): List<Weather>
}