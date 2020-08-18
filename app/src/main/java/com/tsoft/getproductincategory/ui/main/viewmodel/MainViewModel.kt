package com.tsoft.getproductincategory.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.paging.*
import com.tsoft.getproductincategory.data.api.NetworkStatus
import com.tsoft.getproductincategory.data.api.request.Requests
import com.tsoft.getproductincategory.data.api.responses.ProductResponse
import com.tsoft.getproductincategory.data.api.responses.UserLoginResponse
import com.tsoft.getproductincategory.data.repository.MainRepository
import com.tsoft.getproductincategory.ui.main.pagination.ItemDataSource
import com.tsoft.getproductincategory.ui.main.pagination.ItemDataSourceFactory
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun userLogin(user: String, pass: String): LiveData<NetworkStatus<UserLoginResponse>> {
        return liveData {
            emit(NetworkStatus.Loading())
            emit(repository.userLogin(user, pass))
        }

    }

    var itemPagedList: LiveData<PagedList<ProductResponse.Data>>? = null
    var liveDataSource: MutableLiveData<PageKeyedDataSource<Integer, ProductResponse.Data>>? = null

    fun ItemViewModel() {
        val itemDataSourceFactory = ItemDataSourceFactory(Requests())
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource()
        val config: PagedList.Config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ItemDataSource.Companion.PAGE_SIZE)
            .build()
        itemPagedList = LivePagedListBuilder(itemDataSourceFactory, config).build()
    }

}