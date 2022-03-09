package com.example.movies_api.domain.model

import androidx.room.Entity


data class Movie(val Title: String,
                 val Poster: String,
                 val Plot: String,
                 val Genre: String,
                 val imdbRating: String,
                 val Type:String,
                 val imdbID: String
                )


