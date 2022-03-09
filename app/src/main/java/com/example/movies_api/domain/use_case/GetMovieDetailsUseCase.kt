package com.example.movies_api.domain.use_case

import android.util.Log
import com.example.movies_api.common.Resource
import com.example.movies_api.data.model.toDomainMovieDetails
import com.example.movies_api.domain.model.MovieDetails
import com.example.movies_api.domain.repository.MovieDetailsRepository
import com.example.movies_api.domain.repository.MovieSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MovieDetailsRepository){


    operator fun invoke(id:String,apiKey:String,type:String): Flow<Resource<MovieDetails>> = flow {
        Log.d("GetMovieDetailsUseCase","invoke id:$id, type:$type")
        try {

            emit(Resource.Loading())


                val response = repository.getMovieDetails(id,apiKey).toDomainMovieDetails()
                Log.d("GetMovieDetailsUseCase","invoke response:$response")
                Log.d("GetMovieDetailsUseCase","invoke emit(Resource.Success(data = response))")
                emit(Resource.Success(data = response))




        }catch (e: HttpException){

            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))

        }catch (e: IOException){

            emit(Resource.Error(message = e.localizedMessage?:"Check your internet Connection"))

        }catch (e: Exception){

            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))

        }

    }

}