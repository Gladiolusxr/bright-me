package com.example.brightme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.ForgotPasswordResponse
import com.example.brightme.data.response.RegisterResponse
import com.example.brightme.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForgotPasswordViewModel(private val pref: UserPreference): ViewModel() {
    private val _userForgot = MutableLiveData<ForgotPasswordResponse>()
    val userForgot: LiveData<ForgotPasswordResponse> = _userForgot

    companion object {
        private const val TAG = "ForgotPasswordViewModel"
    }

    fun forgotPassword(email: String) {
        var client: Call<ForgotPasswordResponse> = ApiConfig().getApiService().forgotPassword(email)
        client.enqueue(object : Callback<ForgotPasswordResponse> {
            override fun onResponse(
                call: Call<ForgotPasswordResponse>,
                response: Response<ForgotPasswordResponse>,
            ) {
                if (response.isSuccessful) {
                    _userForgot.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<ForgotPasswordResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun saveUserToken(token: String) {
        viewModelScope.launch {
            pref.setToken(token)
        }
    }
}