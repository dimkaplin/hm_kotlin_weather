package com.example.hm_view_model.model.repository

import com.example.hm_view_model.WeatherLoader
import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.model.getRussianCities
import com.example.hm_view_model.model.getWorldCities
import com.example.hm_view_model.model.rest.WeatherRepo
import com.example.hm_view_model.ui.main.MainFragment

class RepositoryImpl: Repository {
    /*override fun getWeatherFromServer(): Weather {
        return Weather()
    }*/
    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromServerLesson(x: Double, y:Double): Weather {
        //val dto = WeatherLoader.loadWeather(x,y)
        //TODO
        val dto = WeatherRepo.api.getWethe(x,y).body()//execute выдает ошибку, непонятно почему
        return Weather(
            temperature = dto?.fact?.temp,
            feelsLike = dto?.fact?.feels_like,
            condition = dto?.fact?.condition

        )
    }


    /*override fun getLocalRusWeather(): List<Weather> {
        return getRussianCities()
    }*/
    override fun getLocalRusWeather() = getRussianCities()

    /*override fun getLocalAnotherWeather(): List<Weather> {
        return getWorldCities()
    }*/
    override fun getLocalAnotherWeather()= getWorldCities()

}