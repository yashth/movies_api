package com.example.movies_api.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieTable(
    var Title: String,
    var Poster: String,
    var Plot: String,
    var Genre: String,
    var imdbRating: String,
    var Type:String,
    @PrimaryKey
    var imdbID: String,
    var isFav:Boolean,
    var isHide:Boolean
) {


}