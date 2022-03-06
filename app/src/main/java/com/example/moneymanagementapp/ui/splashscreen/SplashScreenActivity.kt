package com.example.moneymanagementapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import com.example.moneymanagementapp.R
import com.example.moneymanagementapp.data.local.preference.UserPreference
import com.example.moneymanagementapp.ui.landingpage.IntroActivity
import com.example.moneymanagementapp.ui.main.MainActivity

class SplashScreenActivity : AppCompatActivity() {

    private val userPreference: UserPreference by lazy {
        UserPreference(this)
    }

    private val timer: CountDownTimer by lazy {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(p0: Long) {
                //do nothing
            }

            override fun onFinish() {
                if (userPreference.alreadyOpenFirstTime) {
                    val intent = Intent(this@SplashScreenActivity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    val intent = Intent(this@SplashScreenActivity, IntroActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        setSplashScreenTimer()
    }

    private fun setSplashScreenTimer() {
        timer.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        timer.cancel()
    }
}