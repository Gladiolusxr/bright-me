package com.example.brightme.ui.main

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.brightme.R
import com.example.brightme.data.UserPreference
import com.example.brightme.databinding.ActivityMainBinding
import com.example.brightme.ui.UploadActivity
import com.example.brightme.ui.ViewModelFactory
import com.example.brightme.ui.login.LoginActivity
import com.example.brightme.ui.navigation.home.HomeFragment
import com.example.brightme.ui.navigation.profile.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var auth: FirebaseAuth
    private var signOutStatus: Boolean = false
    private var token: String = ""

    companion object{
        const val CAMERA_X_RESULT = 200

        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
        private const val REQUEST_CODE_PERMISSIONS = 10
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (!allPermissionsGranted()) {
                Toast.makeText(
                    this,
                    "Tidak mendapatkan permission.",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(baseContext, it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (!allPermissionsGranted()) {
            ActivityCompat.requestPermissions(
                this,
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

        setupViewModel()
        setupView()
        setupAction()
        setupSignOut()
    }

    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this,
            ViewModelFactory(UserPreference.getInstance(dataStore))
        )[MainViewModel::class.java]

        mainViewModel.getToken().observe(this){
            if(it != null && it.toString() != "NotFound"){
                token = it.toString()
                Log.e("it getToken()", "hasil token = $token")
            }
        }

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

        val navView: BottomNavigationView = binding.bottomNavigationView

        val navController = findNavController(R.id.nav_host_fragment_activity_home)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_activity,
                R.id.navigation_shop,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        val name = intent.getStringExtra("name")

        val homeFragment = HomeFragment()
        homeFragment.arguments = bundleOf("name" to name)
        if (token != ""){
            homeFragment.arguments = bundleOf("token" to token)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.nav_host_fragment_activity_home, homeFragment)
            .commit()
    }

    private fun setupAction() {
        binding.fab.setOnClickListener {
            startActivity(Intent(this@MainActivity, UploadActivity::class.java))
        }
    }

    private fun setupSignOut() {
        auth = Firebase.auth
        val firebaseUser = auth.currentUser
        signOutStatus = intent.getBooleanExtra("signOut", false)

        if (firebaseUser == null && signOutStatus == true) {
            // Not signed in, launch the Login activity
            this@MainActivity.getSharedPreferences("data", 0)
                .edit().clear().apply()
            mainViewModel.logout()

            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
    }
}