package com.example.solitaryhelper.viewmodel

import android.app.*
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
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
import com.example.solitaryhelper.localdb.entitiy.*
import com.example.solitaryhelper.view.dest.fake_kakao.FragmentFakeKakaoChat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class KaKaoChatViewModel(private val dataSource: KaKaoChatDao) : ViewModel() {
    private val ioScope = CoroutineScope(Dispatchers.IO + Job())

    val chatList: LiveData<List<KaKaoTalkChatData>>
        get() = dataSource.getAllList()
    val chatList1: LiveData<List<KaKaoTalkChatData1>>
        get() = dataSource.getAllList1()
    val chatList2: LiveData<List<KaKaoTalkChatData2>>
        get() = dataSource.getAllList2()
    val chatList3: LiveData<List<KaKaoTalkChatData3>>
        get() = dataSource.getAllList3()
    val chatList4: LiveData<List<KaKaoTalkChatData4>>
        get() = dataSource.getAllList4()
    val chatList5: LiveData<List<KaKaoTalkChatData5>>
        get() = dataSource.getAllList5()
    val chatList6: LiveData<List<KaKaoTalkChatData6>>
        get() = dataSource.getAllList6()
    val chatList7: LiveData<List<KaKaoTalkChatData7>>
        get() = dataSource.getAllList7()
    val chatList8: LiveData<List<KaKaoTalkChatData8>>
        get() = dataSource.getAllList8()
    val chatList9: LiveData<List<KaKaoTalkChatData9>>
        get() = dataSource.getAllList9()
    val chatList10: LiveData<List<KaKaoTalkChatData10>>
        get() = dataSource.getAllList10()
    val chatList11: LiveData<List<KaKaoTalkChatData11>>
        get() = dataSource.getAllList11()
    val chatList12: LiveData<List<KaKaoTalkChatData12>>
        get() = dataSource.getAllList12()
    val chatList13: LiveData<List<KaKaoTalkChatData13>>
        get() = dataSource.getAllList13()
    val chatList14: LiveData<List<KaKaoTalkChatData14>>
        get() = dataSource.getAllList14()
    val chatList15: LiveData<List<KaKaoTalkChatData15>>
        get() = dataSource.getAllList15()
    val chatList16: LiveData<List<KaKaoTalkChatData16>>
        get() = dataSource.getAllList16()
    val chatList17: LiveData<List<KaKaoTalkChatData17>>
        get() = dataSource.getAllList17()
    val chatList18: LiveData<List<KaKaoTalkChatData18>>
        get() = dataSource.getAllList18()
    val chatList19: LiveData<List<KaKaoTalkChatData19>>
        get() = dataSource.getAllList19()

//    private val _chatListPlus = MutableLiveData<KaKaoTalkChatDataCopy>()
//    val chatListPlus:LiveData<KaKaoTalkChatDataCopy>
//    get() = _chatListPlus

    //    fun insertCopyData(data:KaKaoTalkChatDataCopy){
//        _chatListPlus.postValue(data)
//    }

    //챗 리스트 인서트
    fun insert(kakao: KaKaoTalkChatData) {
        ioScope.launch {
            dataSource.insert(kakao)
        }
    }//챗 리스트 인서트
    fun insert1(kakao: KaKaoTalkChatData1) {
        ioScope.launch {
            dataSource.insert1(kakao)
        }
    }//챗 리스트 인서트
    fun insert2(kakao: KaKaoTalkChatData2) {
        ioScope.launch {
            dataSource.insert2(kakao)
        }
    }//챗 리스트 인서트
    fun insert3(kakao: KaKaoTalkChatData3) {
        ioScope.launch {
            dataSource.insert3(kakao)
        }
    }//챗 리스트 인서트
    fun insert4(kakao: KaKaoTalkChatData4) {
        ioScope.launch {
            dataSource.insert4(kakao)
        }
    }//챗 리스트 인서트
    fun insert5(kakao: KaKaoTalkChatData5) {
        ioScope.launch {
            dataSource.insert5(kakao)
        }
    }//챗 리스트 인서트
    fun insert6(kakao: KaKaoTalkChatData6) {
        ioScope.launch {
            dataSource.insert6(kakao)
        }
    }//챗 리스트 인서트
    fun insert7(kakao: KaKaoTalkChatData7) {
        ioScope.launch {
            dataSource.insert7(kakao)
        }
    }//챗 리스트 인서트
    fun insert8(kakao: KaKaoTalkChatData8) {
        ioScope.launch {
            dataSource.insert8(kakao)
        }
    }//챗 리스트 인서트
    fun insert9(kakao: KaKaoTalkChatData9) {
        ioScope.launch {
            dataSource.insert9(kakao)
        }
    }//챗 리스트 인서트
    fun insert10(kakao: KaKaoTalkChatData10) {
        ioScope.launch {
            dataSource.insert10(kakao)
        }
    }//챗 리스트 인서트
    fun insert11(kakao: KaKaoTalkChatData11) {
        ioScope.launch {
            dataSource.insert11(kakao)
        }
    }//챗 리스트 인서트
    fun insert12(kakao: KaKaoTalkChatData12) {
        ioScope.launch {
            dataSource.insert12(kakao)
        }
    }//챗 리스트 인서트
    fun insert13(kakao: KaKaoTalkChatData13) {
        ioScope.launch {
            dataSource.insert13(kakao)
        }
    }//챗 리스트 인서트
    fun insert14(kakao: KaKaoTalkChatData14) {
        ioScope.launch {
            dataSource.insert14(kakao)
        }
    }//챗 리스트 인서트
    fun insert15(kakao: KaKaoTalkChatData15) {
        ioScope.launch {
            dataSource.insert15(kakao)
        }
    }//챗 리스트 인서트
    fun insert16(kakao: KaKaoTalkChatData16) {
        ioScope.launch {
            dataSource.insert16(kakao)
        }
    }//챗 리스트 인서트
    fun insert17(kakao: KaKaoTalkChatData17) {
        ioScope.launch {
            dataSource.insert17(kakao)
        }
    }//챗 리스트 인서트
    fun insert18(kakao: KaKaoTalkChatData18) {
        ioScope.launch {
            dataSource.insert18(kakao)
        }
    }//챗 리스트 인서트
    fun insert19(kakao: KaKaoTalkChatData19) {
        ioScope.launch {
            dataSource.insert19(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert(kakao: List<KaKaoTalkChatData>) {
        ioScope.launch {
            dataSource.allInsert(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert1(kakao: List<KaKaoTalkChatData1>) {
        ioScope.launch {
            dataSource.allInsert1(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert2(kakao: List<KaKaoTalkChatData2>) {
        ioScope.launch {
            dataSource.allInsert2(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert3(kakao: List<KaKaoTalkChatData3>) {
        ioScope.launch {
            dataSource.allInsert3(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert4(kakao: List<KaKaoTalkChatData4>) {
        ioScope.launch {
            dataSource.allInsert4(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert5(kakao: List<KaKaoTalkChatData5>) {
        ioScope.launch {
            dataSource.allInsert5(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert6(kakao: List<KaKaoTalkChatData6>) {
        ioScope.launch {
            dataSource.allInsert6(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert7(kakao: List<KaKaoTalkChatData7>) {
        ioScope.launch {
            dataSource.allInsert7(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert8(kakao: List<KaKaoTalkChatData8>) {
        ioScope.launch {
            dataSource.allInsert8(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert9(kakao: List<KaKaoTalkChatData9>) {
        ioScope.launch {
            dataSource.allInsert9(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert10(kakao: List<KaKaoTalkChatData10>) {
        ioScope.launch {
            dataSource.allInsert10(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert11(kakao: List<KaKaoTalkChatData11>) {
        ioScope.launch {
            dataSource.allInsert11(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert12(kakao: List<KaKaoTalkChatData12>) {
        ioScope.launch {
            dataSource.allInsert12(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert13(kakao: List<KaKaoTalkChatData13>) {
        ioScope.launch {
            dataSource.allInsert13(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert14(kakao: List<KaKaoTalkChatData14>) {
        ioScope.launch {
            dataSource.allInsert14(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert15(kakao: List<KaKaoTalkChatData15>) {
        ioScope.launch {
            dataSource.allInsert15(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert16(kakao: List<KaKaoTalkChatData16>) {
        ioScope.launch {
            dataSource.allInsert16(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert17(kakao: List<KaKaoTalkChatData17>) {
        ioScope.launch {
            dataSource.allInsert17(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert18(kakao: List<KaKaoTalkChatData18>) {
        ioScope.launch {
            dataSource.allInsert18(kakao)
        }
    }
    //챗 리스트 인서트
    fun listInsert19(kakao: List<KaKaoTalkChatData19>) {
        ioScope.launch {
            dataSource.allInsert19(kakao)
        }
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
        bundle: Bundle

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
            .setArguments(bundle)
            .setDestination(R.id.fragmentFakeKakaoChat)
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


