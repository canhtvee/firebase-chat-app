package com.canhtv.ee.firebasechatapp.data.remote
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

abstract class BaseFirebaseService {

    protected suspend fun getResult(
        auth: FirebaseAuth,
        call: suspend () -> Task<AuthResult>
    ): Resource<FirebaseUser> {

        try {
            val task = call()
            if (task.isSuccessful) {
                val user = auth.currentUser
                return Resource.Success(user!!)
            }
            return error(" ${task.exception} ${task.exception?.message}")
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.Error("Network call has failed for a following reason: $message")
    }
}
