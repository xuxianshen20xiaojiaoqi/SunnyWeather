package com.example.sunnyweather.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceReaponse(val status:String,val place:List<Place>)

data class Place(val name: String,val localtion:Lacation,@SerializedName("formatted_address")val address:String)

data class Lacation(val lng:String,val lat:String)