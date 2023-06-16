package com.example.brightme.ui.navigation.profile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.brightme.databinding.FragmentProfileBinding
import com.example.brightme.ui.DataListener
import com.example.brightme.ui.login.LoginActivity
import com.example.brightme.ui.main.MainActivity
import com.example.brightme.ui.navigation.home.HomeFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class ProfileFragment : Fragment(), DataListener {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var token: String

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    companion object {
        private const val FRAGMENT_REQUEST_CODE = 123
    }

    override fun onDataReceived(dataToken: String) {
        token = dataToken

        Log.e("Cek token", "token = $token")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val notificationsViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)
        val profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val tvName: TextView = binding.tvName
//        val tvEmail : TextView = binding.tvEmail

        val homeFragment = HomeFragment()
        val profileFragment = ProfileFragment()

        homeFragment.setDataListener(profileFragment)

        if (token != null) {
            profileViewModel.showProfile(token)

            profileViewModel.userProfile.observe(viewLifecycleOwner){
                if (it != null){
                    binding.tvName.text = it.name
                    binding.tvEmail.text = it.email
                }
            }
        }

        binding.btnSignOut.setOnClickListener {
            auth = Firebase.auth
            auth.signOut()

            val intent = Intent(activity, MainActivity::class.java)
            intent.putExtra("signOut", true)
            startActivity(intent)
        }

//        notificationsViewModel.textName.observe(viewLifecycleOwner) {
//            tvName.text = it
//        }
//
//        notificationsViewModel.textEmail.observe(viewLifecycleOwner) {
//            tvEmail.text = it
//        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}