package com.eggy.liveattendanceapp.views.login

data class LoginRequest(
	val password: String? = null,
	val deviceName: String? = null,
	val email: String? = null
)

