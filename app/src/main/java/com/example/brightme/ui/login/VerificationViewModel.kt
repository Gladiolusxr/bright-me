package com.example.brightme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.VerificationResponse
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VerificationViewModel(private val pref: UserPreference): ViewModel() {
    private val _userVerify = MutableLiveData<VerificationResponse>()
    val userVerify: LiveData<VerificationResponse> = _userVerify

    companion object {
        private const val TAG = "VerificationViewModel"
    }

    fun verification(token: String, otp: String) {
        var client: Call<VerificationResponse> = ApiConfig().getApiService().verify(token, otp)
        client.enqueue(object : Callback<VerificationResponse> {
            override fun onResponse(
                call: Call<VerificationResponse>,
                response: Response<VerificationResponse>,
            ) {
                if (response.isSuccessful) {
                    _userVerify.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<VerificationResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }
}