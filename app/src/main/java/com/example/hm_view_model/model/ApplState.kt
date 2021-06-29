package com.example.hm_view_model.model

sealed class ApplState {
    data class Good( val weatherData: List<Weather>): ApplState()
    data class Bad(val error: Throwable): ApplState()
    object Middle: ApplState()
}
