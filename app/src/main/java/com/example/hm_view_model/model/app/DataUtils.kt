package com.example.hm_view_model.model.app

import com.example.hm_view_model.model.City
import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.model.database.HistoryEntity

class DataUtils {
    fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> {
        return entityList.map {
            Weather(City(it.city, 0.0, 0.0), it.temperature, 0, it.condition)
        }
    }

    fun convertWeatherToEntity(weather: Weather): HistoryEntity {
        return HistoryEntity(0, weather.city.city, weather.temperature, weather.condition)

    }
}