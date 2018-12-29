package com.example.carmiloandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

//data class Ride(val _id:String,
  //              val _name:String){
class Ride : Serializable{

    @SerializedName("_id")
    var _id:String

    @SerializedName("departure")
    var departure: String

    @SerializedName("date")
    var date: String

    @SerializedName("passengers")
    var passengers: ArrayList<Passenger>

    @SerializedName("address")
    var address: Address

    @SerializedName("maxPassengers")
    var maxPassengers: String


    constructor(_id:String,departure:String, date:String, passengers:ArrayList<Passenger>, address:Address, maxPassengers:String){
        this._id=_id
        this.departure=departure
        this.date=date
        this.passengers=passengers
        this.address=address
        this.maxPassengers=maxPassengers

    }


}