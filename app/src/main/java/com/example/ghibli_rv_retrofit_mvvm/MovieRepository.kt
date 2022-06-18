package com.example.ghibli_rv_retrofit_mvvm

class MovieRepository(private val retrofitService: MovieApi){
    fun getAllMovies() = retrofitService.getMovies()
}
