package com.example.brightme.ui.navigation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.brightme.databinding.FragmentHomeBinding
import com.example.brightme.ui.main.MainActivity
import com.google.firebase.auth.FirebaseAuth

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // Access the data passed to the fragment
        val name = arguments?.getString("name")

        // Set the text in the TextView
        if (name != null){
            binding.tvName.text = name
        }

        binding.searchView.setOnQueryTextListener(object  : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // Logika yang akan dijalankan saat pengguna menekan tombol 'Enter' di keyboard
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Logika yang akan dijalankan saat teks pencarian berubah
                return true
            }
        })

        homeViewModel.getProducts()
        homeViewModel.allProduct.observe(viewLifecycleOwner, {
            if (it != null) {
                Glide.with(this)
                    .load(it.get(0).imageUrl)
                    .into(binding.ivProduct1)
                binding.tvTitleProduct1.text = it.get(0).name
                binding.tvMerkProduct1.text = it.get(0).category.name
                binding.tvPriceProduct1.text = it.get(0).category.name


                Glide.with(this)
                    .load(it.get(1).imageUrl)
                    .into(binding.ivProduct2)
                binding.tvTitleProduct2.text = it.get(1).name
                binding.tvMerkProduct2.text = it.get(1).category.name
                binding.tvPriceProduct2.text = it.get(1).category.name
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}