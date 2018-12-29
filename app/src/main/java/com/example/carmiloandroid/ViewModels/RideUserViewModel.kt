package com.example.carmiloandroid.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.carmiloandroid.Adapters.RideAdapter
import com.example.carmiloandroid.Adapters.RideUserAdapter
import com.example.carmiloandroid.Endpoint
import com.example.carmiloandroid.Models.Ride
import com.example.carmiloandroid.Models.User
import com.example.carmiloandroid.RetrofitClientInstance
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class RideUserViewModel() : ViewModel() {
    private var rideUserAdapter : RideUserAdapter
    private val ridesUserList = MutableLiveData <ArrayList<Ride>>()
    private var subscription : Disposable
    val isLoading = MutableLiveData<Boolean>()

    init {
        ridesUserList.value = ArrayList()
        rideUserAdapter = RideUserAdapter(ridesUserList.value!!)
        //subscriptions initializen
        val service = RetrofitClientInstance().getRetrofitInstance()!!.create(Endpoint::class.java)
        subscription = service.getRidesUser("5c250c3118904c2674144202")
            //we tell it to fetch the data on background by
            .subscribeOn(Schedulers.io())
            //we like the fetched data to be displayed on the MainTread (UI)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onRetrieveCarmiloStart() }
            .doOnTerminate { onRetrieveCarmiloFinish() }
            .subscribe(
                { result -> onRetrieveCarmiloSucces(result) },
                { error -> onRetrieveCarmiloError(error) }
            )

    }

    fun getRideUserAdapter(): RideUserAdapter {
        return rideUserAdapter
    }

    //Indien call mislukt
    private fun onRetrieveCarmiloError(error: Throwable) {
        Logger.e(error.message!!)
    }

    //Indien call lukt
    private fun onRetrieveCarmiloSucces(result: List<Ride>) {
        rideUserAdapter.setRides(result)

    }

    private fun onRetrieveCarmiloFinish() {
        Logger.i("Finished retrieving Carmilo info")
        isLoading.value = false
    }

    private fun onRetrieveCarmiloStart() {
        Logger.i("Started retrieving Carmilo info")
        isLoading.value = true
    }
}