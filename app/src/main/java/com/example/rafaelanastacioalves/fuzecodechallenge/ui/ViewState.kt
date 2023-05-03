package com.example.rafaelanastacioalves.fuzecodechallenge.ui

sealed class ViewState<out T> {
    object Loading : ViewState<Nothing>()
    data class Success<T>(val viewDate: T ) : ViewState<T>()
    data class Error(val message: String) : ViewState<Nothing>()
}