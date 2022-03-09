package com.example.movies_api.presentation.movie_search

import com.example.movies_api.domain.model.Movie

data class MovieSearchState(    val data:Movie? = null,
                                val error:String = "",
                                val isLoading:Boolean = false) {

}