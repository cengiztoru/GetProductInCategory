package com.tsoft.getproductincategory.data.api.responses

data class ProductResponse(
    val data: ArrayList<Data>?,
    val extra: List<Extra>,
    val message: List<Message>,
    val success: Boolean,
    val summary: Any
) {
    data class Data(
        val active_on_groups: String,
        val additional_field_1: String,
        val additional_field_2: String,
        val additional_field_3: String,
        val barcode: String,
        val brand: String,
        val brand_id: String,
        val brand_image: String,
        val brand_url: String,
        val can_add_cart_in_list: Boolean,
        val cargo_free: String,
        val cargo_free_member: String,
        val cargo_free_vendor: String,
        val category_id: String,
        val category_id_path: String,
        val category_ids: String,
        val category_name: String,
        val category_path: String,
        val category_url: String,
        val cbm: String,
        val combination_id: Int,
        val comment_rank: String,
        val currency: String,
        val currency_id: String,
        val delivery_time: Any,
        val discount_percent: Int,
        val discount_percent_raw: Double,
        val display_vat: String,
        val filter_category_id: String,
        val has_image: String,
        val has_variant: Int,
        val id: String,
        val image: Image,
        val image_count: Int,
        val image_list: List<Image>,
        val image_ratio: Double,
        val in_stock: Boolean,
        val is_active: String,
        val is_discount_active: Int,
        val is_display_discounted_active: String,
        val is_display_product: String,
        val is_money_order_active: Boolean,
        val is_new_product: String,
        val max_order_count: Int,
        val max_sale_count: String,
        val min_order_count: Int,
        val min_sale_count: String,
        val model: String,
        val model_id: String,
        val model_url: String,
        val money_order_percent: Int,
        val price_buy: String,
        val price_credit_cart: Double,
        val price_guest: String,
        val price_money_order: String,
        val price_not_discounted: Double,
        val price_raw: Double,
        val price_sell: String,
        val price_sell_default: Double,
        val price_visitor: Double,
        val product_code: String,
        val registration_date: Int,
        val s1: String,
        val s10: String,
        val s2: String,
        val s3: String,
        val s4: String,
        val s5: String,
        val s6: String,
        val s7: String,
        val s8: String,
        val s9: String,
        val short_description: String,
        val show_promotion: String,
        val show_vendor: String,
        val stock: String,
        val stock_increment: Int,
        val stock_unit: String,
        val stock_unit_id: String,
        val store_id: String,
        val sub_product_code: String,
        val supplier_product_code: String,
        val symbol_data1: String,
        val symbol_data10: String,
        val symbol_data2: String,
        val symbol_data3: String,
        val symbol_data4: String,
        val symbol_data5: String,
        val symbol_data6: String,
        val symbol_data7: String,
        val symbol_data8: String,
        val symbol_data9: String,
        val symbol_tag1: String,
        val symbol_tag10: String,
        val symbol_tag2: String,
        val symbol_tag3: String,
        val symbol_tag4: String,
        val symbol_tag5: String,
        val symbol_tag6: String,
        val symbol_tag7: String,
        val symbol_tag8: String,
        val symbol_tag9: String,
        val target_currency: String,
        val target_currency_original: String,
        val title: String,
        val url: String,
        val variant_code: String,
        val variant_feature1_list: List<Any>,
        val variant_feature1_selected: String,
        val variant_feature2_list: List<Any>,
        val variant_feature2_selected: String,
        val variant_id: Int,
        val variant_name: String,
        val variants: List<Variant>,
        val vat: Int
    ) {
        data class Image(
            val big: String,
            val id: String,
            val medium: String,
            val small: String,
            val title: String,
            val variant_ids: List<String>,
            val variant_type_id: String
        )

        data class Variant(
            val children: List<Children>,
            val color_code: String,
            val in_stock: Boolean,
            val type: String,
            val type_id: String,
            val type_image: String,
            val variant_id: String
        ) {
            data class Children(
                val barcode: String,
                val color_code: String,
                val in_stock: Boolean,
                val price: Double,
                val price_not_discounted: String,
                val stock: String,
                val type: String,
                val type_id: String,
                val type_image: String,
                val variant_id: String
            )
        }
    }

    data class Extra(
        val id: String,
        val list: List<Lists>,
        val name: String,
        val select_single: Int
    ) {
        data class Lists(
            val children: List<Children>,
            val count: Int,
            val id: String,
            val name: String,
            val row_number: String,
            val selected: Int,
            val url: String
        ) {
            data class Children(
                val count: Int,
                val id: String,
                val name: String,
                val selected: Int,
                val url: String
            )
        }
    }

    data class Message(
        val code: String,
        val errorField: List<Any>,
        val id: String,
        val index: Int,
        val subid: String,
        val text: List<String>,
        val type: Int
    )

    data class Summary(
        val totalRecordCount: Int
    )
}