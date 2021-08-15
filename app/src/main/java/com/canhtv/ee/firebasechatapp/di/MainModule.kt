package com.canhtv.ee.firebasechatapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.canhtv.ee.firebasechatapp.R
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object MainModule {

    @Provides
    fun provideSharedPreferenceKeys() = SharedPreferencesKeys()

    @Provides
    fun provideMainNavController(activity: FragmentActivity
    ): NavController = activity.findNavController(R.id.nav_host_fragment_container)

    @Provides
    fun provideSharedPreferences(activity: FragmentActivity
    ): SharedPreferences = activity.getSharedPreferences("Session", Context.MODE_PRIVATE)


}