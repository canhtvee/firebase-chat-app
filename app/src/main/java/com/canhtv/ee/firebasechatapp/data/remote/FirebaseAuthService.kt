package com.canhtv.ee.firebasechatapp.data.remote

import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
    ) : BaseFirebaseService(){

    suspend fun createUserWithEmailAndPassword(email: String, password: String): Resource<FirebaseUser> {
        return getResult(auth) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener{}
        }
    }
    suspend fun signInWithEmailAndPassword(email: String, password: String): Resource<FirebaseUser> {
        return getResult(auth) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener{}
        }
    }
}