package com.kmmhackernewsapp.android.ui.login.model

sealed class LoginResult

data class LoginResponseModel(
    var username: String,
    var email: String,
) : LoginResult()

data class LoginErrorModel(
    var error: String
) : LoginResult()