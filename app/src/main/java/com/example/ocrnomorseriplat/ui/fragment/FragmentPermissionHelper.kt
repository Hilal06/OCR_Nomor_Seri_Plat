package com.example.ocrnomorseriplat.ui.fragment

import android.Manifest
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity

class FragmentPermissionHelper {
    fun startPermissionRequest(fr: FragmentActivity, fs: FragmentPermissionInterface, manifest: String) {
        val requestPermissionLauncher: ActivityResultLauncher<String> =
            fr.registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                fs.onGranted(isGranted)
            }
        requestPermissionLauncher.launch(
            manifest
        )
    }
}