package com.example.mechinetest.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {

    companion object {

        val baseURL = "https://api.themoviedb.org/3/"

        fun getRetroInstance(): Retrofit {
            val levelType: HttpLoggingInterceptor.Level = HttpLoggingInterceptor.Level.BODY
            val logging = HttpLoggingInterceptor()
            logging.setLevel(levelType)

            val okhttpClient = OkHttpClient.Builder()
            okhttpClient.addInterceptor(logging)
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okhttpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }
    }
}