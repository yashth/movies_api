package com.example.movies_api.data.repository

import com.example.movies_api.data.model.MovieDTO
import com.example.movies_api.data.model.MoviesDTO
import com.example.movies_api.data.remote.MovieSearchApi
import com.example.movies_api.domain.repository.MovieSearchRepository

class GetMovieSearchImpl(private val movieSearchApi: MovieSearchApi):MovieSearchRepository {
    override suspend fun getMovie(movie: String, apiKey: String): MovieDTO {
        return movieSearchApi.getMovie(movie,apiKey)
    }


}