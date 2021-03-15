package com.example.zingproject

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.zingproject.Service.FirebaseService
import com.google.firebase.iid.FirebaseInstanceId
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        main_bottom_nav.setupWithNavController(navController)

        FirebaseService.sharedPref = getSharedPreferences("Zing App", Context.MODE_PRIVATE)
        FirebaseInstanceId.getInstance().instanceId.addOnSuccessListener {
            FirebaseService.token = it.token
        }

        if (intent != null && intent.hasExtra("Name")) {

            val args = Bundle()
            for (key in intent.extras!!.keySet()) {
                val data = intent.extras!!.getString(key)
                args.putString(key, data)
            }

            navController.navigate(R.id.homeFragment, args)

        }
    }
}