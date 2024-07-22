package com.xpayback.machinetest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {
    var retrofitService: ApiService? = null

    fun getInstance() : ApiService {
        if (retrofitService == null) {

            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()


            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitService = retrofit.create(ApiService::class.java)
        }
        return retrofitService!!
    }
}