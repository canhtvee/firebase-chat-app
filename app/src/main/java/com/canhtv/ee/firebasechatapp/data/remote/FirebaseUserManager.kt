package com.canhtv.ee.firebasechatapp.data.remote

import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.Result
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import com.canhtv.ee.firebasechatapp.utils.UserProfileKeys
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseUserManager @Inject constructor(
    private val sessionKeys: SessionKeys,
    private val firebaseAuth: FirebaseAuth,
    private val databaseReference: DatabaseReference,
) : BaseFirebaseServices() {
    private val userProfileKeys = UserProfileKeys()

    suspend fun registerNewUser(userCredential: UserCredential, userProfile: UserProfile,): Result<UserSession> {
        return when (val authResult = getTaskResult { firebaseAuth.createUserWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }) {
            is Result.Success -> {
                when (val writeResult = writeUser(firebaseAuth.currentUser!!, userProfile)) {
                    is Result.Success -> Result.Success(UserSession(sessionKeys.STATE_SIGN_IN, userCredential))
                    is Result.Error -> Result.Error(writeResult.message)
                }
            }
            is Result.Error -> Result.Error(authResult.message)
        }
    }

    suspend fun signInUser(userCredential: UserCredential): Result<UserSession> {
        return when (val authResult = getTaskResult { firebaseAuth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }) {
            is Result.Success -> Result.Success(UserSession(sessionKeys.STATE_SIGN_IN, userCredential))
            is Result.Error -> Result.Error(authResult.message)
        }
    }

    fun signOutUser(): Result<UserSession> {
        return when (val authResult = getSignOutResult(firebaseAuth)) {
            is Result.Success -> Result.Success(UserSession(sessionKeys.STATE_SIGN_OUT, null))
            is Result.Error -> Result.Error(authResult.message)
        }
    }

    suspend fun setOnlineStatus(firebaseUser: FirebaseUser, status: String)
    = getTaskResult { databaseReference.child("users").child(firebaseUser.uid).
    setValue(HashMap<String, Any>().put(userProfileKeys.IS_ONLINE, status)) }

    private suspend fun writeUser(firebaseUser: FirebaseUser, userProfile: UserProfile): Result<String> {
        val hashMap = HashMap<String, Any>()
        with(hashMap) {
            put(userProfileKeys.USER_NAME, userProfile.username!!)
            put(userProfileKeys.AVATAR_URL, "default")
            put(userProfileKeys.EMAIL, firebaseUser.email!!)
            put(userProfileKeys.IS_ONLINE, "online")
        }
        return getTaskResult { databaseReference.child("users").child(firebaseUser.uid).setValue(hashMap) }
    }

}