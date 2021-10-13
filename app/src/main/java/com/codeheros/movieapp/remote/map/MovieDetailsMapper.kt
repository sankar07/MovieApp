package com.codeheros.movieapp.remote.map

import com.codeheros.movieapp.model.MovieDetails
import com.codeheros.movieapp.remote.model.MovieGenre
import com.codeheros.movieapp.remote.model.MovieSummary

class MovieDetailsMapper(private val baseImageURL: String) : Mapper<MovieSummary, MovieDetails> {
    var movieVideoKey = ""

    override fun map(remote: MovieSummary) = MovieDetails(
        remote.id ?: 0,
        remote.title ?: "",
        remote.runtime ?: 0,
        remote.votesCount ?: 0,
        extractGenresNames(remote.genres ?: emptyList()),
        "$baseImageURL${remote.poster}",
        remote.overview ?: "",
        movieVideoKey
    )

    private fun extractGenresNames(list: List<MovieGenre>) = list.map {
        it.name ?: ""
    }
}