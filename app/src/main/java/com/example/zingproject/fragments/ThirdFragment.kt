package com.example.zingproject.fragments

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.akexorcist.localizationactivity.core.LanguageSetting.setLanguage
import com.example.zingproject.R
import com.google.android.material.switchmaterial.SwitchMaterial
import kotlinx.android.synthetic.main.fragment_third.*
import java.util.*

class ThirdFragment : Fragment(R.layout.fragment_third) {

    private lateinit var telephonyManager: TelephonyManager
    private var isMulti: Int = 1
    private lateinit var switchTheme: SwitchMaterial
    private lateinit var switchLang: SwitchMaterial

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
        telephonyManager = context?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        requestPermissions()
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_PHONE_STATE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        isMulti = telephonyManager.isMultiSimSupported
        Log.e("sim Serial Number", "" + telephonyManager.simSerialNumber)


        simDetails()

         */

        switchTheme = view.findViewById(R.id.darkSwitch) as SwitchMaterial

        switchTheme.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

        }

        switchLang = view.findViewById(R.id.changeLngSwitch) as SwitchMaterial
        switchLang.setOnCheckedChangeListener { buttonView, isChecked ->

            if (isChecked) {

                setLanguage(requireContext(), Locale("hi", "In"))
            }
        }


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestPermissions() {
        var permissionsRequest = mutableListOf<String>()

        if (!hasPhoneStatePermission()) {
            permissionsRequest.add(Manifest.permission.READ_PHONE_STATE)
        } else {
            val details = """
           
           Call State  :  ${telephonyManager.callState}
           Data State :            ${telephonyManager.dataState}
           is Data Enabled:            ${telephonyManager.isDataEnabled}
           Phone Type :            ${telephonyManager.phoneType}
           Network Operator Name:            ${telephonyManager.networkOperatorName}
           Sim Operator Name :            ${telephonyManager.simOperatorName}
           Sim State :            ${telephonyManager.getSimState()}
            networkCountryISO : ${telephonyManager.networkCountryIso}
            SIM Country ISO : ${telephonyManager.simCountryIso}
            isRoaming : ${telephonyManager.isNetworkRoaming}
            isMultiSim :${isMulti}
            """.trimIndent()

            val simState = telephonyManager.getSimState(0)

            when (simState) {
                TelephonyManager.SIM_STATE_ABSENT -> Log.e("sim", "sim absent")
                TelephonyManager.SIM_STATE_READY -> Log.e("sim", "sim ready")
            }





            thirdFragmentText.text = details
        }

        if (permissionsRequest.isNotEmpty()) {
            requestPermissions(permissionsRequest.toTypedArray(), 0)
        }
    }

    private fun hasPhoneStatePermission() = ContextCompat.checkSelfPermission(
        requireContext(),
        Manifest.permission.READ_PHONE_STATE
    ) == PackageManager.PERMISSION_GRANTED


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 0 && grantResults.isNotEmpty()) {

            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    // permission has been granted
                    val details = """
            
            Call State  :  ${telephonyManager.callState}
           Data State :           ${telephonyManager.dataState}
           is Data Enabled:           ${telephonyManager.isDataEnabled}
           Phone Type :           ${telephonyManager.phoneType}
           Network Operator Name:           ${telephonyManager.networkOperatorName}
           Sim Operator Name :           ${telephonyManager.simOperatorName}
           Sim State :           ${telephonyManager.getSimState()}
            networkCountryISO : ${telephonyManager.networkCountryIso}
            SIM Country ISO : ${telephonyManager.simCountryIso}
            isRoaming : ${telephonyManager.isNetworkRoaming}
            isMultiSim :            ${isMulti}
            """.trimIndent()


                    thirdFragmentText.text = details
                }
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun simDetails() {
        Log.e("sim", "" + telephonyManager.subscriptionId)


    }
}
