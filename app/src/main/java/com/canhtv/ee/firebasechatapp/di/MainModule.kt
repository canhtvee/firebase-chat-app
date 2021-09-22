package com.canhtv.ee.firebasechatapp.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseUserManager
import com.canhtv.ee.firebasechatapp.data.repositories.SessionManager
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    // Call this function in the fragment that is a direct child of the NavHost with result in crash
    @Provides
    fun provideMainNavController(activity: FragmentActivity
    ): NavController = activity.findNavController(R.id.nav_host_fragment_container)

    @Provides
    fun provideSessionManager(sessionKeys: SessionKeys,
                              sharePreferencesAccess: SharePreferencesAccess,
                              firebaseUserManager: FirebaseUserManager,)
    = SessionManager(sessionKeys, sharePreferencesAccess, firebaseUserManager,)

    @Provides
    fun provideSessionViewModel(sessionManager: SessionManager
    ) = SessionViewModel(sessionManager)

}