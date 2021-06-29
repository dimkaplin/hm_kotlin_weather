package com.example.hm_view_model.model.repository

import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.model.getRussianCities
import com.example.hm_view_model.model.getWorldCities

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getLocalRusWeather(): List<Weather> {
        //return getLocalRusWeather()
        return getRussianCities()
    }

    override fun getLocalAnotherWeather(): List<Weather> {
        //return getLocalAnotherWeather()
        return getWorldCities()
    }
}