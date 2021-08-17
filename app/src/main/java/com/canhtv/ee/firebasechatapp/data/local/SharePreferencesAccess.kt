package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SharePreferencesAccess @Inject constructor(
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val sharedPreferences: SharedPreferences
){

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun putSessionData(sessionState: Int, userCredential: UserCredential, user: FirebaseUser) {
        with(editor) {
            putInt(sharedPreferencesKeys.SESSION_STATE, sessionState )
            putString(sharedPreferencesKeys.EMAIL, user.email)
            putString(sharedPreferencesKeys.USER_NAME, user.displayName)
            putString(sharedPreferencesKeys.PASSWORD, userCredential.password!!)
            putString(sharedPreferencesKeys.URL_TO_IMAGE, user.photoUrl.toString())
            apply()
        }
    }

    fun getUserSession(): UserSession {
        val sessionState = getInt(sharedPreferencesKeys.SESSION_STATE)
        val email = getString(sharedPreferencesKeys.EMAIL)
        val password = getString(sharedPreferencesKeys.PASSWORD)
        return UserSession(sessionState, UserCredential(email, password))
    }

    private fun getInt(key: String) = sharedPreferences
        .getInt(key, sharedPreferencesKeys.DEFAULT_STATE)

    private fun getString(key: String) = sharedPreferences
        .getString(key, sharedPreferencesKeys.DEFAULT_STRING)

}