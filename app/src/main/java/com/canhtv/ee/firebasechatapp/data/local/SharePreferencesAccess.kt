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

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun putSessionData(sessionState: Int, userCredential: UserCredential, user: FirebaseUser) {
        with(editor) {
            putInt(sharedPrefKeys.SESSION_STATE, sessionState )
            putString(sharedPrefKeys.EMAIL, user.email)
            putString(sharedPrefKeys.USER_NAME, user.displayName)
            putString(sharedPrefKeys.PASSWORD, userCredential.password!!)
            putString(sharedPrefKeys.URL_TO_IMAGE, user.photoUrl.toString())
            apply()
        }
    }

    fun getUserSession(): UserSession {
        val sessionState = getInt(sharedPrefKeys.SESSION_STATE)
        val email = getString(sharedPrefKeys.EMAIL)
        val password = getString(sharedPrefKeys.PASSWORD)
        Log.d("TAG SharedPrefAccess", "UserCredential $sessionState $email $password")
        return UserSession(sessionState, UserCredential(email, password))
    }

    private fun getInt(key: String) = sharedPreferences
        .getInt(key, sharedPrefKeys.DEFAULT_STATE)

    private fun getString(key: String) = sharedPreferences
        .getString(key, sharedPrefKeys.DEFAULT_STRING)

}