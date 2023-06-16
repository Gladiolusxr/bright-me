package com.example.brightme.ui.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.Data
import com.example.brightme.data.response.GoogleResponse
import com.example.brightme.data.response.LoginResponse
import com.example.brightme.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (private val pref: UserPreference) : ViewModel() {

    private val _userLogin = MutableLiveData<Data>()
    val userLogin: LiveData<Data> = _userLogin

    private val _userGoogle = MutableLiveData<GoogleResponse>()
    val userGoogle: LiveData<GoogleResponse> = _userGoogle

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun login(email: String, password: String) {
        var client: Call<LoginResponse> = ApiConfig().getApiService().login(email, password)
        client.enqueue(object : Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        _userLogin.value = responseBody.data
                    }
                } else {
                    Log.e(TAG, "onFailure y:${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun googleLogin(credential: String){
        var client: Call<GoogleResponse> = ApiConfig().getApiService().googleLogin(credential)
        client.enqueue(object : Callback<GoogleResponse> {
            override fun onResponse(
                call: Call<GoogleResponse>,
                response: Response<GoogleResponse>,
            ) {
                if (response.isSuccessful) {
                    _userGoogle.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure :${response.message()}")
                }
            }

            override fun onFailure(call: Call<GoogleResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun saveUserToken(token: String) {
        viewModelScope.launch {
            pref.setToken(token)
        }
    }

    fun deleteToken() {
        viewModelScope.launch {
            pref.deleteToken()
        }
    }

}