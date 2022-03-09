package com.example.movies_api.domain.repository

import com.example.movies_api.data.model.MovieDTO
import com.example.movies_api.data.model.SeriesDTO

interface MovieDetailsRepository {
    suspend fun getMovieDetails(id:String,apiKey:String): MovieDTO
    suspend fun getSeriesDetails(id:String,apiKey: String):SeriesDTO

}