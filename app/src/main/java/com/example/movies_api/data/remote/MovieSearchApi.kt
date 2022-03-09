package com.example.movies_api.data.remote

import com.example.movies_api.data.model.MovieDTO
import com.example.movies_api.data.model.MoviesDTO
import com.example.movies_api.data.model.SeriesDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieSearchApi {

    @GET("/")
    suspend fun getMovie(@Query("t") movie:String,
                         @Query("apikey") apiKey:String): MovieDTO

    @GET("/")
    suspend fun getMovieDetails(@Query("i") movie:String,
                         @Query("apikey") apiKey:String): MovieDTO

    @GET("/")
    suspend fun getSeriesDetails(@Query("i") movie:String,
                                @Query("apikey") apiKey:String): SeriesDTO
}