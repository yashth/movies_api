package com.example.movies_api.presentation.movie_search

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.Navigator
import androidx.room.Room
import com.example.movies_api.common.Resource
import com.example.movies_api.domain.model.MovieTable
import com.example.movies_api.domain.repository.MovieDatabase
import com.example.movies_api.domain.use_case.GetMovieSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Named
import kotlin.coroutines.coroutineContext

@HiltViewModel
class MovieSearchViewModel @Inject constructor(private val getMovieSearchUseCase: GetMovieSearchUseCase)  : ViewModel() {

    private val _movieSearchList = MutableStateFlow<MovieSearchState>(MovieSearchState())
    val movieSearchList: StateFlow<MovieSearchState> = _movieSearchList

    lateinit var db:MovieDatabase
    lateinit var favMovies : LiveData<List<MovieTable>>
    lateinit var hiddenMovies : LiveData<List<MovieTable>>

    fun setupDatabase(context: Context){

        db = MovieDatabase.getInstance(context)

        favMovies = db.dao()!!.getFavMovies()

        hiddenMovies = db.dao()!!.getHiddenMovies()

    }


    fun searchMovie(s:String,apiKey:String){

        getMovieSearchUseCase(s,apiKey).onEach {

            when(it){
                is Resource.Loading->{
                    Log.d("MovieSearchViewModel","searchMovie Resource.Loading")

                    _movieSearchList.value = MovieSearchState(isLoading = true)

                }

                is Resource.Error ->{
                    Log.d("MovieSearchViewModel","searchMovie Resource.Error")

                    _movieSearchList.value = MovieSearchState(error = it.message.toString())
                }

                is Resource.Success->{
                    Log.d("MovieSearchViewModel","searchMovie Resource.Success")
                    _movieSearchList.value = MovieSearchState(data = it.data)
                }
            }

        }.launchIn(viewModelScope)//Tell us to launch in viewModelScope

    }


    suspend fun insert(movieTable:MovieTable){
        db.dao()!!.insert(movieTable)
    }




}