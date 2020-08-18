package com.tsoft.getproductincategory.ui.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.tsoft.getproductincategory.R
import com.tsoft.getproductincategory.data.api.NetworkStatus
import com.tsoft.getproductincategory.data.api.request.Requests
import com.tsoft.getproductincategory.data.repository.MainRepository
import com.tsoft.getproductincategory.ui.main.pagination.ItemAdapter
import com.tsoft.getproductincategory.ui.main.viewmodel.MainViewModel
import com.tsoft.getproductincategory.ui.main.viewmodel.MainViewModelFactory
import com.tsoft.getproductincategory.util.*
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ir.androidexception.andexalertdialog.AndExAlertDialog
import ir.androidexception.andexalertdialog.Font
import kotlinx.android.synthetic.main.activity_main.*


/**
 * REQUEST EXAMPLE
 * http://mobiluygulama.1ticaret.com/rest1/auth/login/appmobile/USER_NAME?pass=PASSWORD
 * http://mobiluygulama.1ticaret.com/rest1/product/find/?token=EXAMPLE_TOKEN&category=212&pg=1&perpage=6
 *
 * */

class MainActivity : AppCompatActivity() {

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repository = MainRepository(Requests())
        val factory = MainViewModelFactory(repository)
        val viewModel = ViewModelProviders.of(this, factory).get(MainViewModel::class.java)
        SharedAdapter.setInstance(this)


        val single: Single<Boolean> = ReactiveNetwork.checkInternetConnectivity()

        single
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { isConnectedToInternet ->
                if (isConnectedToInternet) {
                    userLoginAndGetToken(viewModel)
                } else {
                    showNoInternetConnectionDialog()
                }
            }
    }


    private fun userLoginAndGetToken(viewModel: MainViewModel) {
        val user = ""      //todo change here
        val pass = ""         //todo change here
        val userLogin = viewModel.userLogin(user, pass)

        userLogin.observe(this, Observer { status ->
            val data = getSuccessData(status)?.response?.data

            SharedAdapter.put(AppConstants.USER_TOKEN, data?.get(0)?.token)
            getProductOfCategoryPaging(viewModel)

        })
    }

    private fun getProductOfCategoryPaging(viewModel: MainViewModel) {
        viewModel.ItemViewModel()
        val adapter = ItemAdapter()
        viewModel.itemPagedList?.observe(this, Observer {
            adapter.submitList(it)
        })
        recyclerView.adapter = adapter
        progressBar.hide()

    }

    fun <T> getSuccessData(status: NetworkStatus<T>): NetworkStatus.Success<T>? {
        when (status) {
            is NetworkStatus.Loading -> progressBar.show()
            is NetworkStatus.Failed -> {
                progressBar.hide()
                toast("FAIL" + status.message)
            }

            is NetworkStatus.Exception -> {
                progressBar.hide()
                toast(" EXCEPTION " + status.exception)

            }
            is NetworkStatus.Success -> {
                return NetworkStatus.Success(status.response)
            }
        }
        return null
    }

    //Showing the No internet connection Custom Dialog =)
    fun showNoInternetConnectionDialog() {
        progressBar.hide()
        AndExAlertDialog.Builder(this)
            .setTitle("CONNECTION")
            .setMessage("You havent got any connection")
            .setPositiveBtnText("OK")
            .OnPositiveClicked {
                finish()
            }
            .setCancelableOnTouchOutside(false)
            .setFont(Font.IRAN_SANS)
            .build()
    }


}