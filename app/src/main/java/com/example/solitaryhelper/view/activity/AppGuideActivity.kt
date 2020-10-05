package com.example.solitaryhelper.view.activity

import android.content.Intent
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityAppGuideBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerAppGuide
import com.example.solitaryhelper.view.base.BaseActivity
import com.example.solitaryhelper.view.contents.Contents
import com.example.solitaryhelper.view.pref.PrefCheckRun
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AppGuideActivity : BaseActivity<ActivityAppGuideBinding>(R.layout.activity_app_guide) {

    private var imageList: Array<String>? = null
    private val auth by lazy { FirebaseAuth.getInstance(FirebaseApp.initializeApp(this)!!) }

    override fun ActivityAppGuideBinding.setData() {
        checkFirstRun()
        setAnim()
    }
    private fun ActivityAppGuideBinding.setAnim() {
        AnimationUtils.loadAnimation(this@AppGuideActivity, R.anim.splash_anim).let {
            textViewLogoText.alpha = 0f
            imageViewAppLogo.startAnimation(it)
            textViewLogoText.animate().setStartDelay(1500L).alpha(1f).setDuration(800L).start()
        }
    }

    private fun ActivityAppGuideBinding.checkFirstRun() {

        if (!PrefCheckRun.getInstance(this@AppGuideActivity).appGuideFirstRunUserMark) {
            CoroutineScope(Dispatchers.Main).launch {
                setAdapter()
                delay(3000)
                imageViewAppLogo.visibility = View.GONE
                textViewLogoText.visibility = View.GONE
                viewPagerAppGuide.visibility = View.VISIBLE
                linerLayoutIndicators.visibility = View.VISIBLE
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                if (auth.currentUser != null) {
                    delay(3000)
                    startActivity(Intent(this@AppGuideActivity, MainActivity::class.java))
                    finish()
                } else {
//                    delay(3000)
//                    startActivity(Intent(this@AppGuideActivity, LoginActivity::class.java))
//                    finish()
                }
            }
        }
    }

    private fun ActivityAppGuideBinding.setAdapter() {
        imageList = Array(4) { "" }

        for (i in imageList!!.indices) {
            imageList!![i] += (Contents.IMAGE_URL_DEFAULT_FILE_PATH + resources.getIdentifier(
                "guide_sample$i", "drawable",
                packageName
            ).toString())
        }
        viewPagerAppGuide.adapter = AdapterViewPagerAppGuide(imageList!!.toList())

        var prevIndicator = -1
        viewPagerAppGuide.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

                findViewById<ImageView>(
                    resources.getIdentifier(
                        "imageView_indicator_${position}",
                        "id",
                        packageName
                    )
                ).isSelected = true

                if (prevIndicator != -1) {
                    findViewById<ImageView>(
                        resources.getIdentifier(
                            "imageView_indicator_${prevIndicator}",
                            "id",
                            packageName
                        )
                    ).isSelected = false
                }
                prevIndicator = position

                when {
                    viewPagerAppGuide.currentItem == imageList!!.lastIndex -> {
                        buttonAppStart.visibility = View.VISIBLE
                        buttonAppStart.setOnClickListener {
                            if (auth.currentUser != null) {
                                startActivity(
                                    Intent(this@AppGuideActivity,
                                        MainActivity::class.java))
                                finish()
                                PrefCheckRun.getInstance(this@AppGuideActivity).
                                appGuideFirstRunUserMark = true
                            }else{
                                startActivity(
                                    Intent(this@AppGuideActivity,
                                        LoginActivity::class.java))
                                finish()
                            }
                        }
                    }
                    viewPagerAppGuide.currentItem != imageList!!.lastIndex -> {
                        buttonAppStart.visibility = View.GONE
                    }
                }

            }
        })
    }



}
