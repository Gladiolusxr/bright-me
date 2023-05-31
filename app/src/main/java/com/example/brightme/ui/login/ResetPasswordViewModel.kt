package com.example.brightme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.ResetPasswordResponse
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ResetPasswordViewModel(private val pref: UserPreference): ViewModel() {
    private val _userReset = MutableLiveData<ResetPasswordResponse>()
    val userReset: LiveData<ResetPasswordResponse> = _userReset

    companion object {
        private const val TAG = "RegisterViewModel"
    }

    fun resetPassword(token: String, newPassword: String, confirmPassword: String) {
        var client: Call<ResetPasswordResponse> = ApiConfig().getApiService().resetPassword(token, newPassword, confirmPassword)
        client.enqueue(object : Callback<ResetPasswordResponse> {
            override fun onResponse(
                call: Call<ResetPasswordResponse>,
                response: Response<ResetPasswordResponse>,
            ) {
                if (response.isSuccessful) {
                    _userReset.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResetPasswordResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }
}