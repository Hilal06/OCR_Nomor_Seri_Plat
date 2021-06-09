package com.example.ocrnomorseriplat.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.databinding.ActivityMainBinding
import com.example.ocrnomorseriplat.ui.fragment.camera.CameraFragment
import com.example.ocrnomorseriplat.utils.Constants


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val fragment =
            this.supportFragmentManager.findFragmentById(R.id.cameraFragment)
        (fragment as? IonRequestPermissionsResult)?.onRequestPermissionsResult()?.let {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    interface CameraFragmentListener{
        fun startCamera()
        fun allPermissionGranted(): Boolean
    }

    interface IonRequestPermissionsResult{
        fun onRequestPermissionsResult()
    }


}