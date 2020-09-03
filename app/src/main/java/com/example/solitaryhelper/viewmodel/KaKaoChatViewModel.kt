package com.example.solitaryhelper.viewmodel

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.localdb.dao.KaKaoChatDao
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatData
import com.example.solitaryhelper.localdb.entitiy.KaKaoTalkChatDataCopy
import com.example.solitaryhelper.view.dest.fake_kakao.FragmentFakeKakaoChat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class KaKaoChatViewModel(private val dataSource: KaKaoChatDao) : ViewModel() {
    val ioScope = CoroutineScope(Dispatchers.IO + Job())

    val chatList: LiveData<List<KaKaoTalkChatData>>
        get() = dataSource.getAllList()

    val _chatListPlus = MutableLiveData<KaKaoTalkChatDataCopy>()
    val chatListPlus:LiveData<KaKaoTalkChatDataCopy>
    get() = _chatListPlus


    //챗 리스트 인서트
    fun insert(kakao: KaKaoTalkChatData) {
        ioScope.launch {
            dataSource.insert(kakao)
        }
    }

    fun insertCopyData(data:KaKaoTalkChatDataCopy){
        _chatListPlus.postValue(data)
    }


    fun clearExistingNotifications(notificationId: Int, manager: NotificationManager) {
        manager.cancel(notificationId)
    }

    fun messagingStyle(
        @DrawableRes icon: Int,
        context: Context,
        manager: NotificationManager,
        name: String,
        message: String

    ) {
        clearExistingNotifications(FragmentFakeKakaoChat.NOTIFICATION_ID, manager)
        clearExistingNotifications(FragmentFakeKakaoChat.NOTIFICATION_ID_2, manager)

        createNotificationChannel(context)

        val channelId = "FakeKaKaoTalk"

        val userIcon1 = IconCompat.createWithResource(context, icon)
        val userName1 = name

        val timestamp = System.currentTimeMillis()
        val user1 = androidx.core.app.Person.Builder().setIcon(userIcon1).setName(userName1).build()
        val style = NotificationCompat.MessagingStyle(user1)
            .addMessage(message, timestamp, user1)


//        val intent = Intent(activityContext, AutoChatRunCheckActiviy::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//        val pendingIntent = PendingIntent.getActivity(
//            context, 0,
//            intent, PendingIntent.FLAG_UPDATE_CURRENT
//        )
        val pendingIntent = NavDeepLinkBuilder(context)
            .setGraph(R.navigation.main)
            .setDestination(R.id.fragmentAutoChatRunCheck)
            .createPendingIntent()

        val builder = NotificationCompat.Builder(context, channelId)
        builder.setSmallIcon(icon)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    R.drawable.kakao_talk_logo
                )
            )
            .setStyle(style)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)


        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(FragmentFakeKakaoChat.NOTIFICATION_ID, builder.build())
    }


    private fun createNotificationChannel(context: Context) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "FakeKaKaoTalk"
            val channel =
                NotificationChannel(channelId, "FakeKaKaoTalk", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "세 메시지가 도착했습니다."
            channel.setShowBadge(false)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }

}
//    fun setTimeVisible(chatList:List<KaKaoTalkChatData>,timeVisibleList: MutableList<Int>?) {
//        if ((chatList.last().timeList == chatList[chatList.lastIndex - 1].timeList)
//            && chatList.last().user == chatList[chatList.lastIndex - 1].user
//        ) {
//            timeVisibleList?.set(timeVisibleList.lastIndex - 1, View.INVISIBLE)
//            timeVisibleList?.add(View.VISIBLE)
//
//        } else
//            timeVisibleList?.add(View.VISIBLE)
//
//
//    }


//    fun setProfileVisible(chatList:List<KaKaoTalkChatData>,thisprofileVisibleList: MutableList<Int>?) {
//        if (chatList.last().user != chatList[chatList.lastIndex - 1].user ||
//            chatList.last().timeList != chatList[chatList.lastIndex-1].timeList) {
//            thisprofileVisibleList?.add(View.VISIBLE)
//
//            Log.d(
//                "opop9",
//                "불리언 체크:${chatList.last().user != chatList[chatList.lastIndex - 1].user}\n" +
//                        "시간 체크:${chatList.last().timeList != chatList[chatList.lastIndex - 1].timeList}"
//            )
//        }else
//            thisprofileVisibleList?.add(View.INVISIBLE)
//    }
//val pendingIntent = NavDeepLinkBuilder(context)
//    .setGraph(R.navigation.nav_graph)
//    .setDestination(R.id.android)
//    .setArguments(args)
//    .createPendingIntent()

//@RequiresApi(Build.VERSION_CODES.Q)
//fun addNotification(
//    id: String,
//    personName: String,
//    addMessage: String,
//    @DrawableRes icon: Int,
//    notificationManager:NotificationManager,
//    context:Context
//) {
//
//    val receiverHeader = Icon.createWithResource(context, icon)
//    val receiver = Person.Builder()
//        .setName(personName)
//        .setIcon(receiverHeader).build()
//    val timestamp = System.currentTimeMillis()
//    val style = Notification.MessagingStyle(receiver)
//        .addMessage(
//            Notification.MessagingStyle.Message(
//                addMessage,
//                timestamp,
//                receiver
//            )
//        )
//        .setGroupConversation(true)
//
//
//    val pendingIntent = NavDeepLinkBuilder(context)
//        .setGraph(R.navigation.main)
//        .setDestination(R.id.fragmentFakeKakaoChat)
//        .createPendingIntent()
//
//    val senderHeader =
//        Icon.createWithResource(context, icon)
//
//    val bubbleMetadata = Notification.BubbleMetadata.Builder(pendingIntent, senderHeader)
//        .setDesiredHeight(450)
//        .setAutoExpandBubble(true)
//        .setSuppressNotification(true)
//        .build()
//
//    val notification = Notification.Builder(context, id)
//        .setSmallIcon(icon)
//        .setContentIntent(pendingIntent)
//        .setStyle(style)
//        .setAutoCancel(true)
//        .setBubbleMetadata(bubbleMetadata)
//        .build()
//
//    notificationManager.notify(99, notification)
//}
