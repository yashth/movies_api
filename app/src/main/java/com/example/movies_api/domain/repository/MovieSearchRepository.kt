package com.example.movies_api.domain.repository

import com.example.movies_api.data.model.MovieDTO
import com.example.movies_api.data.model.MoviesDTO

interface MovieSearchRepository {

suspend fun getMovie(movie:String,apiKey:String): MovieDTO
}