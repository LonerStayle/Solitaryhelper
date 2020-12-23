package com.example.solitaryhelper.view.dest.fake_call

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.support.v4.os.IResultReceiver
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.viewModels
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallBinding
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.messagingStyle
import com.example.solitaryhelper.viewmodel.CallViewModel
import kotlinx.android.synthetic.main.fragment_fake_call.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

class FragmentFakeCall : BaseFragment<FragmentFakeCallBinding>(R.layout.fragment_fake_call) {
    companion object{
        const val MUSIC_TEST = 4123
    }
    private val viewModelCall by viewModels<CallViewModel>()
    private val args by lazy {
        FragmentFakeCallArgs.fromBundle(requireArguments())
    }

    private val vib by lazy { requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator }
    private val sound by lazy { MediaPlayer.create(requireContext(), R.raw.s8_callsound) }

    override fun FragmentFakeCallBinding.setEventListener() {
        setButtonCallCancleClickListener()
        setButtonAgree()
    }

    override fun FragmentFakeCallBinding.setCreateView() {

        setCallScreenDelay()
        setName()
        setCallNotication()
    }

    private fun FragmentFakeCallBinding.setButtonAgree() {
        buttonCallAgree.setOnClickListener {
            vib.cancel()
            sound.stop()
            sound.reset()
            sound.release()

            findNavController().navigate(
                FragmentFakeCallDirections.actionFragmentFakeCallToFragmentFakeCallAgreeScreen(
                    name = textViewName.text.toString(),
                    image =Contents.IMAGE_URL_DEFAULT_FILE_PATH+resources.getIdentifier(
                        "sample0",
                        "drawable",
                        requireActivity().packageName
                    ).toString()
                )
            )
        }
    }

    private fun FragmentFakeCallBinding.setName() {
        text = args.callPartyName
    }


    private fun setCallNotication() {
        if (PrefCheckRun.getInstance(requireContext()).callDelayCotrol ==
            Contents.CALL_DELAY_NOTICATION_ENABLED_OFF
            || args.callNotication == 1
        ) {
            val longArray = longArrayOf(1000L, 300L, 1000L, 300L)
            when (args.callMode) {
                1 -> return
                2 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
                            vib.vibrate(longArray, 0)
                            delay(60000)
                            vib.cancel()

                        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            vib.vibrate(VibrationEffect.createWaveform(longArray, 0))
                            delay(60000)
                            vib.cancel()
                        }
                    }
                }

                3 -> {
                    val manager =
                        requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


                    sound.start()
                    sound.isLooping = true

                    CoroutineScope(Dispatchers.IO).launch {
                        delay(61600)
                        sound.stop()
                        sound.reset()
                        sound.release()

                    }

                }
            }
        }
    }

    private fun FragmentFakeCallBinding.setCallScreenDelay() {

        if (args.callNotication == 2 && PrefCheckRun.getInstance(requireContext()).callDelayCotrol ==
            Contents.CALL_DELAY_NOTICATION_ENABLED_ON
        ) {

            layoutScreenCover.visibility = View.VISIBLE
            buttonVisibleSetting(View.GONE)
            val manager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            viewModelCall.clearExistingNotifications(444444, manager)

            args.toBundle().putInt("callNotication", 1)
            val arg = args.toBundle()
            PrefCheckRun.getInstance(requireContext()).callDelayCotrol =
                Contents.CALL_DELAY_NOTICATION_ENABLED_OFF

            CoroutineScope(Dispatchers.IO).launch {
                viewModelCall.basic(
                    manager, requireContext(), arg, R.drawable.applogo_hood_line_64,
                    "전화에 응하시겠습니까?", "몰래 눌러주세요", "Call"
                )
                requireActivity().finishAffinity()

            }
        }

    }

    private fun FragmentFakeCallBinding.buttonVisibleSetting(visible: Int) {
        buttonCallAgree.visibility = visible
        buttonCallCancle.visibility = visible
    }

    private fun FragmentFakeCallBinding.setButtonCallCancleClickListener() {
        buttonCallCancle.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000L)
                requireActivity().finish()
            }
        }
    }

    override fun onPause() {
        if (args.callMode == 3) {

            sound.release()
        }

        vib.cancel()
        super.onPause()
    }

}