package com.example.brightme.ui.login

import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.EditText
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import com.example.brightme.data.UserPreference
import com.example.brightme.databinding.ActivityVerificationBinding
import com.example.brightme.ui.ViewModelFactory

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class VerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerificationBinding
    private lateinit var verificationViewModel: VerificationViewModel
    private lateinit var otpEditTexts: Array<EditText>
    private lateinit var token: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
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

        binding.tvEmail.text = intent.getStringExtra("email")

        otpEditTexts = arrayOf(
            binding.otpEditText1,
            binding.otpEditText2,
            binding.otpEditText3,
            binding.otpEditText4,
            binding.otpEditText5,
            binding.otpEditText6
        )

        setupOtpEditTexts()
    }

    private fun setupOtpEditTexts() {
        for (i in otpEditTexts.indices) {
            val currentEditText = otpEditTexts[i]

            currentEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                    // Tidak perlu diimplementasikan
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Tidak perlu diimplementasikan
                }

                override fun afterTextChanged(s: Editable?) {
                    if (s?.length == 1) {
                        if (i < otpEditTexts.lastIndex) {
                            otpEditTexts[i + 1].requestFocus()
                        } else {
                            // Tidak perlu diimplementasikan
                        }
                    }
                }
            })

            currentEditText.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
                if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_DOWN && i > 0 && currentEditText.text.isEmpty()) {
                    otpEditTexts[i - 1].requestFocus()
                }
                false
            })
        }
    }


    private fun setupViewModel() {
        verificationViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[VerificationViewModel::class.java]

        verificationViewModel.getToken().observe(this){
            if(it != null && it.toString() != "NotFound"){
                token = it.toString()
            }
        }
    }

    private fun setupAction() {
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }

        binding.submitButton.setOnClickListener {
            val otpBuilder = StringBuilder()
            for (editText in otpEditTexts) {
                otpBuilder.append(editText.text.toString())
            }
            val otp = otpBuilder.toString()

            verificationViewModel.verification(token, otp)
            verificationViewModel.userVerify.observe(this) {
                if (it != null) {
                    val intent = Intent(this, ResetPasswordActivity::class.java).also { send ->
                        send.putExtra("verificationToken", it.data.token)
                    }
                    startActivity(intent)
                }
            }
        }

        binding.tvLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }

}