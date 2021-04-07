package com.example.zingproject

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_third.*

class ThirdFragment:Fragment(R.layout.fragment_third) {

    private lateinit var telephonyManager: TelephonyManager

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        telephonyManager =   context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager

        requestPermissions()
}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestPermissions(){
        var permissionsRequest =  mutableListOf<String>()

        if(!hasPhoneStatePermission()){
            permissionsRequest.add(Manifest.permission.READ_PHONE_STATE)
        }else{
            val details = """
           
         
            networkCountryISO : ${telephonyManager.networkCountryIso}
            SIM Country ISO : ${telephonyManager.simCountryIso}
            isRoaming : ${telephonyManager.isNetworkRoaming}
            """.trimIndent()


            thirdFragmentText.text = details
        }

        if(permissionsRequest.isNotEmpty()){
            requestPermissions(permissionsRequest.toTypedArray(),0)
        }
    }

    private fun hasPhoneStatePermission() = ContextCompat.checkSelfPermission(requireContext(),Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode==0 && grantResults.isNotEmpty()){

            for(i in grantResults.indices){
                if (grantResults[i]==PackageManager.PERMISSION_GRANTED){
                    // permission has been granted
                    val details = """
            
            networkCountryISO : ${telephonyManager.networkCountryIso}
            SIM Country ISO : ${telephonyManager.simCountryIso}
            isRoaming : ${telephonyManager.isNetworkRoaming}
            """.trimIndent()


                    thirdFragmentText.text = details
                }
            }

        }
    }
}
