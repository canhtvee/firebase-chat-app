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
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val sharedPrefKeys: SharedPreferencesKeys,
    private val sharedPrefAccess: SharePreferencesAccess
){
    suspend fun applySignInSession(userCredential: UserCredential): LiveData<Resource<UserSession>> {
        return applySession(
            sharedPrefKeys.STATE_SIGN_IN, userCredential
        ) { firebaseAuthService.signInWithEmailAndPassword(userCredential) }
    }

    suspend fun applyRegisterSession(userCredential: UserCredential) = applySession(
        sharedPrefKeys.STATE_SIGN_IN, userCredential
    ) { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }

    suspend fun getSession(): LiveData<Resource<UserSession>> = liveData {
        emit(Resource.Success(sharedPrefAccess.getUserSession()))
    }

    private suspend fun applySession(
        sessionState: Int,
        userCredential: UserCredential,
        authRequest: suspend () -> Resource<FirebaseUser>
    ): LiveData<Resource<UserSession>> = liveData {
        Log.d("TAG CALL", "SessionRepository: call applySession")
        emit(Resource.Loading())
        when (val result = authRequest.invoke()) {
            is Resource.Success -> {
                sharedPrefAccess.putSessionData(sessionState, userCredential, result.data )
                emit(Resource.Success(sharedPrefAccess.getUserSession()))
            } else -> {
            emit(Resource.Success(sharedPrefAccess.getUserSession()))
            }
        }
    }
}