package com.example.solitaryhelper.view.dest.user_profile

import android.app.*
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.graphics.drawable.Icon
import android.os.Build
import android.view.View
import android.view.animation.AnimationUtils
import androidx.annotation.RequiresApi
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentWiseSayingBinding
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.activity.WiseSayingActivity
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.utill.toastDebugTest
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


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
        setAnimTextViewStartInfoAnim()
        setAnimTextViewInfoAnimation()

        createNotificationChannel()
        setWiseText()
    }

    private fun FragmentWiseSayingBinding.setAnimTextViewStartInfoAnim() {
        CoroutineScope(Dispatchers.Main).launch {
            val anim = AnimationUtils.loadAnimation(requireContext(), R.anim.wise_textview_start_info)
            textViewStartInfo.startAnimation(anim)
            delay(5000)
            textViewStartInfo.visibility = View. GONE
            textViewInfo.animate().alphaBy(0f).alpha(1.0f).setDuration(1000L).withStartAction {
                textViewWiseList.animate().alphaBy(0f).alpha(1.0f).setDuration(1000L).withEndAction {
                    textViewWiseList.visibility = View.VISIBLE
                    textViewInfo.visibility = View.VISIBLE
                    textViewInfo.startAnimation(anim)
                }
            }


        }

    }

    private fun FragmentWiseSayingBinding.setAnimTextViewInfoAnimation() {
        val textViewInfoAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.textviewinfo)
        textViewInfo.startAnimation(textViewInfoAnim)
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

            if (click) {
                mainTab.visibility = View.GONE
                it.setBackgroundResource(R.drawable.ic_baseline_keyboard_arrow_up_24)
            }else {
                mainTab.visibility = View.VISIBLE
                it.setBackgroundResource(R.drawable.ic_outline_details_24)
            }
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
            Icon.createWithResource(requireContext(), R.drawable.applogo_hood_line_64)
        val receiver = Person.Builder()
            .setName("아싸도우미 명언 바로보기")
            .setIcon(receiverHeader).build()
        val timestamp = System.currentTimeMillis()
        val style = Notification.MessagingStyle(receiver)
            .addMessage(
                Notification.MessagingStyle.Message(
                    "버블을 이용하면 명언을 언제든 볼 수 있어요.",
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

        val notification = Notification.Builder(requireContext(), "bubble")
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
            .setName("아싸도우미 명언 바로보기")
            .setIcon(icon)
            .build()

        val intent = Intent(requireContext(), MainActivity::class.java)
            .setAction(Intent.ACTION_VIEW)

        val shortcutInfo = ShortcutInfo.Builder(requireContext(), "contact")
            .setLongLived(true)
            .setShortLabel("아싸도우미 명언 바로보기")
            .setLongLabel("아싸도우미 명언 바로보기")
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
                "bubble",
                "아싸도우미 명언 바로보기",
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