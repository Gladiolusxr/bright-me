package com.example.brightme.ui.survey

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import com.example.brightme.databinding.ActivitySurvey2Binding
import com.example.brightme.ui.main.MainActivity

class SurveyActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySurvey2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurvey2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupAction()
    }

    private fun setupView() {
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
        supportActionBar?.hide()
    }

    private fun setupAction() {
        val name = intent.getStringExtra("name")

        binding.submitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java).also {
                it.putExtra("name", name)
            }
            startActivity(intent)
        }
    }
}