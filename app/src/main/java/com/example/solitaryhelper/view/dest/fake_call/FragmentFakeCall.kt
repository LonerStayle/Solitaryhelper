package com.example.solitaryhelper.view.dest.fake_call

import android.app.NotificationManager
import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat.getSystemService
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.fragment.findNavController
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.FragmentFakeCallBinding
import com.example.solitaryhelper.view.base.BaseFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FragmentFakeCall : BaseFragment<FragmentFakeCallBinding>(R.layout.fragment_fake_call) {
    private val args by lazy {
        FragmentFakeCallArgs.fromBundle(requireArguments())
    }

    override fun FragmentFakeCallBinding.setEventListener() {
        setButtonCallCancleClickListener()
    }

    override fun FragmentFakeCallBinding.setCreateView() {
        setCallScreenDelay()

    }

    private fun FragmentFakeCallBinding.setCallScreenDelay() {
        if (args.callReservationTime > 1L) {

            val manager =
                requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            viewModelCall.clearExistingNotifications(444444, manager)
            val context = requireContext()
            val arg = args.toBundle()

            CoroutineScope(Dispatchers.IO).launch {
                requireActivity().finishAffinity()
                delay(args.callReservationTime)
                viewModelCall.basic(manager, context, arg)
            }

        }
    }

    private fun FragmentFakeCallBinding.setButtonCallCancleClickListener() {
        buttonCallCancle.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                delay(3000L)
                requireActivity().finish()
            }
        }
    }


}