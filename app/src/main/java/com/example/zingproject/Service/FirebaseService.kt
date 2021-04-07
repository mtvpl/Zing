package com.example.zingproject.Service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.zingproject.MainActivity
import com.example.zingproject.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONObject
import kotlin.random.Random


private const val CHANNEL_ID = "Channel_1"

class FirebaseService : FirebaseMessagingService() {

    companion object {
        var sharedPref: SharedPreferences? = null
        var token: String?
            get() {
                return sharedPref?.getString("token", "")
            }
            set(value) {
                sharedPref?.edit()?.putString("token", value)?.apply()
            }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        //  val intent = Intent(this, MainActivity::class.java)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationId = Random.nextInt()
        // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager)
        }

        val args = Bundle()
        args.putString("seqNo", message.data["seqNo"])
        args.putString("title", message.data["title"])
        args.putString("senderRef", message.data["senderRef"])
        args.putString("senderId", message.data["senderId"])
        args.putString("body", message.data["body"])
        args.putString("msgRef", message.data["msgRef"])
        args.putString("msgType", message.data["msgType"])

        args.putString("ts", message.data["ts"])
        args.putString("deleted", message.data["deleted"])
        args.putString("msgRead", message.data["msgRead"])

        val params: Map<String, String> = message.data
        val jsonObject = JSONObject(params)
        val dataPart = jsonObject.get("dataPart").toString()

        args.putString("dataPart", dataPart)

        val pendingIntent = NavDeepLinkBuilder(this)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.main_nav_graph)
            .setDestination(R.id.homeFragment)
            .setArguments(args)
            .createPendingIntent()

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(message.notification!!.title)
            .setContentText(message.notification!!.body)
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()

        notificationManager.notify(notificationId, notification)

        Log.e("TAG", message.data["Name"] + message.data["Email"])


    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channelName = "channelName"
        val channel = NotificationChannel(
            CHANNEL_ID,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        ).apply {
            description = "My channel description"
        }

        notificationManager.createNotificationChannel(channel)
    }


    override fun onNewToken(newToken: String) {
        super.onNewToken(newToken)
        token = newToken
    }
}