package com.example.movies_api.hilt

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.movies_api.common.Contants
import com.example.movies_api.data.remote.MovieSearchApi
import com.example.movies_api.data.repository.GetMovieDetailsImpl
import com.example.movies_api.data.repository.GetMovieSearchImpl
import com.example.movies_api.domain.model.MovieTable
import com.example.movies_api.domain.repository.MovieDatabase
import com.example.movies_api.domain.repository.MovieDetailsRepository
import com.example.movies_api.domain.repository.MovieSearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import hilt_aggregated_deps._dagger_hilt_android_internal_modules_ApplicationContextModule
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMovieSearchAPI():MovieSearchApi{
        Log.d("HiltModules","provideMovieSearchAPI")
        return Retrofit.Builder().baseUrl(Contants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build().create(MovieSearchApi::class.java)

    }


    @Provides
    fun provideMovieSearchRepository(movieSearchAPI: MovieSearchApi):MovieSearchRepository{
        Log.d("HiltModules","provideMealSearchRepository")
        return GetMovieSearchImpl(movieSearchAPI)
    }

    @Provides
    fun provideMovieDetailsRepository(movieSearchAPI: MovieSearchApi):MovieDetailsRepository{
        Log.d("HiltModules","provideMovieDetailsRepository")
        return GetMovieDetailsImpl(movieSearchAPI)
    }



}