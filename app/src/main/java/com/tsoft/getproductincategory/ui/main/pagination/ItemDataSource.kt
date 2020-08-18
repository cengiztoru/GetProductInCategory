package com.tsoft.getproductincategory.ui.main.pagination

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.tsoft.getproductincategory.data.api.request.Services
import com.tsoft.getproductincategory.data.api.responses.ProductResponse
import com.tsoft.getproductincategory.util.AppConstants
import com.tsoft.getproductincategory.util.SharedAdapter
import kotlinx.coroutines.Dispatchers
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
    //PagedKeyDataSource
    //Positional Data Source
    //Item Keyed Data Source

    companion object {
        val PAGE_SIZE = 3
    }

    private val FIRST_PAGE = 1

    fun getDefaultFields(): HashMap<String, String> {
        val fields = HashMap<String, String>()
        fields.put("category", "212")
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

        GlobalScope.launch(Dispatchers.Main) {
            services.getProducts(fields).body()?.data.let {
                callback.onResult(it?.toList()!!, null, Integer(FIRST_PAGE + 1))
            }
        }
    }

    //load for previous page / data
    override fun loadBefore(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, ProductResponse.Data>
    ) {

//        val fields = getDefaultFields()
//        fields.put("pg", (params.key).toString())
//
//        GlobalScope.launch {
//            services.getProducts(fields).body()?.data.let {
//                val key = if (params.key > 1) params.key.toInt() - 1 else -1
//                callback.onResult(
//                       it?.toList()!!, Integer(key)
//                    )
//            }
//        }
    }

    //When user scrool to down of screen this method will work for fetch later page
    override fun loadAfter(
        params: LoadParams<Integer>,
        callback: LoadCallback<Integer, ProductResponse.Data>
    ) {

        val fields = getDefaultFields()
        fields.put("pg", (params.key).toString())

        GlobalScope.launch(Dispatchers.Main) {
            val response = services.getProducts(fields).body()

            val previousDataCount =
                6 * (params.key.toInt() - 1)          // Per page * (Next Page -1 )
            if (response?.extra?.get(0)?.list?.get(0)?.count.let { it!! > previousDataCount }) //has more data
            {
                callback.onResult(
                    response?.data?.toList()!!, params.key
                )
            }
        }


    }
}