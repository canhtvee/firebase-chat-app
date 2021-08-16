package com.canhtv.ee.firebasechatapp.utils

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import javax.inject.Inject

class SessionController @Inject constructor(
    private val activity: FragmentActivity,
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val mainNavController: NavController,
    private val sessionViewModel: SessionViewModel,
){
    fun checkSession() {
        sessionViewModel.sessionData.observe(activity, {
            when(it.sessionState) {
                sharedPreferencesKeys.DEFAULT_STATE -> mainNavController.navigate(R.id.action_global_registerFragment)
                sharedPreferencesKeys.SESSION_STATE_SIGN_OUT -> mainNavController.navigate(R.id.action_global_loginFragment)
                sharedPreferencesKeys.SESSION_STATE_SIGN_IN -> mainNavController.navigate(R.id.action_global_homeFragment)
            }
        })
    }
}