package com.example.brightme.ui.navigation.activity.personal_consul

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.brightme.R
import com.example.brightme.data.adapter.ListDoctorAppointmentAdapter
import com.example.brightme.data.model.DoctorAppointment
import com.example.brightme.databinding.FragmentActivityBinding
import com.example.brightme.databinding.FragmentPersonalConsulBinding
import com.example.brightme.ui.navigation.activity.ActivityFragment
import com.example.brightme.ui.navigation.activity.community.CommunityActivity
import com.example.brightme.ui.navigation.activity.community.CommunityFragment
import com.example.brightme.ui.navigation.activity.glowup_education.GlowupEducationFragment
import com.example.brightme.ui.navigation.activity.glowup_plan.GlowupPlanFragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PersonalConsulFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PersonalConsulFragment : Fragment() {

    private var _binding: FragmentPersonalConsulBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val list = ArrayList<DoctorAppointment>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentPersonalConsulBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupView()
        setupAction()

        return root
    }

    private fun setupView() {
        binding.rvDoctorAppointment.setHasFixedSize(true)

        list.addAll(getListDoctor())
        binding.rvDoctorAppointment.layoutManager = LinearLayoutManager(requireContext())
        val listDoctorAdapter = ListDoctorAppointmentAdapter(list)
        binding.rvDoctorAppointment.adapter = listDoctorAdapter
    }

    private fun getListDoctor(): ArrayList<DoctorAppointment> {
        val dataName = resources.getStringArray(R.array.data_doctor_name)
        val dataNumber = resources.getStringArray(R.array.data_doctor_number)
        val dataPhoto = resources.obtainTypedArray(R.array.data_doctor_photo)
        val listDoctor = ArrayList<DoctorAppointment>()
        for (i in dataName.indices) {
            val doctor = DoctorAppointment(dataName[i], dataNumber[i], dataPhoto.getResourceId(i, -1))
            listDoctor.add(doctor)
        }
        return listDoctor
    }

    private fun setupAction() {
        binding.btnCanceled.setBackgroundResource(android.R.color.transparent)
        binding.btnDone.setBackgroundResource(android.R.color.transparent)

        for (i in 0 until binding.buttonGroup.childCount) {
            val button = binding.buttonGroup.getChildAt(i) as Button

            button.setOnClickListener {
                clearButtonBackgrounds()
                button.setBackgroundResource(R.drawable.bg_consul_selected)
                button.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                if (button == binding.buttonGroup.getChildAt(0)){
                    binding.rvDoctorAppointment.visibility = View.VISIBLE
                } else if (button == binding.buttonGroup.getChildAt(2)){
                    binding.canceled.visibility = View.VISIBLE
                }
            }
        }

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

    private fun clearButtonBackgrounds() {
        for (i in 0 until binding.buttonGroup.childCount) {
            val button = binding.buttonGroup.getChildAt(i) as Button
            button.setBackgroundResource(android.R.color.transparent)
            button.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            if (button != binding.buttonGroup.getChildAt(0)){
                binding.rvDoctorAppointment.visibility = View.GONE
            } else if (button != binding.buttonGroup.getChildAt(2)){
                binding.canceled.visibility = View.GONE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}