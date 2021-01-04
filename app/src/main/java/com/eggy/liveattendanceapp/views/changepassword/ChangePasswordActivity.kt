package com.eggy.liveattendanceapp.views.changepassword

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.eggy.liveattendanceapp.databinding.ActivityChangePasswordBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityChangePasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChangePasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)


        init()
        onClick()
    }

    private fun init(){
        binding.tbChangePassword.setNavigationOnClickListener {
            finish()
        }
    }
    private fun onClick() {
        setSupportActionBar(binding.tbChangePassword)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}