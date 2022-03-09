package com.example.movies_api.data.model

import android.util.Log
import com.example.movies_api.domain.model.Movie
import com.example.movies_api.domain.model.MovieDetails

data class MovieDTO(
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
    val Language: String,
    val Country: String,
    val Awards: String,
    val Poster: String,
    val Ratings: List<Rating>,
    val Metascore: String,
    val imdbRating: String,
    val imdbVotes: String,
    val imdbID: String,
    val Type: String,
    val DVD: String,
    val BoxOffice: String,
    val Production: String,
    val Website: String,
    val Response: String
)


fun MovieDTO.toDomainMovie(): Movie {

    Log.d("MovieDTO","toDomainMovie")

    return Movie(
        Title = this.Title,
        Poster = this.Poster,
        Plot = this.Plot,
        Genre = this.Genre,
        imdbRating = this.imdbRating,
        Type = this.Type,
        imdbID = this.imdbID
    )

}

fun MovieDTO.toDomainMovieDetails(): MovieDetails {

    Log.d("MovieDTO","toDomainMovie")

    return MovieDetails( Title = this.Title,
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
    DVD= this.DVD,
    BoxOffice= this.BoxOffice,
    Production= this.Production,
    Website= this.Website,
    Response= this.Response)

}


