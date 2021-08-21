package com.canhtv.ee.firebasechatapp.data.remote

import android.util.Log
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
    ) : BaseFirebaseService(){

    suspend fun createUserWithEmailAndPassword(userCredential: UserCredential): Resource<FirebaseUser> {
        return getResult(auth)
        { auth.createUserWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

    suspend fun signInWithEmailAndPassword(userCredential: UserCredential): Resource<FirebaseUser> {

        tryGetResult(userCredential)
        return getResult(auth)
        { auth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

    fun getFirebaseUser() = auth.currentUser


    private fun tryGetResult(
        userCredential: UserCredential
    ): Resource<FirebaseUser> {
        Log.d("TAG CALL", " call tryGetResult")
        try {
            val result = auth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!).result
            Log.d("CALL", "tryGetResult ${result?.user.toString()}")

            return Resource.Loading<FirebaseUser>()
        } catch (e: Exception) {
            e.toString()
            return Resource.Error(e.message ?: e.toString())
        }
    }

}

