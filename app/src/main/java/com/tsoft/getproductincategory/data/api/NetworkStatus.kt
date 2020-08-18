package com.tsoft.getproductincategory.data.api

sealed class NetworkStatus<out CT> {
    data class Success<out CT>(val response: CT? = null) : NetworkStatus<CT>()
    data class Failed(val message: String? = null) : NetworkStatus<Nothing>()
    data class Exception(val exception: kotlin.Exception? = null) : NetworkStatus<Nothing>()
    data class Loading(val nothing: Nothing? = null) : NetworkStatus<Nothing>()
}
