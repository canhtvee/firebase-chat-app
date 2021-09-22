package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import javax.inject.Inject

class SharePreferencesAccess @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val sessionKeys: SessionKeys,
){
    fun putSession(userSession: UserSession) {
        with(sharedPreferences.edit()) {
            putInt(sessionKeys.SESSION_STATE, userSession.sessionState )
            putString(sessionKeys.EMAIL, userSession.credential!!.email)
            putString(sessionKeys.PASSWORD, userSession.credential!!.password!!)
            apply()
        }
    }

    fun getSession(): UserSession {
        val sessionState = getInt(sessionKeys.SESSION_STATE)
        val email = getString(sessionKeys.EMAIL)
        val password = getString(sessionKeys.PASSWORD)
        return UserSession(sessionState, UserCredential(email, password))
    }

    fun putInt(key: String, value: Int) {
        sharedPreferences.edit().putInt(key, value).apply()
    }

    private fun getInt(key: String) = sharedPreferences
        .getInt(key, sessionKeys.DEFAULT_STATE)

    private fun getString(key: String) = sharedPreferences
        .getString(key, sessionKeys.DEFAULT_STRING)

}