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
                title = getString(R.string.text_title_landing_page),
                description = getString(R.string.text_description_landing_page)
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                descriptionColorRes = R.color.black,
                titleColorRes = R.color.black,
                imageDrawable = R.drawable.ic_intro2,
                title = getString(R.string.text_graph),
                description = getString(R.string.text_description_graph)
            )
        )
        addSlide(
            AppIntroFragment.createInstance(
                descriptionColorRes = R.color.black,
                titleColorRes = R.color.black,
                imageDrawable = R.drawable.ic_intro3,
                title = getString(R.string.text_features),
                description = getString(R.string.text_description_features)
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