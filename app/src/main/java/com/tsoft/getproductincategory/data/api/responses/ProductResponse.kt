package com.tsoft.getproductincategory.data.api.responses

data class ProductResponse(
    val data: List<Data>?,
    val extra: List<Extra?>,
    val success: Boolean
) {
    data class Data(
        val id: String?,
        val cargo_free: String?,
        val title: String?,
        val price_sell: String?,
        val image: Image?
    ) {
        data class Image(
            val medium: String?
        )
    }

    data class Extra(
        val list: List<Lists>?
    ) {
        data class Lists(
            val children: List<Children?>?,
            val count: Int?,
            val id: String?,
            val name: String?,
            val row_number: String?,
            val selected: Int?,
            val url: String?
        ) {
            data class Children(
                val count: Int?,
                val id: String?,
                val name: String?,
                val selected: Int?,
                val url: String?
            )
        }
    }
}