package com.example.moneymanagementapp.ui.landingpage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.data.local.preference.UserPreference
import com.example.moneymanagementapp.ui.main.MainActivity
import com.github.appintro.AppIntro2
import com.github.appintro.AppIntroFragment

class IntroActivity : AppIntro2() {

    private val userPreference: UserPreference? by lazy{
        UserPreference(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        addSlide(
            AppIntroFragment.createInstance(
                descriptionColorRes = R.color.black,
                titleColorRes = R.color.black,
                imageDrawable = R.drawable.ic_intro1,
                title = "Money Management App",
                description = "You can use this application to help you record your daily income and expenses"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                descriptionColorRes = R.color.black,
                titleColorRes = R.color.black,
                imageDrawable = R.drawable.ic_intro2,
                title = "Graph Feature",
                description = "This application also can show graph based on your income and expenses (monthly)"
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                descriptionColorRes = R.color.black,
                titleColorRes = R.color.black,
                imageDrawable = R.drawable.ic_intro3,
                title = "News Feature",
                description = "You can also read up-to-date news about crypto, stocks, and currencies at news section"
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        val intent = Intent(this@IntroActivity, MainActivity::class.java)
        startActivity(intent)
        userPreference?.alreadyOpenFirstTime = true
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        val intent = Intent(this@IntroActivity, MainActivity::class.java)
        startActivity(intent)
        userPreference?.alreadyOpenFirstTime = true
        finish()
    }
}