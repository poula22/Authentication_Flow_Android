package com.codecraft.data.api

import com.codecraft.domain.retrofit.RetrofitManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

class RetrofitManagerImp @Inject constructor():RetrofitManager {
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient())
            .build()
    }

    override fun <T> buildWebService(webService: Class<T>): T = retrofit.create(webService)
}