package com.canhtv.ee.firebasechatapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.remote.BaseFirebaseService
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.databinding.ActivityMainBinding
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var sharedPreferencesKeys: SharedPreferencesKeys

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
        val mainNav = Navigation.findNavController(binding.navHostFragmentContainer)
        when (sharePreferencesAccess.getUserSession().sessionState) {
            sharedPreferencesKeys.STATE_SIGN_IN -> mainNav.navigate(R.id.action_global_homeFragment)
            sharedPreferencesKeys.STATE_SIGN_OUT -> {
                mainNav.popBackStack()
                mainNav.navigate(R.id.action_global_loginFragment)
            }
            else -> {
                mainNav.popBackStack()
                mainNav.navigate(R.id.action_global_registerFragment)
            }
        }
    }
}