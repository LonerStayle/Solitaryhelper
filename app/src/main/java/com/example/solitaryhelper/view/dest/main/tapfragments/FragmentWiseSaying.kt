package com.example.solitaryhelper.view.dest.main.tapfragments

import android.app.*
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentMainBinding
import com.example.solitaryhelper.databinding.FragmentWiseSayingBinding
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.activity.WiseSayingActivity
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.dest.main.MainFragment
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_fake_sms.*
import java.util.*
import kotlin.random.Random.Default.nextInt


class FragmentWiseSaying : BaseFragment<FragmentWiseSayingBinding>(R.layout.fragment_wise_saying) {

    private val wiseList by lazy {
        resources.getStringArray(R.array.wiseList)
    }
    private val stack = Stack<String>()

    private val shortcutManager by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().getSystemService<ShortcutManager>(ShortcutManager::class.java)
        } else {
            TODO("VERSION.SDK_INT < N_MR1")
        }
    }

    private val notificationManager by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            requireActivity().getSystemService<NotificationManager>(NotificationManager::class.java)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
    }

    override fun FragmentWiseSayingBinding.setEventListener() {

        buttonBubbleDataUseClickListener()
        buttonMainTabVisible()
        buttonPrevClickListener()
        buttonNextClickListener()

    }



    override fun FragmentWiseSayingBinding.setCreateView() {
        createNotificationChannel()
        setWiseText()
    }


    private fun FragmentWiseSayingBinding.buttonPrevClickListener() {
       buttonPriv.setOnClickListener {
           wiseText = if (stack.isEmpty())
               wiseList[Random().nextInt(wiseList.size)]
           else
               stack.pop()
       }

    }

    private fun FragmentWiseSayingBinding.setWiseText() {
        wiseText = wiseList[Random().nextInt(wiseList.size)]
    }

    private fun FragmentWiseSayingBinding.buttonNextClickListener() {
        buttonNext.setOnClickListener {
            stack.push(wiseText)
            wiseText = wiseList[Random().nextInt(wiseList.size)]
        }

    }

    private fun FragmentWiseSayingBinding.buttonMainTabVisible() {
        var click = false
        buttonMainTabVisible.setOnClickListener {
            click = !click
            val mainTab = requireActivity().findViewById<TabLayout>(R.id.tabLayout_main)

            if (click)
                mainTab.visibility = View.GONE
            else
                mainTab.visibility = View.VISIBLE
        }
    }

    private fun FragmentWiseSayingBinding.buttonBubbleDataUseClickListener() {
        buttonBubbleDataUse.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                addShortcutToNotification()
            } else {
                context?.toastDebugTest("이 기능은 안드로이드11 이상에서만 사용하실 수 있습니다. ")
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun addShortcutToNotification() {

        val receiverHeader =
            Icon.createWithResource(requireContext(), R.drawable.ic_launcher_background)
        val receiver = Person.Builder()
            .setName("얌얌")
            .setIcon(receiverHeader).build()
        val timestamp = System.currentTimeMillis()
        val style = Notification.MessagingStyle(receiver)
            .addMessage(
                Notification.MessagingStyle.Message(
                    "얌",
                    timestamp,
                    receiver
                )
            )
            .setGroupConversation(true)

        val pendingIntent = PendingIntent.getActivity(
            requireContext(),
            0,
            Intent(requireContext(), WiseSayingActivity::class.java)
                .setAction(Intent.ACTION_VIEW),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        createShortcut()
        val senderContact = "contact"
        val senderHeader =
            Icon.createWithResource(requireContext(), R.drawable.ic_launcher_background)

        val bubbleMetadata = Notification.BubbleMetadata.Builder(pendingIntent, senderHeader)
            .setDesiredHeight(450)
            .setAutoExpandBubble(true)
            .setSuppressNotification(true)
            .build()

        val notification = Notification.Builder(requireContext(), "notification_channel_id")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentIntent(pendingIntent)
            .setStyle(style)
            .setAutoCancel(true)
            .setShortcutId(senderContact)
            .setBubbleMetadata(bubbleMetadata)
            .build()

        notificationManager.notify(99, notification)
    }

    @RequiresApi(Build.VERSION_CODES.R)
    private fun createShortcut() {

        val icon = Icon.createWithResource(requireContext(), R.drawable.ic_launcher_background)

        val person = Person.Builder()
            .setName("얌")
            .setIcon(icon)
            .build()

        val intent = Intent(requireContext(), MainActivity::class.java)
            .setAction(Intent.ACTION_VIEW)

        val shortcutInfo = ShortcutInfo.Builder(requireContext(), "contact")
            .setLongLived(true)
            .setShortLabel("contact")
            .setLongLabel("라랄랄")
            .setIcon(icon)
            .setPerson(person)
            .setCategories(setOf("com.jacob.material.bubbles.category.TEXT_SHARE_TARGET"))
            .setIntent(intent)
            .build()

        shortcutManager.pushDynamicShortcut(shortcutInfo)
    }


    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(
                "notification_channel_id",
                "name",
                importance
            ).apply {
                description = "descriptionText"
            }
            notificationManager.createNotificationChannel(channel)

        } else {
            return
        }
    }
}