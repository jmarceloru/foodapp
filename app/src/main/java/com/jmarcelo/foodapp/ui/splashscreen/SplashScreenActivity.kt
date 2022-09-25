package com.jmarcelo.foodapp.ui.splashscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.jmarcelo.foodapp.R
import com.jmarcelo.foodapp.ui.login.LoginActivity
import com.jmarcelo.foodapp.ui.onboarding.OnBoardingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screenSplashScreen = installSplashScreen()
        setContentView(R.layout.activity_splash_screen)

        screenSplashScreen.setKeepOnScreenCondition{true}

        val intent = Intent(this,LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}