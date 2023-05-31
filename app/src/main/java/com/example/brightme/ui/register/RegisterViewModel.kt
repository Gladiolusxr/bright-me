package com.example.brightme.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.brightme.data.response.RegisterResponse
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel : ViewModel() {

    private val _userRegister = MutableLiveData<RegisterResponse>()
    val userRegister: LiveData<RegisterResponse> = _userRegister

    companion object {
        private const val TAG = "RegisterViewModel"
    }

    fun register(name: String, email: String, password: String) {
        var client: Call<RegisterResponse> = ApiConfig().getApiService().register(email, password, name)
        client.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                call: Call<RegisterResponse>,
                response: Response<RegisterResponse>,
            ) {
                if (response.isSuccessful) {
                    _userRegister.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}