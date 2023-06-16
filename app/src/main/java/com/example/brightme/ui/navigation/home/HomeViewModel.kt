package com.example.brightme.ui.navigation.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brightme.data.response.ProductResponse
import com.example.brightme.data.response.ProductsItem
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Home Fragment"
    }
    val text: LiveData<String> = _text

    private val _allProduct = MutableLiveData<List<ProductsItem>>()
    val allProduct: LiveData<List<ProductsItem>> = _allProduct

    companion object {
        private const val TAG = "HomeViewModel"
    }

    fun getProducts() {
        var client: Call<ProductResponse> = ApiConfig().getApiService().getProduct()
        client.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>,
            ) {
                if (response.isSuccessful) {
                    _allProduct.postValue(response.body()?.data?.products)
                } else {
                    Log.e(TAG, "onFailure:${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}