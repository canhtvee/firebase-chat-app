package com.canhtv.ee.firebasechatapp.data.remote
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.utils.Result
import com.google.firebase.auth.*
import javax.inject.Inject

class FirebaseAuthServices @Inject constructor(
    private val auth: FirebaseAuth
    ) : BaseFirebaseServices(){

    suspend fun createUserWithEmailAndPassword(userCredential: UserCredential): Result<FirebaseUser> {
        return getAuthResult(auth)
        { auth.createUserWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

    suspend fun signInWithEmailAndPassword(userCredential: UserCredential): Result<FirebaseUser> {
        return getAuthResult(auth)
        { auth.signInWithEmailAndPassword(userCredential.email!!, userCredential.password!!) }
    }

}

