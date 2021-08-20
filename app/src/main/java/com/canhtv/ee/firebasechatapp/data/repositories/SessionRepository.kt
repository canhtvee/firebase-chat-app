package com.canhtv.ee.firebasechatapp.data.repositories
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val sharedPrefKeys: SharedPreferencesKeys,
    private val sharedPrefAccess: SharePreferencesAccess
){

    fun applyRegisterSession(userCredential: UserCredential
    )= applySession(sharedPrefKeys.STATE_SIGN_IN, userCredential)
    { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }

    fun applySignInSession(userCredential: UserCredential): Flow<Resource<UserSession>> {
        Log.d("TAG CALL", "SessionRepository: call applySignInSession")
        return applySession(sharedPrefKeys.STATE_SIGN_IN, userCredential)
        { firebaseAuthService.signInWithEmailAndPassword(userCredential) }
    }



    fun getSession(): Flow<Resource<UserSession>> = flow {
        Log.d("TAG CALL", "SessionRepository: call getSession")
        emit(Resource.Success(sharedPrefAccess.getUserSession()))
    }.flowOn(Dispatchers.IO)



    fun getNameFlow() = flow {
        Log.d("TAG CALL", "SessionRepository: call getNameFlow")

        val names = listOf("Jody", "Steve", "Lance", "Joe")
        for (name in names) {
            delay(100)
            Log.d("TAG CALL", "SessionRepository: call getNameFlow emit$name")
            emit(name)
        }
    }.flowOn(Dispatchers.IO)










    suspend fun trySignInSession(userCredential: UserCredential
    ) = tryApplySession { firebaseAuthService.signInWithEmailAndPassword(userCredential) }


    private suspend fun tryApplySession(authRequest: suspend () -> Resource<FirebaseUser>
    ) = liveData (Dispatchers.IO) {
        val result = authRequest.invoke()
        Log.d("TAG RESULT", "tryApplySession: ${result.toString()}")
        emit(Resource.Success(sharedPrefAccess.getUserSession()))
    }
















    private fun applySession(
        sessionState: Int,
        userCredential: UserCredential,
        authRequest: suspend () -> Resource<FirebaseUser>
    ): Flow<Resource<UserSession>> = flow {
        Log.d("TAG CALL", "SessionRepository: call applySession")

        emit(Resource.Success(sharedPrefAccess.getUserSession()))

//        emit(Resource.Loading())
//
//        when (val result = authRequest.invoke()) {
//            is Resource.Success -> {
//                sharedPrefAccess.putSessionData(sessionState, userCredential, result.data )
//                emit(Resource.Success(sharedPrefAccess.getUserSession()))
//            } else -> {
//            emit(Resource.Success(sharedPrefAccess.getUserSession()))
//            }
//        }
    }.flowOn(Dispatchers.IO)
}