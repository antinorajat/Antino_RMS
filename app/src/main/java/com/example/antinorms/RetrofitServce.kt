package com.example.antinorms

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitService {
    val BASE_URL = "https://rms-be.antino.ca/"

    var interceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build();


    private var network =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    fun networkCall(): ApiInterface {
        return network.create(ApiInterface::class.java)
    }
}