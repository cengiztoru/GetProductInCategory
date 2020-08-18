package com.tsoft.getproductincategory.data.api.responses

data class UserLoginResponse(
    val data: List<Data>?,
    val message: List<Message>,
    val success: Boolean,
    val summary: String
) {
    data class Data(
        val expirationTime: String,
        val limited: String,
        val secretKey: String,
        val tableId: String,
        val token: String,
        val type: String,
        val userId: String,
        val username: String
    )

    data class Message(
        val code: String,
        val errorField: List<Any>,
        val id: String,
        val index: Int,
        val subid: String,
        val text: List<String>,
        val type: Int
    )
}