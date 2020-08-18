package com.tsoft.getproductincategory.ui.main.pagination

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.tsoft.getproductincategory.data.api.request.Services
import com.tsoft.getproductincategory.data.api.responses.ProductResponse


/**
 * Created by Cengiz TORU on 17/08/2020.
 * cengiz.toru@tsoft.com.tr
 */
class ItemDataSourceFactory(private val services: Services) :
    DataSource.Factory<Integer, ProductResponse.Data>() {

    private val itemLiveDataSource =
        MutableLiveData<PageKeyedDataSource<Integer, ProductResponse.Data>>()


    override fun create(): ItemDataSource {
        val itemDataSource = ItemDataSource(services)
        itemLiveDataSource.postValue(itemDataSource)
        return itemDataSource
    }

    fun getItemLiveDataSource(): MutableLiveData<PageKeyedDataSource<Integer, ProductResponse.Data>> {
        return itemLiveDataSource
    }

}