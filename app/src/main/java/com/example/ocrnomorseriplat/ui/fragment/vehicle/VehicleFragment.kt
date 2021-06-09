package com.example.ocrnomorseriplat.ui.fragment.vehicle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.databinding.FragmentVehicleBinding

class VehicleFragment : Fragment() {

    private lateinit var binding: FragmentVehicleBinding
    private lateinit var adapterVehicle: VehicleSectionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehicleBinding.inflate(inflater, container, false)
        val view = binding.root
        setupViewPager()
        return view
    }

    private fun setupViewPager(){
        adapterVehicle = VehicleSectionAdapter(childFragmentManager, requireContext())
        binding.vpVehicle.adapter = adapterVehicle
        binding.tabLayout.setupWithViewPager(binding.vpVehicle)
    }
}