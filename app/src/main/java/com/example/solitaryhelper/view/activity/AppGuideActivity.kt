package com.example.solitaryhelper.view.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.viewpager2.widget.ViewPager2
import com.example.solitaryhelper.R
import com.example.solitaryhelper.databinding.ActivityAppGuideBinding
import com.example.solitaryhelper.view.adapter.AdapterViewPagerAppGuide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.prefs.Preferences


class AppGuideActivity : AppCompatActivity() {


    private lateinit var prefs: SharedPreferences
    private var imageList: Array<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityAppGuideBinding>(
            this, R.layout.activity_app_guide
        )

        binding.checkFirstRun()
    }

    private fun ActivityAppGuideBinding.checkFirstRun() {
        prefs = getSharedPreferences(packageName, MODE_PRIVATE)

        if (prefs.getBoolean("firstrun", true)) {


            CoroutineScope(Dispatchers.Main).launch {
                setAdapter()
                delay(3000)
                imageViewAppLogo.visibility = View.GONE
                textViewLogoText.visibility = View.GONE
                viewPagerAppGuide.visibility = View.VISIBLE
                linerLayoutIndicators.visibility = View.VISIBLE
            }
            prefs.edit().putBoolean("firstrun", false).apply()
        } else {

            CoroutineScope(Dispatchers.Main).launch {
                delay(3000)
                startActivity(Intent(this@AppGuideActivity, MainActivity::class.java))
                finish()
            }
        }
    }

    private fun ActivityAppGuideBinding.setAdapter() {
        imageList = Array(4) { "" }

        for (i in imageList!!.indices) {
            imageList!![i] += (resources.getIdentifier(
                "sample$i", "drawable",
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
                    )).isSelected = true

              if(prevIndicator != -1 ) {
                        findViewById<ImageView>(
                            resources.getIdentifier(
                                "imageView_indicator_${prevIndicator}",
                                "id",
                                packageName
                            )).isSelected = false
                    }
                prevIndicator = position

                when {
                    viewPagerAppGuide.currentItem == imageList!!.lastIndex -> {
                        buttonAppStart.visibility = View.VISIBLE
                        buttonAppStart.setOnClickListener {
                            startActivity(Intent(this@AppGuideActivity, MainActivity::class.java))
                            finish()
                        }
                    }
                    viewPagerAppGuide.currentItem != imageList!!.lastIndex ->{
                        buttonAppStart.visibility = View.GONE
                    }
                }

            }
        })
    }
}
