package com.example.brightme.ui.navigation.activity.community

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.brightme.R
import com.example.brightme.databinding.FragmentCommunityBinding
import com.example.brightme.ui.navigation.activity.ActivityFragment
import com.example.brightme.ui.navigation.activity.glowup_education.GlowupEducationFragment
import com.example.brightme.ui.navigation.activity.glowup_plan.GlowupPlanFragment

class CommunityFragment : Fragment() {

    private var _binding: FragmentCommunityBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCommunityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupAction()

        return root
    }

    private fun setupAction() {
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}