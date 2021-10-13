package me.sankar.movieslist.domain

import androidx.paging.PagingSource
import me.sankar.movieslist.core.Movie
import me.sankar.movieslist.core.MovieListType
import me.sankar.movieslist.repository.MoviesListRepository
import javax.inject.Inject

/**
 * Retrieves a list of movies by a given type.
 *
 * Created by sankar on 2021-10-11.
 */
class GetMovieListUseCase @Inject constructor(private val repository: MoviesListRepository)
    :PagingSource<Int, Movie>(){

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val pageNumber = params.key ?: 1
            var nextPageNumber:Int
            val response = repository.getMoviesListBy(MovieListType.MOVIE_LIST_UPCOMING,pageNumber)
            nextPageNumber = pageNumber+(response.size / params.loadSize)
            LoadResult.Page(
                    response,
                if (pageNumber == 1) null else pageNumber -1,
                if (response.isEmpty() || response?.size<20) null else nextPageNumber
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}