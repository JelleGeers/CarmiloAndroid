package com.example.carmiloandroid


import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Connectie met de databank -> link.
 * Via retrofit
 */
class RetrofitClientInstance {
    private var retrofit: Retrofit? = null
   private val BASE_URL = "http://10.0.2.2:3000"
    fun getRetrofitInstance(): Retrofit? {
        if (retrofit == null) {
            val gson = GsonBuilder()
                    .create()
            retrofit = retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.addConverterFactory(StringConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
        }
        return retrofit
    }
}