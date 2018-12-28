package com.example.carmiloandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class User :Serializable{
    @SerializedName("name")
    var name:String

    @SerializedName("email")
    var email:String

    @SerializedName("rides")
    var rides:Ride

    constructor(name:String,email:String,rides:Ride){
        this.name=name
        this.email=email
        this.rides=rides

    }
}