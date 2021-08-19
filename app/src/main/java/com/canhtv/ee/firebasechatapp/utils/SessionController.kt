package com.canhtv.ee.firebasechatapp.utils

import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import javax.inject.Inject

class SessionController @Inject constructor(
    private val sharedPrefKeys: SharedPreferencesKeys,
    private val mainNavController: NavController
){
    fun checkSession(session: UserSession) {
        when (session.sessionState) {
            sharedPrefKeys.STATE_SIGN_IN -> mainNavController.navigate(R.id.action_global_homeFragment)
        }
    }
}