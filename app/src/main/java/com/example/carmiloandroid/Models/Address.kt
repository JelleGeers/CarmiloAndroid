package com.example.carmiloandroid.Models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Address:Serializable{

    @SerializedName("street")
    var street:String

    @SerializedName("houseNr")
    var houseNr:String

    @SerializedName("zipcode")
    var zipcode:String

    constructor(street:String, houseNr:String, zipcode:String){
        this.street=street
        this.houseNr=houseNr
        this.zipcode=zipcode
    }
}