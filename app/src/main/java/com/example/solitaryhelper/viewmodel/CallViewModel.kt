package com.example.solitaryhelper.viewmodel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.dataclass.KaKaoTalkData


class CallViewModel : ViewModel() {


     fun clearExistingNotifications(notificationId: Int,manager: NotificationManager) {
        manager.cancel(notificationId)
    }
    private fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean,
                                          name: String, description: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun basic(manager: NotificationManager,context: Context,args:Bundle,
    @DrawableRes icon:Int,title:String,text:String,channelId:String) {
        clearExistingNotifications(444444,manager)
        clearExistingNotifications(123123,manager)
        createNotificationChannel(context, NotificationManagerCompat.IMPORTANCE_DEFAULT, false,
            channelId, "App notification channel")

        val channelId = channelId
        val title = title
        val content = text

        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.main)
            .setDestination(R.id.fragmentFakeCall)
            .setArguments(args)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, channelId)
        builder.setSmallIcon(icon)

        builder.setContentTitle(title)
        builder.setContentText(content)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(444444, builder.build())
    }

}

