package com.example.hm_view_model.model.app

import com.example.hm_view_model.model.Weather
import com.example.hm_view_model.model.database.HitoryDAO

class LocalRepositoryImpl(private val localDataSource: HitoryDAO) :
    LocalRepository {

    override fun getAllHistory(): List<Weather> {
        return DataUtils().convertHistoryEntityToWeather(localDataSource.all())
    }

    override fun saveEntity(weather: Weather) {
        localDataSource.insert(DataUtils().convertWeatherToEntity(weather))
    }
}
