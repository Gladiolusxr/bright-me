package com.example.brightme.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.brightme.data.UserPreference
import com.example.brightme.databinding.ActivityResetPasswordBinding
import com.example.brightme.ui.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var resetPasswordViewModel: ResetPasswordViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
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

    private fun setupViewModel() {
        resetPasswordViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[ResetPasswordViewModel::class.java]

    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.submitButton.setOnClickListener {
            val token = intent.getStringExtra("verificationToken")
            val newPassword = binding.passwordEditText.text.toString()
            val confirmPassword = binding.passwordEditText2.text.toString()
            when {
                newPassword.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Input new password"
                }
                confirmPassword.isEmpty() -> {
                    binding.passwordEditTextLayout2.error = "Input confirm password"
                }
                newPassword != confirmPassword -> {
                    binding.passwordEditTextLayout2.error = "Konfirmasi password tidak sesuai"
                }
                else -> {
                    if (token != null) {
                        resetPasswordViewModel.resetPassword(token, newPassword, confirmPassword)

                        resetPasswordViewModel.userReset.observe(this) {
                            if (it != null) {
                                val intent = Intent(this, ResetPassword2Activity::class.java)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }
}