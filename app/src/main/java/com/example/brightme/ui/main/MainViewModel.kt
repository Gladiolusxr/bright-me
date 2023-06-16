package com.example.brightme.ui.main

import android.util.Log
import androidx.lifecycle.*
import com.example.brightme.data.UserModel
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.LoginResponse
import com.example.brightme.data.retrofit.ApiConfig
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: UserPreference) : ViewModel() {
    fun logout() {
        viewModelScope.launch {
            pref.deleteToken()
        }
    }

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }
}