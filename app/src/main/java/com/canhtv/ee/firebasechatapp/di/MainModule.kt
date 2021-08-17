package com.canhtv.ee.firebasechatapp.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
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
        sharedPreferencesAccess: SharePreferencesAccess,
        sharedPreferencesKeys: SharedPreferencesKeys,
        mainNavController: NavController
    ) = SessionController(sharedPreferencesAccess, sharedPreferencesKeys, mainNavController)

}