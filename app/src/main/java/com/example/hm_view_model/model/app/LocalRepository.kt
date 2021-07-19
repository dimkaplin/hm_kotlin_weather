package com.example.hm_view_model.model.app

import com.example.hm_view_model.model.Weather

interface LocalRepository {
    fun getAllHistory(): List<Weather>
    fun saveEntity(weather: Weather)
}
