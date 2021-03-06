package com.example.movies_api.data.model

import android.util.Log
import com.example.movies_api.domain.model.MovieDetails
import com.example.movies_api.domain.model.SeriesDetails

data class SeriesDTO(
    val Title: String,
    val Year: String,
    val Rated: String,
    val Released: String,
    val Runtime: String,
    val Genre: String,
    val Director: String,
    val Writer: String,
    val Actors: String,
    val Plot: String,
    val Language:String,
    val Country: String,
    val Awards: String,
    val Poster: String,
    val Ratings: List<RatingX>,
    val Metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    val Type: String,
    val totalSeasons: String,
    val Response: String
)


fun SeriesDTO.toDomainSeriesDetails(): SeriesDetails {

    Log.d("MovieDTO","toDomainMovie")

    return SeriesDetails( Title = this.Title,
        Year= this.Year,
        Rated= this.Rated,
        Released= this.Released,
        Runtime= this.Runtime,
        Genre= this.Genre,
        Director= this.Director,
        Writer= this.Writer,
        Actors= this.Actors,
        Plot= this.Plot,
        Language= this.Language,
        Country= this.Country,
        Awards= this.Awards,
        Poster= this.Poster,
        Ratings= this.Ratings,
        Metascore= this.Metascore,
        imdbRating= this.imdbRating,
        imdbVotes= this.imdbVotes,
        imdbID= this.imdbID,
        Type= this.Type,
        totalSeasons = this.totalSeasons,
        Response= this.Response)

}