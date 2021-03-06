package com.codeheros.movieapp.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codeheros.movieapp.model.MovieDetails

@Dao
interface MovieDetailsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createOrUpdate(movieDetails: MovieDetails)

    @Query("SELECT * FROM movie_details WHERE pk == :movieId")
    suspend fun read(movieId: Int): List<MovieDetails>
}