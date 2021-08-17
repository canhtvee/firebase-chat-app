package com.canhtv.ee.firebasechatapp.utils

data class SharedPreferencesKeys (
    val SHARED_PREFERENCES : String = "userSession",
    val USER_NAME          : String = "username",
    val USER_ID            : String = "userId",
    val EMAIL              : String = "email",
    val PASSWORD           : String = "password",
    val URL_TO_IMAGE       : String = "urlToImage",
    val SESSION_STATE      : String = "sessionState",
    val DEFAULT_STRING     : String = "Not Registered",
    val DEFAULT_STATE        : Int    = 0,
    val SESSION_STATE_REGISTER  : Int = 1,
    val SESSION_STATE_SIGN_IN  : Int = 2,
    val SESSION_STATE_SIGN_OUT : Int = 3,
)