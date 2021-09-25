package com.canhtv.ee.firebasechatapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.databinding.ActivityMainBinding
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sessionKeys: SessionKeys

    @Inject
    lateinit var sharePreferencesAccess: SharePreferencesAccess

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        // On check user session
//        val mainNav = Navigation.findNavController(binding.navHostFragmentContainer)
//        when (sharePreferencesAccess.getSession().sessionState) {
//            sessionKeys.STATE_SIGN_IN -> mainNav.navigate(R.id.action_global_homeFragment)
//            sessionKeys.STATE_SIGN_OUT -> {
//                mainNav.popBackStack()
//                mainNav.navigate(R.id.action_global_loginFragment)
//            }
//            else -> {
//                mainNav.popBackStack()
//                mainNav.navigate(R.id.action_global_registerFragment)
//            }
//        }
    }
}