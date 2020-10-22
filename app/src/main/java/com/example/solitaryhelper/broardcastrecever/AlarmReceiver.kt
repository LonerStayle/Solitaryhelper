package com.example.solitaryhelper.broardcastrecever

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver:BroadcastReceiver() {
    companion object {
        const val NOTIFICATION_ID = 0
        const val PRIMARY_CHANNEL_ID = "primary_notification_channel"
    }
    lateinit var notificationManager: NotificationManager
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("receiverCheck","Recived intent:$intent")
//        Log.d("receiverCheck", "Received intent : $intent")
//        notificationManager = context.getSystemService(
//            Context.NOTIFICATION_SERVICE) as NotificationManager
//
//        createNotificationChannel()
//        deliverNotification(context)
    }
}