package com.canhtv.ee.firebasechatapp.data.remote
import android.util.Log
import androidx.compose.runtime.saveable.autoSaver
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

abstract class BaseFirebaseServices {

    protected suspend fun getAuthResult(
        auth: FirebaseAuth,
        call: suspend () -> Task<AuthResult>
    ): Resource<FirebaseUser> {
        return try {
            val task = call.invoke()
            task.await()
            if (auth.currentUser != null) {Resource.Success(auth.currentUser!!)} else {
                Resource.Error("${task.exception}")
            }
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.Error("Network call has failed for a following reason: $message")
    }
}