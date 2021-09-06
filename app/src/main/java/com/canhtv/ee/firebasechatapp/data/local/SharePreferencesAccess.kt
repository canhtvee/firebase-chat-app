package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SharePreferencesAccess @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sharedPrefKeys: SharedPreferencesKeys
){
    fun putUserSession(userSession: UserSession) {
        with(sharedPreferences.edit()) {
            putInt(sharedPrefKeys.SESSION_STATE, userSession.sessionState )
            putString(sharedPrefKeys.EMAIL, userSession.credential.email)
            putString(sharedPrefKeys.PASSWORD, userSession.credential.password!!)
            apply()
        }
    }

    fun getUserSession(): UserSession {
        val sessionState = getInt(sharedPrefKeys.SESSION_STATE)
        val email = getString(sharedPrefKeys.EMAIL)
        val password = getString(sharedPrefKeys.PASSWORD)
        return UserSession(sessionState, UserCredential(email, password))
    }

    private fun getInt(key: String) = sharedPreferences
        .getInt(key, sharedPrefKeys.DEFAULT_STATE)

    private fun getString(key: String) = sharedPreferences
        .getString(key, sharedPrefKeys.DEFAULT_STRING)

}