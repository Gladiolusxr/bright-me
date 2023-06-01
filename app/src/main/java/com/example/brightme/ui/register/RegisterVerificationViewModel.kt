package com.example.brightme.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.RegisterVerificationResponse
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterVerificationViewModel(private val pref: UserPreference): ViewModel() {
    private val _userVerify = MutableLiveData<RegisterVerificationResponse>()
    val userVerify: LiveData<RegisterVerificationResponse> = _userVerify

    companion object {
        private const val TAG = "RegisterVerifViewModel"
    }

    fun verification(token: String, otp: String) {
        var client: Call<RegisterVerificationResponse> = ApiConfig().getApiService().regisVerify(token, otp)
        client.enqueue(object : Callback<RegisterVerificationResponse> {
            override fun onResponse(
                call: Call<RegisterVerificationResponse>,
                response: Response<RegisterVerificationResponse>,
            ) {
                if (response.isSuccessful) {
                    _userVerify.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<RegisterVerificationResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }
}