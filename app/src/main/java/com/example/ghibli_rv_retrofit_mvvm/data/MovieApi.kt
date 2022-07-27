package com.example.ghibli_rv_retrofit_mvvm.data

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MovieApi {

    @GET("/films")
    fun getMovies():  Call<List<Movie>>

    companion object {

        var BASE_URL = "https://ghibliapi.herokuapp.com"

        fun create() : MovieApi {

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(MovieApi::class.java)

        }
    }
}