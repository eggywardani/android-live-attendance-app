package com.eggy.liveattendanceapp.views.forgotpassword

import com.google.gson.annotations.SerializedName

data class ForgotPasswordRequest(

	@field:SerializedName("email")
	val email: String? = null
)
