package com.mosl.shared

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.ArrayList

class CheckPermissionHelper() {

    private lateinit var context: AppCompatActivity
    private var fragment: Fragment? = null
    private var activity: Activity? = null

    constructor(
        context: AppCompatActivity,
        activity: Activity,
    ) : this() {
        this.context = context
        this.activity = activity
    }

    constructor(
        context: AppCompatActivity,
        fragment: Fragment,
    ) : this() {
        this.context = context
        this.fragment = fragment
    }

    fun checkAndRequestPermissions() {
        val camera = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA)

        val listPermissionsNeeded = ArrayList<String>()

        if (camera != PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA)
        }

        if (listPermissionsNeeded.isNotEmpty()) {
            if (fragment != null)
                fragment?.requestPermissions(
                    listPermissionsNeeded.toTypedArray(),
                    MY_PERMISSIONS_REQUEST_READ
                )
            else if (activity != null && VERSION.SDK_INT >= VERSION_CODES.M)
                activity?.requestPermissions(
                    listPermissionsNeeded.toTypedArray(),
                    MY_PERMISSIONS_REQUEST_READ
                )
        }
    }

    fun onPermissionsResult(
        requestCode: Int,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_READ -> {
                if (grantResults.isEmpty() || grantResults[0] != PERMISSION_GRANTED) {
                    showPermissionNotGrantedDialog()
                }
            }
        }
    }

    private fun showPermissionNotGrantedDialog() {
        AlertDialog.Builder(context).setMessage(
            R.string.text_label_camera_permission_message
        )
            .setPositiveButton(
                R.string.button_label_yes
            ) { _, _ ->
                checkAndRequestPermissions()
            }
            .setNegativeButton(
                R.string.button_label_no
            ) { _, _ ->
                Log.d("Permission Not Set", "Permission Not Set")
            }
            .show()
    }

    fun permissionStatus(): Boolean {
        return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == 0
    }

    companion object {
        private const val MY_PERMISSIONS_REQUEST_READ = 1
    }
}