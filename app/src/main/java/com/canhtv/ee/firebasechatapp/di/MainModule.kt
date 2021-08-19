package com.canhtv.ee.firebasechatapp.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
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

    // Call this function in the fragment that is a direct child of the NavHost with result in crash
    @Provides
    fun provideMainNavController(activity: FragmentActivity
    ): NavController = activity.findNavController(R.id.nav_host_fragment_container)

    @Provides
    fun provideSessionRepository(firebaseAuthService: FirebaseAuthService,
                                 sharedPreferencesKeys: SharedPreferencesKeys,
                                 sharePreferencesAccess: SharePreferencesAccess
    ) = SessionRepository(firebaseAuthService, sharedPreferencesKeys, sharePreferencesAccess)

    @Provides
    fun provideSessionViewModel(sessionRepository: SessionRepository
    ) = SessionViewModel(sessionRepository)

    @Provides
    fun provideSessionController(
        sharedPreferencesKeys: SharedPreferencesKeys,
        mainNavController: NavController
    ) = SessionController(sharedPreferencesKeys, mainNavController)

}