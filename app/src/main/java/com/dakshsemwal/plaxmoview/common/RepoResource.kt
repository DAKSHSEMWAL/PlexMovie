package com.dakshsemwal.plaxmoview.common

data class RepoResource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T): RepoResource<T> =
            RepoResource(status = Status.SUCCESS, data = data, message = null)

        fun <T> error(data: T?, message: String): RepoResource<T> =
            RepoResource(status = Status.ERROR, data = data, message = message)

        fun <T> loading(data: T?): RepoResource<T> =
            RepoResource(status = Status.LOADING, data = data, message = null)
    }
}