package com.canhtv.ee.firebasechatapp.di

import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseDatabaseService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideFirebaseAuthService(auth: FirebaseAuth): FirebaseAuthService = FirebaseAuthService(auth)

    @Provides
    fun provideFirebaseUser(auth: FirebaseAuth) = auth.currentUser

    @Provides
    fun provideFirebaseDatabase(): DatabaseReference = Firebase.database.reference

    @Provides
    fun provideFirebaseDataService(firebaseDatabaseReference: DatabaseReference
    ) = FirebaseDatabaseService(firebaseDatabaseReference)

}