package com.example.hm_view_model.model.repository

import com.example.hm_view_model.model.Weather

class RepositoryImpl: Repository {
    override fun getWeatherFromServer(): Weather {
        return Weather()
    }

    override fun getLocalWeather(): Weather {
        return Weather()
    }
}