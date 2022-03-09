package com.example.movies_api.presentation.movie_details

import com.example.movies_api.domain.model.MovieDetails

data class MovieDetailsState(    val data:MovieDetails? = null,
                                 val error:String = "",
                                 val isLoading:Boolean = false)
