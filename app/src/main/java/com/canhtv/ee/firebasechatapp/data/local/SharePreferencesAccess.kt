package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SharePreferencesAccess @Inject constructor(
    private val sharedPreferences: SharedPreferences
){

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    val keys = SharedPreferencesKeys()

    fun putSessionData(sessionState: Int, userCredential: UserCredential, user: FirebaseUser) {
        with(editor) {
            putInt(keys.SESSION_STATE, sessionState )
            putString(keys.EMAIL, user.email)
            putString(keys.USER_NAME, user.displayName)
            putString(keys.PASSWORD, userCredential.password!!)
            putString(keys.URL_TO_IMAGE, user.photoUrl.toString())
            apply()
        }
    }

    fun getUserSession(): UserSession {
        val sessionState = getInt(keys.SESSION_STATE)
        val email = getString(keys.EMAIL)
        val password = getString(keys.PASSWORD)
        Log.d("TAG SharedPrefAccess", "UserCredential $sessionState $email $password")
        return UserSession(sessionState, UserCredential(email, password))
    }

    private fun getInt(key: String) = sharedPreferences
        .getInt(key, keys.DEFAULT_STATE)

    private fun getString(key: String) = sharedPreferences
        .getString(key, keys.DEFAULT_STRING)

}