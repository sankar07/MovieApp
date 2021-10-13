package me.sankar.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codeheros.movieapp.BuildConfig
import me.sankar.local.dao.MovieDao
import me.sankar.local.dao.MovieDetailsDao
import me.sankar.local.typeconverters.IntListTypeConverter
import me.sankar.local.typeconverters.StringListTypeConverter
import me.sankar.movieslist.core.Movie
import me.sankar.movieslist.core.MovieDetails

/**
 * Created by sankar on 2021-10-11.
 */
@Database(
    entities = [Movie::class,MovieDetails::class],
    version = BuildConfig.VERSION_CODE,
    exportSchema = false
)
@TypeConverters(IntListTypeConverter::class, StringListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun provideMovieDao(): MovieDao
    abstract fun provideMovieDetailsDao(): MovieDetailsDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(ctx: Context) = INSTANCE ?: synchronized(this) {
            INSTANCE ?: createInstance(ctx).also { INSTANCE = it }
        }

        private fun createInstance(ctx: Context) =
            Room.databaseBuilder(
                ctx, AppDatabase::class.java,
                "${BuildConfig.APPLICATION_ID}.database"
            ).fallbackToDestructiveMigration().build()
    }
}