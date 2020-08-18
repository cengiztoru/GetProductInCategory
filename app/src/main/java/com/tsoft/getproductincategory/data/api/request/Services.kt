package com.tsoft.getproductincategory.data.api.request

import com.tsoft.getproductincategory.data.api.responses.ProductResponse
import com.tsoft.getproductincategory.data.api.responses.UserLoginResponse
import retrofit2.Response
import retrofit2.http.*


interface Services {

    //auth/login/appmobile/appmobile?pass=112233
    @POST("auth/login/appmobile/{user}")
    suspend fun userLogin(
        @Path("user") user: String,           //URL PARAMETER
        @Query("pass") pass: String            //POST PARAMETER
    ): Response<UserLoginResponse>

    @FormUrlEncoded
    @POST("product/find/")
    suspend fun getProducts(
        @FieldMap(encoded = true) fields: Map<String, String>
    ): Response<ProductResponse>

}