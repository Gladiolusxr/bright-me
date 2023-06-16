package com.example.brightme.ui.navigation.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.brightme.data.UserPreference
import com.example.brightme.data.response.ProfileResponse
import com.example.brightme.data.response.User
import com.example.brightme.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileViewModel: ViewModel() {

//    private val _textName = MutableLiveData<String>().apply {
//        value = "Fanny"
//    }
//
//    private val _textEmail = MutableLiveData<String>().apply {
//        value = "fanny@gmail.com"
//    }
//    val textName: LiveData<String> = _textName
//    val textEmail: LiveData<String> = _textEmail

    private val _userProfile = MutableLiveData<User>()
    val userProfile: LiveData<User> = _userProfile

    companion object {
        private const val TAG = "ProfileViewModel"
    }

    fun showProfile(token: String) {
        var client: Call<ProfileResponse> = ApiConfig().getApiService().getProfile(token)
        client.enqueue(object : Callback<ProfileResponse> {
            override fun onResponse(
                call: Call<ProfileResponse>,
                response: Response<ProfileResponse>,
            ) {
                if (response.isSuccessful) {
                    _userProfile.postValue(response.body()?.data?.user)
                } else {
                    Log.e(TAG, "onFailure:${response.message()}")
                }
            }

            override fun onFailure(call: Call<ProfileResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }
}