package com.example.movies_api.presentation.movie_details

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies_api.common.Resource
import com.example.movies_api.domain.use_case.GetMovieDetailsUseCase
import com.example.movies_api.domain.use_case.GetMovieSearchUseCase
import com.example.movies_api.domain.use_case.GetSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase, private val getSeriesDetailsUseCase: GetSeriesDetailsUseCase)   : ViewModel() {

    //@Bindable
    var isMovies:Boolean = true

    private val _movieDetails = MutableStateFlow<MovieDetailsState>(MovieDetailsState())
    val movieDetails: StateFlow<MovieDetailsState> = _movieDetails


    private val _seriesDetails = MutableStateFlow<SeriesDetailsState>(SeriesDetailsState())
    val seriesDetails: StateFlow<SeriesDetailsState> = _seriesDetails


    fun getMovieDetails(id:String,apikey:String,type:String){

        getMovieDetailsUseCase(id,apikey,type).onEach {

            Log.d("MovieDetailsViewModel","id: $id")

            when(it) {
                is Resource.Loading -> {
                    _movieDetails.value = MovieDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _movieDetails.value = MovieDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _movieDetails.value = MovieDetailsState(data = it.data)

                }
            }
        }.launchIn(viewModelScope)

    }

    fun getSeriesDetails(id:String,apikey:String,type:String){

        getSeriesDetailsUseCase(id,apikey,type).onEach {

            Log.d("MovieDetailsViewModel","id: $id")

            when(it) {
                is Resource.Loading -> {
                    _seriesDetails.value = SeriesDetailsState(isLoading = true)
                }
                is Resource.Error -> {
                    _seriesDetails.value = SeriesDetailsState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _seriesDetails.value = SeriesDetailsState(data = it.data)

                }
            }
        }.launchIn(viewModelScope)

    }

    fun setSeriesMovies(type:String){
        isMovies = type=="movie"

        Log.d("MovieDetailsViewModel","setSeriesMovies: $isMovies")

    }

    fun getType():Boolean{
        Log.d("MovieDetailsViewModel","getType: $isMovies")
        return isMovies

    }




}