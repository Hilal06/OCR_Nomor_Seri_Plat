package com.example.ocrnomorseriplat.ui.fragment.input

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.databinding.FragmentInputBinding

class InputFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentInputBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentInputBinding.inflate(inflater, container, false)
        val view = binding.root
        setAction()
        return view
    }

    private fun setAction(){
        binding.ivBack.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        findNavController().navigateUp()
    }

}