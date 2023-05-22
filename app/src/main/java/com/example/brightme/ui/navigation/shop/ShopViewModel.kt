package com.example.brightme.ui.navigation.shop

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShopViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is Activity Fragment"
    }
    val text: LiveData<String> = _text
}