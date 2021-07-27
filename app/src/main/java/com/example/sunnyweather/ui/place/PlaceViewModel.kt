package com.example.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sunnyweather.logic.model.Place
import androidx.lifecycle.Transformations
import com.example.sunnyweather.logic.Repository

class PlaceViewModel:ViewModel(){
    private val searchLiveData=MutableLiveData<String>()
    val placeList=ArrayList<Place>()

    val placeLiveData=Transformations.switchMap(searchLiveData){
        query->
        Repository.searchPlaces(query)
    }
    fun searchPlaces(query:String){
        searchLiveData.value=query
    }

    fun savePlace(place: Place)=Repository.savePlace(place)

    fun isPlaceSave()=Repository.isPlaceSaved()

    fun getSavePLace()=Repository.getSavePlace()
}