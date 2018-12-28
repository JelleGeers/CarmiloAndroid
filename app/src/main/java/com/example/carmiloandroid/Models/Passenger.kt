package com.example.carmiloandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Passenger:Serializable
{
    @SerializedName("name")
    var name:String

    @SerializedName("street")
    var street: String

    @SerializedName("houseNr")
    var houseNr: String

    @SerializedName("village")
    var village: String

    @SerializedName("age")
    var age: String

    constructor(name:String, street:String, houseNr:String, village:String, age:String)
    {
        this.name=name
        this.street=street
        this.houseNr=houseNr
        this.village=village
        this.age=age
    }
}