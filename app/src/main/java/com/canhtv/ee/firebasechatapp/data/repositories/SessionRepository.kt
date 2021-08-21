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
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val sharedPrefKeys: SharedPreferencesKeys,
    private val sharedPrefAccess: SharePreferencesAccess
){

    suspend fun applyRegisterSession(userCredential: UserCredential
    )= applySession(sharedPrefKeys.STATE_SIGN_IN, userCredential)
    { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }

    suspend fun applySignInSession(userCredential: UserCredential
    ): Flow<Resource<UserSession>> {
        Log.d("TAG CALL", "SessionRepository: call applySignInSession")
        return applySession(sharedPrefKeys.STATE_SIGN_IN, userCredential) {
            firebaseAuthService.signInWithEmailAndPassword(userCredential)
        }
    }

    fun getSession(): Flow<Resource<UserSession>> = flow {
        Log.d("TAG CALL", "SessionRepository: call getSession")
        emit(Resource.Success(sharedPrefAccess.getUserSession()))
    }.flowOn(Dispatchers.IO)


    private suspend fun applySession(
        sessionState: Int,
        userCredential: UserCredential,
        authRequest: suspend () -> Resource<FirebaseUser>
    ): Flow<Resource<UserSession>> = flow {
        emit(Resource.Loading())
        Log.d("TAG CALL", "SessionRepository: call applySession")
        when (val result = authRequest.invoke()) {
            is Resource.Success -> {
                Log.d("TAG CALL", "SessionRepository: call applySession authRequest successful")
                sharedPrefAccess.putSessionData(sessionState, userCredential, result.data )
                emit(Resource.Success(sharedPrefAccess.getUserSession()))
            } else -> {
            emit(Resource.Success(sharedPrefAccess.getUserSession()))
            }
        }
    }.flowOn(Dispatchers.IO)
}