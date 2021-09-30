package com.canhtv.ee.firebasechatapp.utils

data class FirebaseKeys (
    val USER_NAME          : String = "username",
    val USER_AVATAR_URL    : String = "avatarUrl",
    val USER_EMAIL         : String = "email",
    val USER_ONLINE_STATUS : String = "onlineStatus",

    val DB_CHILD_USER      : String = "users",
    val DB_CHILD_MESSAGE   : String = "messages"
)