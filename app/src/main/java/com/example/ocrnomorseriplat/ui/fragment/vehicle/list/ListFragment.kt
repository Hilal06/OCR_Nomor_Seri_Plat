package com.example.ocrnomorseriplat.ui.fragment.vehicle.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.ui.fragment.vehicle.VehicleFragment

class ListFragment : Fragment() {

    companion object {
        fun newInstance(key: Int): ListFragment {
            return ListFragment().apply {
                arguments = Bundle().apply {
                    putInt(FRAGMENT_KEY, key)
                }
            }
        }

        private const val FRAGMENT_KEY = "fragment_key"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }
}