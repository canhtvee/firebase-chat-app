package com.canhtv.ee.firebasechatapp.data.remote
import android.util.Log
import androidx.compose.runtime.saveable.autoSaver
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

abstract class BaseFirebaseService {

    protected suspend fun getResult(
        auth: FirebaseAuth,
        call: suspend () -> Task<AuthResult>
    ): Resource<FirebaseUser> {
        Log.d("TAG CALL", "BaseFirebaseService: call getResult")
        try {
            val authTask = call.invoke()
//            if (authTask.isSuccessful) {
//                return Resource.Success(auth.currentUser!!)
//                Log.d("RESULT", "call getResult successful uid = ${auth.currentUser!!.uid}")
//            } else {
//                return Resource.Error(authTask.exception.toString())
//            }

            authTask.addOnCompleteListener {
                if (it.isSuccessful) {
                    Resource.Success(auth.currentUser!!)
                    Log.d("RESULT", "call getResult successful uid = ${auth.currentUser!!.uid}")
                } else {
                }
            }


            return Resource.Loading<FirebaseUser>()
        } catch (e: Exception) {
            return error(e.message ?: e.toString())
        }
    }

    private fun <T> error(message: String): Resource<T> {
        return Resource.Error("Network call has failed for a following reason: $message")
    }
}
