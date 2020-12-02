package com.example.solitaryhelper.view.utill

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.dest.fake_call.FragmentFakeCall.Companion.MUSIC_TEST

fun clearExistingNotifications(notificationId: Int, manager: NotificationManager) {
    manager.cancel(notificationId)
}

fun messagingStyle(
    context: Context,
    manager: NotificationManager,
    bundle: Bundle?
) {
    clearExistingNotifications(MUSIC_TEST, manager)
    createNotificationChannel(context)

    val channelId = "음악 테스트"

    val icon = IconCompat.createWithResource(context, R.drawable.applogo_hood_line_64)

    val timestamp = System.currentTimeMillis()
    val user1 = androidx.core.app.Person.Builder().setIcon(icon).setName("음악테스트중").build()
    val style = NotificationCompat.MessagingStyle(user1)
        .addMessage("음악 테스트중", timestamp, user1)


    val pendingIntent = NavDeepLinkBuilder(context).run {
        setGraph(R.navigation.main)
        setArguments(bundle)
        setDestination(R.id.fragmentFakeKakaoChat)
        setComponentName(MainActivity::class.java)
        createPendingIntent()
    }


    val builder = NotificationCompat.Builder(context, channelId).apply {
        setSmallIcon(R.drawable.applogo_hood_line_64)
        setLargeIcon(
            BitmapFactory.decodeResource(
                context.resources,
                R.drawable.kakao_talk_logo
            )
        )
        setStyle(style)
        setContentIntent(pendingIntent)
        setAutoCancel(true)
        priority = NotificationCompat.PRIORITY_HIGH

    }


    val notificationManager = NotificationManagerCompat.from(context)
    notificationManager.notify(MUSIC_TEST, builder.build())

}


private fun createNotificationChannel(context: Context) {

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "musicTest"
        val channel =
            NotificationChannel(channelId, "백그라운드\n테스트중", NotificationManager.IMPORTANCE_HIGH)
        channel.description = "테스트중."
        channel.setShowBadge(false)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}
