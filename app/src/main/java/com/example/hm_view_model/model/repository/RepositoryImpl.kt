package com.example.hm_view_model.model.repository

import com.example.hm_view_model.model.Weather

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getLocalRusWeather(): List<Weather> {
        return getLocalRusWeather()
    }

    override fun getLocalAnotherWeather(): List<Weather> {
        return getLocalAnotherWeather()
    }
}