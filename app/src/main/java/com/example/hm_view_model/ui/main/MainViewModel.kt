package com.example.hm_view_model.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hm_view_model.model.ApplState
import com.example.hm_view_model.model.repository.Repository
import com.example.hm_view_model.model.repository.RepositoryImpl

class MainViewModel : ViewModel() {
    private val liveData : MutableLiveData<ApplState> = MutableLiveData()
    private val repositoryImpl: Repository = RepositoryImpl()

    /*fun getData() :LiveData<Any> {
        setDataFromSource();
        return liveData;
    }*/

    fun getData() = liveData

    /*fun getWeather() = setDataFromSource()

    private fun setDataFromSource() {
        liveData.value = ApplState.Middle
        Thread{
            Thread.sleep(1000)
            //liveData.postValue(Any())
            //liveData.postValue(ApplState.Good(Weather()))
            liveData.postValue(ApplState.Good(repositoryImpl.getLocalWeather()))
            //liveData.value = Any()
        }.start()
    }*/

    fun getWeatherFromLocalRusSource() = getDataFromSource(true)

    fun getWeatherFromAnotherSource() = getDataFromSource(false)

    fun getRemoteSourceWeather() = getDataFromSource(true)

    private fun getDataFromSource(isLocal: Boolean) {
        liveData.value = ApplState.Middle
        Thread{
            Thread.sleep(1000)
            liveData.postValue(ApplState.Good(if (isLocal) repositoryImpl.getLocalRusWeather()
            else repositoryImpl.getLocalAnotherWeather()))
        }.start()
    }
}