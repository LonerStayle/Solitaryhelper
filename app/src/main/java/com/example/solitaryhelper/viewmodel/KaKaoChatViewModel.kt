package com.example.solitaryhelper.viewmodel

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.graphics.drawable.IconCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDeepLinkBuilder
import com.example.solitaryhelper.R
import com.example.solitaryhelper.view.dataclass.KaKaoTalkChatData
import com.example.solitaryhelper.view.dest.fake_kakao.FragmentFakeKakaoChat
import com.example.solitaryhelper.view.dialog.DialogCustom


class KaKaoChatViewModel() : ViewModel() {


    val myChatText = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText2 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText3 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText4 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText5 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText6 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText7 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText8 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText9 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText10 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText11 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText12 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText13 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText14 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText15 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText16 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText17 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText18 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText19 = MutableLiveData<MutableList<KaKaoTalkChatData>>()
    val myChatText20 = MutableLiveData<MutableList<KaKaoTalkChatData>>()


    // 전체 리스트 인서트
    fun insertAllList(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText.postValue(myChatList)
    }

    fun insertAllList2(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText2.postValue(myChatList)
    }

    fun insertAllList3(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText3.postValue(myChatList)
    }

    fun insertAllList4(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText4.postValue(myChatList)
    }

    fun insertAllList5(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText5.postValue(myChatList)
    }

    fun insertAllList6(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText6.postValue(myChatList)
    }

    fun insertAllList7(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText7.postValue(myChatList)
    }

    fun insertAllList8(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText8.postValue(myChatList)
    }

    fun insertAllList9(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText9.postValue(myChatList)
    }

    fun insertAllList10(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText10.postValue(myChatList)
    }

    fun insertAllList11(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText11.postValue(myChatList)
    }

    fun insertAllList12(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText12.postValue(myChatList)
    }

    fun insertAllList13(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText13.postValue(myChatList)
    }

    fun insertAllList14(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText14.postValue(myChatList)
    }

    fun insertAllList15(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText15.postValue(myChatList)
    }

    fun insertAllList16(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText16.postValue(myChatList)
    }

    fun insertAllList17(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText17.postValue(myChatList)
    }

    fun insertAllList18(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText18.postValue(myChatList)
    }

    fun insertAllList19(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText19.postValue(myChatList)
    }

    fun insertAllList20(myChatList: MutableList<KaKaoTalkChatData>) {
        myChatText20.postValue(myChatList)

    }
    //하나씩 인서트

    fun insertItemAdd(kakao: KaKaoTalkChatData) {
        myChatText.value?.add(kakao)
        myChatText.value = myChatText.value
    }

    fun insertItemAdd2(kakao: KaKaoTalkChatData) {
        myChatText2.value?.add(kakao)
        myChatText2.value = myChatText2.value
    }

    fun insertItemAdd3(kakao: KaKaoTalkChatData) {
        myChatText3.value?.add(kakao)
        myChatText3.value = myChatText3.value
    }

    fun insertItemAdd4(kakao: KaKaoTalkChatData) {
        myChatText4.value?.add(kakao)
        myChatText4.value = myChatText4.value
    }

    fun insertItemAdd5(kakao: KaKaoTalkChatData) {
        myChatText5.value?.add(kakao)
        myChatText5.value = myChatText5.value
    }

    fun insertItemAdd6(kakao: KaKaoTalkChatData) {
        myChatText6.value?.add(kakao)
        myChatText6.value = myChatText6.value
    }

    fun insertItemAdd7(kakao: KaKaoTalkChatData) {
        myChatText7.value?.add(kakao)
        myChatText7.value = myChatText7.value
    }

    fun insertItemAdd8(kakao: KaKaoTalkChatData) {
        myChatText8.value?.add(kakao)
        myChatText8.value = myChatText8.value
    }

    fun insertItemAdd9(kakao: KaKaoTalkChatData) {
        myChatText9.value?.add(kakao)
        myChatText9.value = myChatText9.value
    }

    fun insertItemAdd10(kakao: KaKaoTalkChatData) {
        myChatText10.value?.add(kakao)
        myChatText10.value = myChatText10.value
    }

    fun insertItemAdd11(kakao: KaKaoTalkChatData) {
        myChatText11.value?.add(kakao)
        myChatText11.value = myChatText11.value
    }

    fun insertItemAdd12(kakao: KaKaoTalkChatData) {
        myChatText12.value?.add(kakao)
        myChatText12.value = myChatText12.value
    }

    fun insertItemAdd13(kakao: KaKaoTalkChatData) {
        myChatText13.value?.add(kakao)
        myChatText13.value = myChatText13.value
    }

    fun insertItemAdd14(kakao: KaKaoTalkChatData) {
        myChatText14.value?.add(kakao)
        myChatText14.value = myChatText14.value
    }

    fun insertItemAdd15(kakao: KaKaoTalkChatData) {
        myChatText15.value?.add(kakao)
        myChatText15.value = myChatText15.value
    }

    fun insertItemAdd16(kakao: KaKaoTalkChatData) {
        myChatText16.value?.add(kakao)
        myChatText16.value = myChatText16.value
    }

    fun insertItemAdd17(kakao: KaKaoTalkChatData) {
        myChatText17.value?.add(kakao)
        myChatText17.value = myChatText17.value
    }

    fun insertItemAdd18(kakao: KaKaoTalkChatData) {
        myChatText18.value?.add(kakao)
        myChatText18.value = myChatText18.value
    }

    fun insertItemAdd19(kakao: KaKaoTalkChatData) {
        myChatText19.value?.add(kakao)
        myChatText19.value = myChatText19.value
    }

    fun insertItemAdd20(kakao: KaKaoTalkChatData) {
        myChatText20.value?.add(kakao)
        myChatText20.value = myChatText20.value
    }


    fun clearExistingNotifications(notificationId: Int, manager: NotificationManager) {
        manager.cancel(notificationId)
    }

    fun messagingStyle(
        @DrawableRes icon: Int,
        context: Context,
        manager: NotificationManager,
        name: String,
        message: String,
        activityContext: Context


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
