package me.sankar.movieslist.di

import android.app.Application
import com.codeheros.movieapp.BuildConfig
import me.sankar.local.di.LocalModule
import me.sankar.movieslist.remote.di.RemoteModule

/**
 * Created by sankar on 2021-10-11.
 */
object Injector {
    @Volatile
    private lateinit var app: Application

    /**
     * Initializes the singleton access for the [DaggerAppComponent] object.
     */
    fun init(application: Application) {
        app = application
    }

    /**
     * Singleton reference for the [AppComponent] class.
     */
    val component: AppComponent by lazy {
        DaggerAppComponent
            .builder()
            .application(app)
            .localModule(LocalModule(app))
            .remoteModule(
                RemoteModule(
                    BuildConfig.DEBUG,
                    BuildConfig.API_KEY,
                    BuildConfig.BASE_URL,
                    BuildConfig.POSTER_BASE_URL
                )
            )
            .build()
    }
}