package com.example.solitaryhelper.view.dest.fake_call

import android.app.AlarmManager
import android.app.NotificationManager
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Log
import android.util.Log.*
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.lifecycle.lifecycleScope
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallBinding
import com.example.solitaryhelper.service.NotificationService
import com.example.solitaryhelper.view.activity.MainActivity
import com.example.solitaryhelper.view.base.BaseFragment
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.example.solitaryhelper.view.utill.toastShort
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentFakeCall : BaseFragment<FragmentFakeCallBinding>(R.layout.fragment_fake_call) {
    companion object {
        const val MUSIC_TEST = 4123
    }

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
        Log.d("checkk","과연?")
        context?.stopService(Intent(requireContext(), NotificationService::class.java))
        setCallScreenDelay {
            setName()
            setCallNotication()
        }
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
                    image = Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
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

    private fun FragmentFakeCallBinding.setCallScreenDelay(notDelay: () -> Unit) {
        d("checkk", "여기 또오나?")
//        val alarmManager = requireContext().getSystemService(ALARM_SERVICE) as AlarmManager
        if (args.callNotication == 2 && PrefCheckRun.getInstance(requireContext()).callDelayCotrol ==
            Contents.CALL_DELAY_NOTICATION_ENABLED_ON
        ) {
            lifecycleScope.launch {
                layoutScreenCover.visibility = View.VISIBLE
                buttonVisibleSetting(View.GONE)

                args.toBundle().putInt("callNotication", 1)
                val arg = args.toBundle()
                PrefCheckRun.getInstance(requireContext()).callDelayCotrol =
                    Contents.CALL_DELAY_NOTICATION_ENABLED_OFF

                Intent(requireContext(), NotificationService::class.java).apply {
                    putExtras(arg)
                    context?.startService(this)
                }
                delay(2000)
                requireActivity().onBackPressed()

            }
        } else
            notDelay()

    }

    private fun FragmentFakeCallBinding.buttonVisibleSetting(visible: Int) {
        buttonCallAgree.visibility = visible
        buttonCallCancle.visibility = visible
    }

    private fun FragmentFakeCallBinding.setButtonCallCancleClickListener() {
        buttonCallCancle.setOnClickListener {
            lifecycleScope.launch {
                context?.toastShort("통화를 취소합니다.")
                startActivity(Intent(requireContext(), MainActivity::class.java))
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