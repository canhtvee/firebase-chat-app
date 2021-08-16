package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import com.canhtv.ee.firebasechatapp.data.models.SessionData
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SharePreferencesServices @Inject constructor(
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val sharedPreferences: SharedPreferences
){

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveData(state: Int, userCredential: UserCredential, user: FirebaseUser) {
        with(editor) {
            putInt(sharedPreferencesKeys.SESSION_STATE, state)
            putString(sharedPreferencesKeys.EMAIL, user.email)
            putString(sharedPreferencesKeys.USER_NAME, user.displayName)
            putString(sharedPreferencesKeys.PASSWORD, userCredential.password!!)
            putString(sharedPreferencesKeys.URL_TO_IMAGE, user.photoUrl.toString())
            apply()
        }
    }

    fun getSession(): SessionData {
        val session = SessionData(sharedPreferencesKeys.DEFAULT_STATE,
            UserCredential(sharedPreferencesKeys.DEFAULT_STRING, sharedPreferencesKeys.DEFAULT_STRING))
        session.sessionState = sharedPreferences
            .getInt(sharedPreferencesKeys.SESSION_STATE, sharedPreferencesKeys.DEFAULT_STATE)
        session.credential.email = sharedPreferences
            .getString(sharedPreferencesKeys.EMAIL, sharedPreferencesKeys.DEFAULT_STRING)
        session.credential.password = sharedPreferences
            .getString(sharedPreferencesKeys.PASSWORD, sharedPreferencesKeys.DEFAULT_STRING)
        return session
    }
}