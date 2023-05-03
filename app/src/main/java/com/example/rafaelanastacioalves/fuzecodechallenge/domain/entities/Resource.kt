package com.example.rafaelanastacioalves.moby.domain.entities

open class Resource<T>(
    val status: Status = Status.LOADING,
    val data: T? = null,
    var message: String) {

    companion object Factory {

        fun <T> success(successData: T?): Resource<T>  {
            return Resource(Status.SUCCESS,successData, "No error");
        }

        fun <T> error(status: Status, data: T?, msg: String): Resource<T> {
            return Resource(status, data, msg)
        }

        fun <T> loading(): Resource<T> {
            return Resource(Status.LOADING, null, "No error")
        }
    }

    enum class Status { SUCCESS, INTERNAL_SERVER_ERROR ,GENERIC_ERROR, LOADING}
}