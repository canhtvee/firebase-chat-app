package com.canhtv.ee.firebasechatapp.data.models

data class UserSession(
    var sessionState : Int,
    var credential: UserCredential?,
)