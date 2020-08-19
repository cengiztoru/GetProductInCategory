package com.tsoft.getproductincategory.ui.main.pagination

import androidx.paging.PageKeyedDataSource
import com.tsoft.getproductincategory.data.api.request.Services
import com.tsoft.getproductincategory.data.api.responses.ProductResponse
import com.tsoft.getproductincategory.util.AppConstants
import com.tsoft.getproductincategory.util.SharedAdapter
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created by Cengiz TORU on 17/08/2020.
 * cengiz.toru@tsoft.com.tr
 */

//this class responsible to fetching data from remote to local storage
//todo change to paging source
class ItemDataSource(private val services: Services) :
    PageKeyedDataSource<Integer, ProductResponse.Data>() {

    private val FIRST_PAGE = 1

    fun getDefaultFields(): HashMap<String, String> {
        val fields = HashMap<String, String>()
        fields.put("category", "212")         //For getting more data, you can comment here
        fields.put("perPage", "6")
        fields.put("token", SharedAdapter.getString(AppConstants.USER_TOKEN, ""))
        return fields
    }

    override fun loadInitial(
        params: LoadInitialParams<Integer>,
        callback: LoadInitialCallback<Integer, ProductResponse.Data>
    ) {
        val fields = getDefaultFields()
        fields.put("pg", (FIRST_PAGE).toString())

        GlobalScope.launch {
            services.getProducts(fields).body()?.data.let {
                if (it != null) {
                    callback.onResult(it, null, Integer(FIRST_PAGE + 1))
                }
            }
        }
    }

    //load for previous page / data
    override fun loadBefore(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, ProductResponse.Data>
    ) {
        //not need
    }

    //When user scroll to down of screen this method will work for fetch next page
    override fun loadAfter(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, ProductResponse.Data>
    ) {

        val fields = getDefaultFields()
        fields.put("pg", (params.key).toString())

        GlobalScope.launch {
            val response = services.getProducts(fields).body()

//            response?.data.let {
            if (response?.data != null) {
                callback.onResult(response.data, params.key)
            }

//            }

        }


    }
}