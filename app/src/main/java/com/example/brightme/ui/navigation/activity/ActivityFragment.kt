package com.example.brightme.ui.navigation.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.brightme.ActivityAdapter
import com.example.brightme.R
import com.example.brightme.data.Doctor
import com.example.brightme.databinding.FragmentActivityBinding
import com.example.brightme.ui.navigation.activity.community.CommunityActivity
import com.example.brightme.ui.navigation.activity.community.CommunityFragment
import com.example.brightme.ui.navigation.activity.glowup_education.GlowupEducationFragment
import com.example.brightme.ui.navigation.activity.glowup_plan.GlowupPlanFragment
import com.example.brightme.ui.navigation.activity.personal_consul.PersonalConsulFragment

private lateinit var adapter : ActivityAdapter
private lateinit var recyclerView : RecyclerView
private lateinit var doctorArrayList : ArrayList <Doctor>

lateinit var imageId : Array <Int>
lateinit var heading : Array <String>
lateinit var rating : Array <String>
lateinit var online : Array <String>
lateinit var years : Array <String>
lateinit var price : Array <String>

class ActivityFragment : Fragment() {

    private var _binding: FragmentActivityBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val activityViewModel = ViewModelProvider(this).get(ActivityViewModel::class.java)

        _binding = FragmentActivityBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val rvDoctor: RecyclerView = binding.rvDoctor

        setupAction()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dataInitialize()
        recyclerView = view.findViewById(R.id.rv_doctor)
        recyclerView.layoutManager = GridLayoutManager (requireContext(), 2)
        recyclerView.setHasFixedSize(true)
        adapter = ActivityAdapter(doctorArrayList)
        recyclerView.adapter = adapter
    }

    private fun setupAction() {
        binding.header.setOnClickListener {
            binding.viewBg.visibility = View.INVISIBLE
            binding.viewBg2.visibility = View.VISIBLE
            binding.transparan.visibility = View.VISIBLE
        }

        binding.header2.setOnClickListener {
            binding.viewBg2.visibility = View.GONE
            binding.transparan.visibility = View.GONE
            binding.viewBg.visibility = View.VISIBLE
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

    private fun dataInitialize (){
        doctorArrayList = arrayListOf<Doctor>()
        imageId = arrayOf(
            R.drawable.ic_doctor,
            R.drawable.iv_doctor_b,
            R.drawable.iv_doctor_c,
            R.drawable.iv_doctor_d,
            R.drawable.ic_doctor,
            R.drawable.iv_doctor_b,
            R.drawable.iv_doctor_c,
            R.drawable.iv_doctor_d,
        )

        heading = arrayOf(
            getString(R.string.doctor_a),
            getString(R.string.doctor_b),
            getString(R.string.doctor_c),
            getString(R.string.doctor_d),
            getString(R.string.doctor_a),
            getString(R.string.doctor_b),
            getString(R.string.doctor_c),
            getString(R.string.doctor_d),
        )

        rating = arrayOf(
            getString(R.string.rating_a),
            getString(R.string.rating_b),
            getString(R.string.rating_c),
            getString(R.string.rating_d),
            getString(R.string.rating_a),
            getString(R.string.rating_b),
            getString(R.string.rating_c),
            getString(R.string.rating_d),
        )

        online = arrayOf(
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
            getString(R.string.status),
        )

        years = arrayOf(
            getString(R.string.years_a),
            getString(R.string.years_b),
            getString(R.string.years_c),
            getString(R.string.years_d),
            getString(R.string.years_a),
            getString(R.string.years_b),
            getString(R.string.years_c),
            getString(R.string.years_d),
        )

        price = arrayOf(
            getString(R.string.price_a),
            getString(R.string.price_b),
            getString(R.string.price_c),
            getString(R.string.price_d),
            getString(R.string.price_a),
            getString(R.string.price_b),
            getString(R.string.price_c),
            getString(R.string.price_d),
        )

        for (i in imageId.indices){
            val doctor = Doctor(imageId[i], heading[i], rating[i], online [i], years [i], price[i])
            doctorArrayList.add(doctor)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}