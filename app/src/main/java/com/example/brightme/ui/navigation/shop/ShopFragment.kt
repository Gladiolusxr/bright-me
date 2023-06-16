package com.example.brightme.ui.navigation.shop

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.brightme.databinding.FragmentShopBinding

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val shopViewModel = ViewModelProvider(this).get(ShopViewModel::class.java)

        _binding = FragmentShopBinding.inflate(inflater, container, false)
        val root: View = binding.root

        shopViewModel.getProducts()
        shopViewModel.allProduct.observe(viewLifecycleOwner, {
            if (it != null) {
                Glide.with(this)
                    .load(it.get(2).imageUrl)
                    .into(binding.ivSpecialOffer)
                binding.tvNameSpecialOffer.text = it.get(2).name
                binding.tvDescSpecialOffer.text = it.get(2).description
                binding.tvPriceSpecialOffer.text = it.get(2).category.name


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

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}