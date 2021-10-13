package me.sankar.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.sankar.movieslist.core.Movie

/**
 * Created by sankar on 2021-10-11.
 */
@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(movies: List<Movie>)

    @Query("SELECT * FROM movies WHERE pk == :movieId")
    suspend fun read(movieId: Int): List<Movie>

    @Query("DELETE FROM movies")
    suspend fun deleteAll()
    @Query("SELECT * FROM movies")
    suspend fun readAll(): List<Movie>
}