package com.example.zingproject.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.zingproject.R
import kotlinx.android.synthetic.main.fragment_second.*

class SecondFragment :Fragment(R.layout.fragment_second) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val details = """
            VERSION.RELEASE : ${Build.VERSION.RELEASE}
            VERSION.INCREMENTAL : ${Build.VERSION.INCREMENTAL}
            VERSION.SDK.NUMBER : ${Build.VERSION.SDK_INT}
            BOARD : ${Build.BOARD}
            BOOTLOADER : ${Build.BOOTLOADER}
            BRAND : ${Build.BRAND}
            CPU_ABI : ${Build.CPU_ABI}
            CPU_ABI2 : ${Build.CPU_ABI2}
            DISPLAY : ${Build.DISPLAY}
            FINGERPRINT : ${Build.FINGERPRINT}
            HARDWARE : ${Build.HARDWARE}
            HOST : ${Build.HOST}
            ID : ${Build.ID}
            MANUFACTURER : ${Build.MANUFACTURER}
            MODEL : ${Build.MODEL}
            PRODUCT : ${Build.PRODUCT}
            SERIAL : ${Build.SERIAL}
            TAGS : ${Build.TAGS}
            TIME : ${Build.TIME}
            TYPE : ${Build.TYPE}
            UNKNOWN : ${Build.UNKNOWN}
            USER : ${Build.USER}
            """.trimIndent()

        Log.e("Device Details", details)
        deviceInfoText.text = details
    }
}