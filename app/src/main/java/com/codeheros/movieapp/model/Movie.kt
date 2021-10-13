package me.sankar.movieslist.core

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by sankar on 2021-10-11.
 */
@Entity(tableName = "movies")
data class Movie(
    @ColumnInfo(name = "pk")
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    val rating: String = "",
    val title: String = "",
    val posterURL: String = "",
    val releaseDate: String = ""
)