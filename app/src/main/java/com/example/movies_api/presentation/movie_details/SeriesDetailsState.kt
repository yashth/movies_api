package com.example.movies_api.presentation.movie_details

import com.example.movies_api.domain.model.MovieDetails
import com.example.movies_api.domain.model.SeriesDetails

data class SeriesDetailsState(val data: SeriesDetails? = null,
                              val error:String = "",
                              val isLoading:Boolean = false)
