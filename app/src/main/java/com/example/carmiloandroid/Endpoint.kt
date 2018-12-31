package com.example.carmiloandroid

import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.Models.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


/**
 * Interface om calls uit te voeren naar de databank
 */
interface Endpoint {

    @GET("/API/rides/")
    fun getRides(): Observable<ArrayList<User>>

    @GET("/API/rides/{userID}/rides")
    fun getRidesUser(@Path("userID")userID:String): Observable<ArrayList<Ride>>


    @POST("/API/rides/{userID}/rides")
    @FormUrlEncoded
    fun addRideUser(@Path("userID") userID:String,@Field("departure")departure:String,@Field("date")date:String,@Field("street")street:String,@Field("houseNr")houseNr:String,@Field("zipcode")zipcode:String,@Field("maxPassengers")maxPassenger:String):Call<Ride>
}