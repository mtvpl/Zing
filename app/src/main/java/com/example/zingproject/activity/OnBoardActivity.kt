package com.example.zingproject.activity

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.akexorcist.localizationactivity.ui.LocalizationActivity
import com.example.zingproject.activity.adapters.OnBoardingItemAdapter
import com.example.zingproject.R
import com.example.zingproject.model.OnBoardingItem
import com.google.android.material.button.MaterialButton

class OnBoardActivity : LocalizationActivity() {

    private lateinit var onBoardingItemAdapter: OnBoardingItemAdapter
    private lateinit var indicatorsContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboard)
        setOnBoardItems()
        setUpIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnBoardItems() {
        onBoardingItemAdapter = OnBoardingItemAdapter(
            listOf(
                OnBoardingItem(
                    R.drawable.ic_baseline_home_24, title = "This is title 1",
                    description = "This is description 1"
                ),
                OnBoardingItem(
                    R.drawable.ic_baseline_home_24, title = "This is title 2",
                    description = "This is description 2"
                ),
                OnBoardingItem(
                    R.drawable.ic_baseline_home_24, title = "This is title 3",
                    description = "This is description 3"
                )
            )
        )
        val onBoardingViewPager = findViewById<ViewPager2>(R.id.onBoardViewPager)
        onBoardingViewPager.adapter = onBoardingItemAdapter
        onBoardingViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onBoardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER

        findViewById<TextView>(R.id.textSkip).setOnClickListener {
            navigateToHome()
        }

        findViewById<MaterialButton>(R.id.btn_get_started).setOnClickListener {
            navigateToHome()
        }

        findViewById<ImageView>(R.id.image_next).setOnClickListener {
            if (onBoardingViewPager.currentItem + 1 < onBoardingItemAdapter.itemCount) {
                onBoardingViewPager.currentItem += 1
            } else {
                navigateToHome()
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(
            intent
        )
    }


    private fun setUpIndicators() {
        indicatorsContainer = findViewById(R.id.indicators_container)
        val indicators = arrayOfNulls<ImageView>(onBoardingItemAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(0, 0, 0, 0)

        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )

                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }

    }

    private fun setCurrentIndicator(position: Int) {
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorsContainer.getChildAt(i) as ImageView

            if (i == position) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active_bg
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive_bg
                    )
                )
            }
        }
    }
}