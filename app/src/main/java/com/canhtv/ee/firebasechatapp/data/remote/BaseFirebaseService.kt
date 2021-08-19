package com.canhtv.ee.firebasechatapp.data.remote
import android.util.Log
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
        var resource: Resource<FirebaseUser> = Resource.Loading()
        try {
            val authTask = call()
            authTask.addOnCompleteListener { task ->
                resource = if (task.isSuccessful) {
                    Resource.Success(auth.currentUser!!)
                } else {
                    Resource.Error(task.exception.toString())
                }
            }
        } catch (e: Exception) {
            resource = error(e.message ?: e.toString())
        }
        return resource
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.Error("Network call has failed for a following reason: $message")
    }
}
