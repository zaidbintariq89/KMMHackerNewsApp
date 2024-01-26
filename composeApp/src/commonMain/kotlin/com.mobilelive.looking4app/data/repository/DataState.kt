package com.mobilelive.looking4app.data.repository

/**
 * Data state for processing api response Loading, Success and Error
 */
sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: String) : DataState<Nothing>()
    data object Loading : DataState<Nothing>()
    data object Content : DataState<Nothing>()
}