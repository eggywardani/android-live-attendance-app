package com.eggy.liveattendanceapp.views.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.eggy.liveattendanceapp.R
import com.eggy.liveattendanceapp.views.login.LoginActivity
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        afterDelayGotoLogin()
    }

    private fun afterDelayGotoLogin() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity<LoginActivity>()
            finishAffinity()
        },1200)
    }
}