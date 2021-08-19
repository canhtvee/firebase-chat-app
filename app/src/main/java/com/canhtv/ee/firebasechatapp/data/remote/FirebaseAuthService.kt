package com.canhtv.ee.firebasechatapp.data.remote

import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
    ) : BaseFirebaseService(){

    suspend fun createUserWithEmailAndPassword(userCredential: UserCredential): Resource<FirebaseUser> {
        return getResult(auth
        ) { auth.createUserWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

    suspend fun signInWithEmailAndPassword(userCredential: UserCredential): Resource<FirebaseUser> {
        return getResult(auth
        ) { auth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }
}