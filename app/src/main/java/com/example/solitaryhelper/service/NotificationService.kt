package com.example.solitaryhelper.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.activity.MainActivity

class NotificationService : Service() {


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        basic(this@NotificationService, intent!!.extras!!, "전화에 응하시겠습니까?",
            "몰래 눌러주세요",)
        return START_STICKY

    }

    override fun onBind(intent: Intent?) = null

    fun clearExistingNotifications(notificationId: Int, manager: NotificationManager) {
        manager.cancel(notificationId)
    }



    @SuppressLint("WrongConstant")
    fun basic(context: Context, args: Bundle, title: String, content: String) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelId = "Call"
        clearExistingNotifications(444444, manager)
        clearExistingNotifications(123123, manager)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel = NotificationChannel(channelId, "알림", 0)
            channel.description = "알림 클릭대기"
            channel.setShowBadge(false)
            manager.createNotificationChannel(channel)
        }

        val pendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.main)
            .setDestination(R.id.fragmentFakeCall)
            .setArguments(args)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, channelId)

        builder.setSmallIcon(R.drawable.applogo_hood_line_64)
        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        startForeground(444444, builder.build())
    }
}