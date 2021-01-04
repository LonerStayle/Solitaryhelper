package com.example.solitaryhelper.broardcastrecever

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R


class AlarmReceiver : BroadcastReceiver()  {


    override fun onReceive(context: Context?, intent: Intent?) {
        (context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager).also {
            clearExistingNotifications(444444,it)
            basic(it,context,intent?.getBundleExtra("args")!!,R.drawable.applogo_hood_line_64,
                "전화가 실행됩니다.", "연기해주세요", "Call")
        }

    }

     private fun clearExistingNotifications(notificationId: Int, manager: NotificationManager) {
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

        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.main)
            .setDestination(R.id.fragmentFakeCall)
            .setArguments(args)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, channelId)
        builder.setSmallIcon(icon)

        builder.setContentTitle(title)
        builder.setContentText(text)
        builder.priority = NotificationCompat.PRIORITY_DEFAULT
        builder.setAutoCancel(true)
        builder.setContentIntent(pendingIntent)

        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(444444, builder.build())
    }



}

