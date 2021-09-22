package com.canhtv.ee.firebasechatapp.data.remote
import com.canhtv.ee.firebasechatapp.utils.Result
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await

abstract class BaseFirebaseServices {

    protected suspend fun <T> getTaskResult(
        requestTask: suspend () -> Task<T>
    ): Result<String> {
        return try {
            val task = requestTask.invoke()
            task.await()
            if (task.isSuccessful) {
                Result.Success("Task done")
            } else
                Result.Error(task.exception!!.message!!)
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    protected fun getSignOutResult(
        firebaseAuth: FirebaseAuth
    ): Result<String> {
        return try {
            firebaseAuth.signOut()
            Result.Success("Task done")
        } catch (e: Exception) {
            error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Result<T> {
        return Result.Error("Network call has failed for a following reason: $message")
    }
}
