package me.sankar.movieslist.repository

import me.sankar.local.CachesLifeManager
import me.sankar.local.dao.MovieDao
import me.sankar.movieslist.core.Movie
import me.sankar.movieslist.core.MovieListType
import me.sankar.movieslist.remote.TheMoviesDBApiService
import me.sankar.movieslist.remote.map.Mapper
import me.sankar.movieslist.remote.model.MovieResult
import javax.inject.Inject

/**
 * Created by sankar on 2021-10-11.
 */
class MoviesListRepository @Inject constructor(
    private val movieDaoDataSource: MovieDao,
    private val mapper: Mapper<MovieResult, Movie>,
    private val cachesLifeManager: CachesLifeManager,
    private val apiDataSource: TheMoviesDBApiService
) {
    /**
     * Retrieves the list of movies by a given type.
     *
     * If the cache for the given list is still valid, it retrieves the
     * cached values from the local database, if the cache has expired, it
     * proceeds to retrieve it from the remote data source.
     *
     * @see MovieListType
     */
    suspend fun getMoviesListBy(type: MovieListType,page:Int) =
        if (page==1 && cachesLifeManager.listCacheIsValid(type)) {
            getMoviesListFromLocal()
        } else {
            generateMovieListCache(type, getMovieListFromRemote(page))
        }

    private suspend fun generateMovieListCache(type: MovieListType, movies: List<Movie>) = movies.apply {
        movieDaoDataSource.insertAll(this)
        cachesLifeManager.generateListCache(type)
    }

    /**
     * Retrieves the cached Movies.
     *
     * First, it proceeds to retrieve the index for the given move list and then
     * proceeds to read the cached movie data from the index movies Ids.
     */
    private suspend fun getMoviesListFromLocal(): List<Movie> =
        movieDaoDataSource.readAll()
    

    /**
     * Calls the appropiate endpoint for retrieving a list of movies.
     */
    private suspend fun getMovieListFromRemote(page:Int) =
       apiDataSource.getNowPlayingMovies(page).results?.map(mapper::map) ?: emptyList()


    /**
     * Retrieves the details of a Movie from the cache source.
     */
    suspend fun getMovieListResult(movieId: Int) = movieDaoDataSource.read(movieId).first()
}
