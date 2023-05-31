package com.example.brightme.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brightme.databinding.ActivityRegisterVerificationBinding

class RegisterVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}