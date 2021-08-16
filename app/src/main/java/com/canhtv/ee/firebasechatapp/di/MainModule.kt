package com.canhtv.ee.firebasechatapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.pm.PermissionInfoCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesServices
import com.canhtv.ee.firebasechatapp.data.repositories.SessionRepository
import com.canhtv.ee.firebasechatapp.utils.SessionController
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.canhtv.ee.firebasechatapp.viewmodels.SessionViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainNavController(activity: FragmentActivity
    ): NavController = activity.findNavController(R.id.nav_host_fragment_container)

    @Provides
    fun provideSessionViewModel(sessionRepository: SessionRepository
    ) = SessionViewModel(sessionRepository)

    @Provides
    fun provideSessionController(
        activity: FragmentActivity,
        sharedPreferencesKeys: SharedPreferencesKeys,
        mainNavController: NavController,
        sessionViewModel: SessionViewModel,
    ) = SessionController(activity, sharedPreferencesKeys, mainNavController, sessionViewModel)

}