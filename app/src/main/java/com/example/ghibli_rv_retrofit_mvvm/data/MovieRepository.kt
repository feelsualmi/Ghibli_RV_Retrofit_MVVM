package com.example.ghibli_rv_retrofit_mvvm.data

class MovieRepository(private val retrofitService: MovieApi){
    fun getAllMovies() = retrofitService.getMovies()
}
