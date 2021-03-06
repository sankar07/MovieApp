package com.codeheros.movieapp.model

/**
 * Data class that transport a given payload from the Repository to the
 * rest of the layers. This class also helps to report the state of a
 * repository call.
 *
 * Created by sankar on 2021-10-11.
 */
class Result<T> private constructor(
    val payload: T? = null,
    val error: String? = null,
    val status: Status
) {
    companion object {
        fun <T> success(payload: T) = Result(payload, null, Status.OK)
        fun <T> loading() = Result<T>(null, null, Status.LOADING)
        fun <T> error(message: String?) = Result<T>(null, message, Status.ERROR)
    }

    enum class Status { OK, ERROR, LOADING }
}