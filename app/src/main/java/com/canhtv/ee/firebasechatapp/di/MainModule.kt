package com.canhtv.ee.firebasechatapp.di

import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideMainNavController(activity: FragmentActivity
    ): NavController = activity.findNavController(R.id.nav_host_fragment_container)
}