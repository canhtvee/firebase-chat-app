package com.canhtv.ee.firebasechatapp.data.remote

import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.utils.Result
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseUsersManager @Inject constructor(
    private val auth: FirebaseAuth,
    private val firebaseDatabaseReference: DatabaseReference
) : BaseFirebaseServices() {

    suspend fun registerNewUser(userProfile: UserProfile, userCredential: UserCredential): Result<String> {
        return when (val authResult = getTaskResult { auth.createUserWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }) {
            is Result.Success -> {
                when (val writeResult = writeUser(auth.currentUser!!, userProfile)) {
                    is Result.Success -> Result.Success("Register User Done")
                    else -> writeResult
                }
            }
            else -> authResult
        }
    }

    suspend fun signInUser(userCredential: UserCredential): Result<String> {
        return getTaskResult { auth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

    suspend fun writeUser(firebaseUser: FirebaseUser, userProfile: UserProfile): Result<String> {
        val hashMap = HashMap<String, String>()
        with(hashMap) {
            put("username", userProfile.username!!)
            put("email", firebaseUser.email!!)
        }
        return getWriteResult { firebaseDatabaseReference.child("users").child(firebaseUser.uid).setValue(hashMap) }
    }

}