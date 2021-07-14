package com.example.hm_view_model.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hm_view_model.model.ApplState
import com.example.hm_view_model.model.repository.Repository
import com.example.hm_view_model.model.repository.RepositoryImpl
import kotlinx.android.extensions.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.anko.custom.async


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

    fun loadDataLesson(x: Double, y:Double) {
        liveData.value = ApplState.Middle
        //launch{
          //val job = async(Dispatchers.IO){repositoryImpl.getWeatherFromServer(x,y)}
        //}
        Thread{
            Thread.sleep(1000)
            liveData.postValue(ApplState.GoodObject(repositoryImpl.getWeatherFromServerLesson(x,y)))
        }.start()
    }

    private fun getDataFromSource(isLocal: Boolean) {
        liveData.value = ApplState.Middle
        Thread{
            Thread.sleep(1000)
            liveData.postValue(ApplState.Good(if (isLocal) repositoryImpl.getLocalRusWeather()
            else repositoryImpl.getLocalAnotherWeather()))
        }.start()
    }
}