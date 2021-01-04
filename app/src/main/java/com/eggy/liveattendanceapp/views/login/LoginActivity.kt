package com.eggy.liveattendanceapp.views.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import com.eggy.liveattendanceapp.databinding.ActivityLoginBinding
import com.eggy.liveattendanceapp.dialog.MyDialog
import com.eggy.liveattendanceapp.hawkstorage.HawkStorage
import com.eggy.liveattendanceapp.model.LoginResponse
import com.eggy.liveattendanceapp.networking.ApiServices
import com.eggy.liveattendanceapp.networking.RetrofitClient
import com.eggy.liveattendanceapp.views.main.MainActivity
import com.eggy.liveattendanceapp.views.forgotpassword.ForgotPasswordActivity
import com.google.gson.Gson
import okhttp3.ResponseBody
import org.jetbrains.anko.startActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Converter
import retrofit2.Response
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    private lateinit var binding:ActivityLoginBinding
    companion object{
        private val TAG = LoginActivity::class.java.simpleName
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


        onClick()
    }

    private fun onClick() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etEmailLogin.text.toString()
            val password = binding.etPasswordLogin.text.toString()
            if (isFormValid(email, password)){
                loginToServer(email, password)
            }

        }


        binding.btnForgotPassword.setOnClickListener {
            startActivity<ForgotPasswordActivity>()
        }
    }

    private fun loginToServer(email: String, password: String) {
        val loginRequest = LoginRequest(password, "mobile", email)
        val loginRequestString = Gson().toJson(loginRequest)

        MyDialog.showProgressDialog(this)
        ApiServices.getLiveAttendanceServices()
            .loginRequest(loginRequestString)
            .enqueue(object : Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    MyDialog.hideDialog()
                    if (response.isSuccessful){
                        val user = response.body()?.user
                        val token = response.body()?.meta?.token

                        if (user != null && token != null){
                            HawkStorage.instance(this@LoginActivity).setUser(user)
                            HawkStorage.instance(this@LoginActivity).setToken(token)
                            goToMain()

                        }

                    }else{
                        val errorConverter:Converter<ResponseBody, LoginResponse> =
                            RetrofitClient.getClient()
                                .responseBodyConverter(
                                    LoginResponse::class.java,
                                    arrayOfNulls<Annotation>(0)
                                )

                        var errorResponse:LoginResponse?
                        try {
                            response.errorBody()?.let {
                                errorResponse = errorConverter.convert(it)
                                MyDialog.dynamicDialog(this@LoginActivity, "Failed", errorResponse?.message.toString() )
                            }
                        }catch (e:IOException){
                            e.printStackTrace()
                        }
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    MyDialog.hideDialog()
                    Log.e(TAG, "Error : ${t.message}")
                }

            })
    }

    private fun goToMain() {
        startActivity<MainActivity>()
        finishAffinity()
    }

    private fun isFormValid(email: String, password: String): Boolean {
        if (email.isEmpty()){
            binding.etEmailLogin.error = "Please field your email"
            binding.etEmailLogin.requestFocus()
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            binding.etEmailLogin.error = "Please use valid email"
            binding.etEmailLogin.requestFocus()
        }else if (password.isEmpty()){
            binding.etEmailLogin.error = null
            binding.etPasswordLogin.error = "Please field your password"
            binding.etPasswordLogin.requestFocus()
        }else{
            binding.etEmailLogin.error = null
            binding.etPasswordLogin.error = null
            return true
        }
        return false
    }
}