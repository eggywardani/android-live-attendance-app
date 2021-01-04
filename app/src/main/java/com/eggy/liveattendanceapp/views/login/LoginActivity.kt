package com.eggy.liveattendanceapp.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eggy.liveattendanceapp.databinding.ActivityLoginBinding
import com.eggy.liveattendanceapp.views.main.MainActivity
import com.eggy.liveattendanceapp.views.forgotpassword.ForgotPasswordActivity
import org.jetbrains.anko.startActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        onClick()
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            startActivity<MainActivity>()
        }


        binding.btnForgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }
    }
}