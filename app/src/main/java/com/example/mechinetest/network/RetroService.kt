package com.example.mechinetest.network

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetroService {


    @GET("discover/movie?")
    suspend fun getDataFromAPI(
        @Query("api_key") api_key: String,
        @Query("language") language: String,
        @Query("sort_by") sort_by: String,
        @Query("include_adult") include_adult: Boolean,
        @Query("include_video") include_video: Boolean,
        @Query("page") page: Int,
        @Query("with_watch_monetization_types") with_watch_monetization_types: String
    ):MovieResponseModel


    @GET("movie/{id}?")
    suspend fun getMovieDetails(@Path("id") id:Long,
        @Query("api_key") api_key: String,
        @Query("language") language: String
    ):MovieDetailsResponse



}


