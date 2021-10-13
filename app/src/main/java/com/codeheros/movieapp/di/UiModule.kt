package me.sankar.movieslist.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import me.sankar.movieslist.NavHostActivity
import com.codeheros.movieapp.movies.MovieListFragment

@Module
abstract class UiModule {
    @ContributesAndroidInjector
    abstract fun contributeNavHostActivity(): NavHostActivity

    @ContributesAndroidInjector
    abstract fun contributeMovieListFragment(): MovieListFragment


}