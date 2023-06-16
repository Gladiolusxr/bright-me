package com.example.brightme.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.brightme.R
import com.example.brightme.data.retrofit.FileUpload2
import com.example.brightme.databinding.ActivityResultBinding
import com.example.brightme.ui.survey.ResultAdapter
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private var resultAdapter = ResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val responseData = intent.getParcelableExtra<FileUpload2>("extra_data")

        binding.tvDisease.text = responseData?.data?.skinDiseases?.label?.toString()
        binding.tvSkinType.text = responseData?.data?.skinTypes?.label?.toString()

        //init recyclerview and adapter
        val productRecommendation = responseData?.data?.productRecommendation
        binding.rvRecommendation.adapter = resultAdapter
        resultAdapter.submitList(productRecommendation)

        //init listener in recyclerview
        resultAdapter.productListener = { product ->
            MaterialAlertDialogBuilder(this@ResultActivity, R.style.Theme_BrightMe)
                .setTitle(product.name)
                .setMessage(product.description)
                .setBackground(getDrawable(R.drawable.bg_background))
                .setPositiveButton("Ok") { dialog, which ->
                    dialog.dismiss()
                }
                .show()
        }
    }
}