package com.example.movies_api.data.repository

import com.example.movies_api.data.model.MovieDTO
import com.example.movies_api.data.model.SeriesDTO
import com.example.movies_api.data.remote.MovieSearchApi
import com.example.movies_api.domain.repository.MovieDetailsRepository

class GetMovieDetailsImpl(private val movieSearchApi: MovieSearchApi):MovieDetailsRepository {

    override suspend fun getMovieDetails(id: String, apiKey: String): MovieDTO {
        return movieSearchApi.getMovieDetails(id,apiKey)
    }

    override suspend fun getSeriesDetails(id: String, apiKey: String): SeriesDTO {
        return movieSearchApi.getSeriesDetails(id,apiKey)
    }

}