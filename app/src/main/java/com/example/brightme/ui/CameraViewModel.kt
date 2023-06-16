package com.example.brightme.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.brightme.data.UserPreference
import com.example.brightme.data.retrofit.ApiConfig
import com.example.brightme.data.retrofit.FileUpload2
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CameraViewModel(private val pref: UserPreference) : ViewModel() {
    private val _userStory = MutableLiveData<FileUpload2>()
    val userStory: LiveData<FileUpload2> = _userStory

    companion object {
        private const val TAG = "CameraViewModel"
    }

    fun uploadImage(photo: MultipartBody.Part, token: String) {
        var client: Call<FileUpload2> =
            ApiConfig().getApiService().uploadImage(photo, token)
        client.enqueue(object : Callback<FileUpload2> {
            override fun onResponse(
                call: Call<FileUpload2>,
                response: Response<FileUpload2>,
            ) {
                if (response.isSuccessful) {
                    _userStory.postValue(response.body())
                } else {
                    Log.e(TAG, "onFailure:${response.message()}")
                }
            }

            override fun onFailure(call: Call<FileUpload2>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getUser(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

}