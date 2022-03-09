package com.example.movies_api.domain.repository

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movies_api.domain.model.Movie
import com.example.movies_api.domain.model.MovieTable
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase

import androidx.annotation.NonNull
import android.os.AsyncTask










@Database(entities = [MovieTable::class], version = 2)
abstract class MovieDatabase() : RoomDatabase() {

    companion object{
        private var INSTANCE: MovieDatabase? = null
        fun getInstance(context:Context): MovieDatabase{
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                    MovieDatabase::class.java,
                    "movie_database")
                    .fallbackToDestructiveMigration()
                    .build()
            }

            return INSTANCE as MovieDatabase
        }
    }

    abstract fun dao(): Dao?


}