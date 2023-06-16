package com.example.brightme.ui.navigation.activity.glowup_education

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brightme.R
import com.example.brightme.data.adapter.ListEducationAdapter
import com.example.brightme.data.model.Education
import com.example.brightme.databinding.FragmentGlowupEducationBinding
import com.example.brightme.ui.navigation.activity.ActivityFragment
import com.example.brightme.ui.navigation.activity.community.CommunityActivity
import com.example.brightme.ui.navigation.activity.community.CommunityFragment
import com.example.brightme.ui.navigation.activity.glowup_plan.GlowupPlanFragment
import com.example.brightme.ui.navigation.activity.personal_consul.PersonalConsulFragment

class GlowupEducationFragment : Fragment() {

    private var _binding: FragmentGlowupEducationBinding? = null
    private val binding get() = _binding!!
    private val list = ArrayList<Education>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentGlowupEducationBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupView()
        setupAction()

        return root
    }

    private fun setupView() {
        binding.rvEducation.setHasFixedSize(true)

        list.addAll(getListEducation())
        binding.rvEducation.layoutManager = LinearLayoutManager(requireContext())
        val listEducationAdapter = ListEducationAdapter(list)
        binding.rvEducation.adapter = listEducationAdapter
    }

    private fun getListEducation(): ArrayList<Education> {
        val dataTitle = resources.getStringArray(R.array.data_title_education)
        val dataName = resources.getStringArray(R.array.data_doctor_name_education)
        val dataVideo = resources.getStringArray(R.array.data_number_of_videos)
        val dataPhoto = resources.obtainTypedArray(R.array.data_doctor_photo_education)
        val listEducation = ArrayList<Education>()
        for (i in dataTitle.indices) {
            val education = Education(dataTitle[i], dataName[i], dataVideo[i], dataPhoto.getResourceId(i, -1))
            listEducation.add(education)
        }
        return listEducation
    }

    private fun setupAction() {
        binding.header.setOnClickListener {
            binding.header.visibility = View.INVISIBLE
            binding.viewBg2.visibility = View.VISIBLE
            binding.transparan.visibility = View.VISIBLE
        }

        binding.header2.setOnClickListener {
            binding.viewBg2.visibility = View.GONE
            binding.transparan.visibility = View.GONE
            binding.header.visibility = View.VISIBLE
        }

        binding.submenu1.setOnClickListener {
            val fragmentActivity = ActivityFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragmentActivity)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.submenu2.setOnClickListener {
            val fragmentPersonal = PersonalConsulFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragmentPersonal)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.submenu3.setOnClickListener {
            binding.viewBg2.visibility = View.GONE
            binding.transparan.visibility = View.GONE
            binding.header.visibility = View.VISIBLE
        }

        binding.submenu4.setOnClickListener {
            val fragmentPlan = GlowupPlanFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragmentPlan)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }

        binding.submenu5.setOnClickListener {
//            val fragmentCommunity = CommunityFragment()
//            val fragmentTransaction = requireFragmentManager().beginTransaction()
//            fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragmentCommunity)
//            fragmentTransaction.addToBackStack(null)
//            fragmentTransaction.commit()

            val intent = Intent(activity, CommunityActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}