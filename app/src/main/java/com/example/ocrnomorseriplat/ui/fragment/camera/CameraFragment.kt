package com.example.ocrnomorseriplat.ui.fragment.camera

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.navigation.fragment.findNavController
import com.example.ocrnomorseriplat.R
import com.example.ocrnomorseriplat.databinding.FragmentCameraBinding
import com.example.ocrnomorseriplat.ui.fragment.FragmentPermissionHelper
import com.example.ocrnomorseriplat.ui.fragment.FragmentPermissionInterface
import com.example.ocrnomorseriplat.utils.Constants
import java.lang.Exception

class CameraFragment : Fragment(), View.OnClickListener{

    private lateinit var binding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var frs: FragmentActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState)
        binding = FragmentCameraBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        if(allPermissionGranted()){
            startCamera()
        }else{
            ActivityCompat.requestPermissions(requireActivity(), Constants.REQUIRED_PERMISSION, Constants.REQUEST_CODE_PERMISSION)
        }
        setAction()

        return view
    }

    fun allPermissionGranted() =
        Constants.REQUIRED_PERMISSION.all {
            ContextCompat.checkSelfPermission(
                requireContext(), it
            ) == PackageManager.PERMISSION_GRANTED
        }

    fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider
            .getInstance(requireContext())

        cameraProviderFuture.addListener({

            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.viewFinder.surfaceProvider
                    )
                }
            imageCapture = ImageCapture.Builder()
                .build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this, cameraSelector, preview, imageCapture
                )
            } catch (e: Exception){
                Log.d(Constants.TAG, "startCamera Fail:",e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun setAction(){
        binding.btnCapture.setOnClickListener(this)
        binding.btnEdit.setOnClickListener(this)
        binding.btnMenu.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_edit -> {
                val action = CameraFragmentDirections.actionCameraFragmentToInputFragment()
                findNavController().navigate(action)
            }
            R.id.btn_menu -> {
                val action = CameraFragmentDirections.actionCameraFragmentToVehicleFragment()
                findNavController().navigate(action)
            }
            R.id.btn_capture -> {
                Toast.makeText(requireActivity(), "Capture", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is FragmentActivity){
            frs = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentPermissionHelper().startPermissionRequest(frs,
            object : FragmentPermissionInterface {
                override fun onGranted(isGranted: Boolean) {
                    if (isGranted){
                        startCamera()
                    } else {
                        requireActivity().finish()
                    }
                }
            },Manifest.permission.CAMERA)
    }

}