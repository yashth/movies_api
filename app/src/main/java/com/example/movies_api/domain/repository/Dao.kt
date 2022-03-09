package com.example.movies_api.domain.repository

import androidx.lifecycle.LiveData

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

import androidx.room.Update
import com.example.movies_api.domain.model.Movie
import com.example.movies_api.domain.model.MovieTable


@androidx.room.Dao
interface Dao {

    @Insert
    suspend fun insert(model: MovieTable)

    @Update
    suspend fun update(model: MovieTable)


    @Query("SELECT * FROM movie_table WHERE isFav = 1")
    fun getFavMovies(): LiveData<List<MovieTable>>

    @Query("SELECT * FROM movie_table WHERE isHide = 1")
    fun getHiddenMovies(): LiveData<List<MovieTable>>


}