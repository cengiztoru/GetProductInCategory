package com.tsoft.getproductincategory.data.repository

import com.tsoft.getproductincategory.data.api.NetworkStatus
import com.tsoft.getproductincategory.data.api.request.Services
import com.tsoft.getproductincategory.data.api.responses.UserLoginResponse
import retrofit2.Response

class MainRepository(private val api: Services) {

    suspend fun userLogin(user: String, pass: String): NetworkStatus<UserLoginResponse> {
        return checkResponse(api.userLogin(user, pass))
    }


    fun <T> checkResponse(response: Response<T>): NetworkStatus<T> {
        val state = if (response.isSuccessful && response.body() != null) {
            NetworkStatus.Success(response.body())
        } else {
            NetworkStatus.Failed("SOMETHING WENT WRONG ${response.errorBody()}")
        }
        return state
    }

//    suspend fun getProducts(fields: HashMap<String, String>): NetworkStatus<ProductResponse> {
//        fields.put("token", SharedAdapter.getString(AppConstants.USER_TOKEN, ""))
//        return checkResponse(api.getProducts(fields))
//    }
}