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

abstract class BaseFirebaseService {

    protected suspend fun getResult(
        auth: FirebaseAuth,
        call: suspend () -> Task<AuthResult>
    ): Resource<FirebaseUser> {
        Log.d("TAG CALL", "BaseFirebaseService: call getResult")
        return try {
            val task = call.invoke()
            // Not executing next commands during waiting task was completed
            task.await()
            Log.d("RESULT", "call getResult successful uid = ${auth.currentUser}")
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
