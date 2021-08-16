package com.canhtv.ee.firebasechatapp.data.local

import android.content.SharedPreferences
import com.canhtv.ee.firebasechatapp.data.models.Session
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class SharePreferencesServices @Inject constructor(
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val sharedPreferences: SharedPreferences
){

    val editor = sharedPreferences.edit()

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

    fun getSession(): Session {
        val session = Session(sharedPreferencesKeys.DEFAULT_INT,
            UserCredential(sharedPreferencesKeys.DEFAULT_STRING, sharedPreferencesKeys.DEFAULT_STRING))
        session.sessionState = sharedPreferences
            .getInt(sharedPreferencesKeys.SESSION_STATE, sharedPreferencesKeys.DEFAULT_INT)
        session.credential.email = sharedPreferences
            .getString(sharedPreferencesKeys.EMAIL, sharedPreferencesKeys.DEFAULT_STRING)
        session.credential.password = sharedPreferences
            .getString(sharedPreferencesKeys.PASSWORD, sharedPreferencesKeys.DEFAULT_STRING)
        return session
    }
}