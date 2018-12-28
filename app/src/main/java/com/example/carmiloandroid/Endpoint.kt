package com.example.carmiloandroid

import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.Models.User
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


/**
 * Interface om calls uit te voeren naar de databank
 */
interface Endpoint {

    @GET("/API/rides/")
    fun getRides(): Observable<ArrayList<User>>

}