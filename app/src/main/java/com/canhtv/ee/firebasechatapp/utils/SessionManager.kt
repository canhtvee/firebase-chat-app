package com.canhtv.ee.firebasechatapp.utils

import android.content.SharedPreferences
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import javax.inject.Inject

class SessionManager @Inject constructor(
    val sharedPreferencesKeys: SharedPreferencesKeys,
    val sharedPreferences: SharedPreferences,
    val mainNavController: NavController
){

    fun checkSession() {
        val sessionState = sharedPreferences
            .getInt(sharedPreferencesKeys.SESSION_STATE, sharedPreferencesKeys.DEFAULT_INT)
        when (sessionState) {
            0 -> mainNavController.navigate(R.id.action_global_registerFragment)
            1 -> mainNavController.navigate(R.id.action_global_loginFragment)
            else -> mainNavController.navigate(R.id.action_global_homeFragment)
        }
    }
}