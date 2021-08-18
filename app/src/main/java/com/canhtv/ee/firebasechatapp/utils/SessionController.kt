package com.canhtv.ee.firebasechatapp.utils

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import javax.inject.Inject

class SessionController @Inject constructor(
    private val sharedPreferencesKeys: SharedPreferencesKeys,
){
    fun checkSession(session: UserSession, mainNavController: NavController) {
        when (session.sessionState) {
            sharedPreferencesKeys.SESSION_STATE_SIGN_IN -> mainNavController.navigate(R.id.action_global_homeFragment)
        }
    }
}