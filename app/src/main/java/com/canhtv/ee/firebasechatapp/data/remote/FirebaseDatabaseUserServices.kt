package com.canhtv.ee.firebasechatapp.data.remote
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.utils.Result
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseDatabaseUserServices @Inject constructor(
    private val firebaseDatabaseReference: DatabaseReference
) : BaseFirebaseServices() {
    suspend fun writeUser(firebaseUser: FirebaseUser, userProfile: UserProfile): Result<String> {
        val hashMap = HashMap<String, String>()
        with(hashMap) {
            put("username", userProfile.username!!)
            put("email", firebaseUser.email!!)
        }
        return getWriteResult { firebaseDatabaseReference.child("users").child(firebaseUser.uid).setValue(hashMap) }
    }
}