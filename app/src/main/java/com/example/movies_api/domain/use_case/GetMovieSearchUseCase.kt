package com.example.movies_api.domain.use_case

import android.util.Log
import com.example.movies_api.common.Resource
import com.example.movies_api.data.model.toDomainMovie
import com.example.movies_api.domain.model.Movie
import com.example.movies_api.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetMovieSearchUseCase @Inject constructor(private val repository: MovieSearchRepository){

    operator fun invoke(s:String,apiKey:String): Flow<Resource<Movie?>> = flow {

        try {

            emit(Resource.Loading())

            val response = repository.getMovie(s,apiKey)

            Log.d("GetMovieSearchUseCase","response: $response")

            val list = if (response==null) null else response.toDomainMovie()
            Log.d("GetMovieSearchUseCase","list: $list")

            emit(Resource.Success(data = list))



        }catch (e: HttpException){

            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))

        }catch (e: IOException){

            emit(Resource.Error(message = e.localizedMessage?:"Check your internet Connection"))

        }catch (e: Exception){

            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))

        }

    }



}