package com.canhtv.ee.firebasechatapp.di

import com.canhtv.ee.firebasechatapp.data.remote.FirebaseDatabaseServices
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseUserManager
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object NetModule {

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    fun provideFirebaseDatabase(): DatabaseReference = Firebase.database.reference

    @Provides
    fun provideFirebaseUser(firebaseAuth: FirebaseAuth) = firebaseAuth.currentUser

    @Provides
    fun provideFirebaseUsersManager(
        sessionKeys: SessionKeys,
        firebaseAuth: FirebaseAuth,
        databaseReference: DatabaseReference,
    ) = FirebaseUserManager(sessionKeys,firebaseAuth, databaseReference)

    @Provides
    fun provideFirebaseDatabaseServices(
        firebaseAuth: FirebaseAuth,
        firebaseDatabaseReference: DatabaseReference) = FirebaseDatabaseServices(firebaseAuth, firebaseDatabaseReference)
}