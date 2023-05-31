package com.example.brightme.ui.survey

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.brightme.databinding.ActivitySurvey2Binding

class SurveyActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivitySurvey2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySurvey2Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}