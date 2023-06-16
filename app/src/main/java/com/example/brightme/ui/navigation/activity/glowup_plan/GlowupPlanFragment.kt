package com.example.brightme.ui.navigation.activity.glowup_plan

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.R
import com.example.brightme.databinding.FragmentGlowupPlanBinding
import com.example.brightme.ui.navigation.activity.ActivityFragment
import com.example.brightme.ui.navigation.activity.community.CommunityActivity
import com.example.brightme.ui.navigation.activity.community.CommunityFragment
import com.example.brightme.ui.navigation.activity.glowup_education.GlowupEducationFragment

private lateinit var adapter : GlowUpPlanAdapter
private lateinit var recyclerView : RecyclerView
private lateinit var planArrayList : ArrayList <GlowUpPlan>

lateinit var imageId : Array <Int>
lateinit var date : Array <String>


class GlowupPlanFragment : Fragment() {

    private var _binding: FragmentGlowupPlanBinding? = null
    private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            _binding = FragmentGlowupPlanBinding.inflate(inflater, container, false)

            val root: View = binding.root

            setupAction()

            return root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            dataInitialize()
            recyclerView = view.findViewById(R.id.rv_plan)
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            recyclerView.setHasFixedSize(true)
            adapter = GlowUpPlanAdapter(planArrayList)
            recyclerView.adapter = adapter
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
            binding.viewBg2.visibility = View.GONE
            binding.transparan.visibility = View.GONE
            binding.header.visibility = View.VISIBLE
        }

        binding.submenu3.setOnClickListener {
            val fragmentEducation = GlowupEducationFragment()
            val fragmentTransaction = requireFragmentManager().beginTransaction()
            fragmentTransaction.replace(R.id.nav_host_fragment_activity_home, fragmentEducation)
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
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

        private fun dataInitialize() {
            planArrayList = arrayListOf<GlowUpPlan>()
            imageId = arrayOf(
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
                R.drawable.ic_image_face,
            )

            date = arrayOf(
                getString(R.string.week_1),
                getString(R.string.week_2),
                getString(R.string.week_3),
                getString(R.string.week_3),
                getString(R.string.week_4),
                getString(R.string.week_5),
                getString(R.string.week_6),
                getString(R.string.week_7),
            )

            for (i in imageId.indices) {
                val plan = GlowUpPlan(imageId[i], date[i])
                planArrayList.add(plan)
            }
        }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}