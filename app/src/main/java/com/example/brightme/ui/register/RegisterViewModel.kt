package com.example.brightme.ui.register

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.RegisterResponse
import com.example.brightme.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterViewModel(private val pref: UserPreference) : ViewModel() {

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