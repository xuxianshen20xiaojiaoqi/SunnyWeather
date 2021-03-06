package com.example.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.model.Location
import androidx.lifecycle.Transformations
import com.example.sunnyweather.logic.Repository


class WeatherViewModel:ViewModel() {
    private val locationLiveData=MutableLiveData<Location>()

    var locationLng=""

    var locationLat=""

    var placeName=""

    val weatherLiveData=Transformations.switchMap(locationLiveData){
        location->
        Repository.refreshWeather(location.lng,location.lat)
    }

    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value=Location(lng,lat)
    }
}