package com.canhtv.ee.firebasechatapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesServices
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)

object LocalModule {
    @Provides
    fun provideSharedPreferenceKeys(): SharedPreferencesKeys = SharedPreferencesKeys()

    @Provides
    fun provideSharedPreferences(sharedPreferencesKeys: SharedPreferencesKeys,
                                 activity: FragmentActivity
    ): SharedPreferences = activity.getSharedPreferences(sharedPreferencesKeys.SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Provides
    fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences
    ): SharedPreferences.Editor = sharedPreferences.edit()

    @Provides
    fun provideSharedPreferencesServices(sharedPreferencesKeys: SharedPreferencesKeys,
                                         sharedPreferences: SharedPreferences,
    ) = SharePreferencesServices(sharedPreferencesKeys, sharedPreferences)


}