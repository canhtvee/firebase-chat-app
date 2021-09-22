package com.canhtv.ee.firebasechatapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)

object LocalModule {
    @Provides
    fun provideSessionKeys(): SessionKeys = SessionKeys()

    @Provides
    fun provideSharedPreferences(sessionKeys: SessionKeys,
                                 activity: FragmentActivity
    ): SharedPreferences = activity.getSharedPreferences(sessionKeys.SHARED_PREFERENCES, Context.MODE_PRIVATE)

    @Provides
    fun provideSharedPreferencesEditor(sharedPreferences: SharedPreferences
    ): SharedPreferences.Editor = sharedPreferences.edit()

    @Provides
    fun provideSharedPreferencesAccess(sharedPreferences: SharedPreferences,
                                       sessionKeys: SessionKeys
    ) = SharePreferencesAccess(sharedPreferences, sessionKeys)

}