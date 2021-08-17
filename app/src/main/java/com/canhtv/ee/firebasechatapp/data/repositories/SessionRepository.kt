package com.canhtv.ee.firebasechatapp.data.repositories
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
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val sharePreferencesAccess: SharePreferencesAccess
){
    suspend fun applySignInSession(userCredential: UserCredential) = applySession(
        sharedPreferencesKeys.SESSION_STATE_SIGN_IN, userCredential
    ) { firebaseAuthService.signInWithEmailAndPassword(userCredential) }

    suspend fun applyRegisterSession(userCredential: UserCredential) = applySession(
        sharedPreferencesKeys.SESSION_STATE_SIGN_IN, userCredential
    ) { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }

    suspend fun getSession(): LiveData<Resource<UserSession>> = liveData {
        emit(Resource.Success(sharePreferencesAccess.getUserSession()))
    }

    private fun applySession(
        sessionState: Int,
        userCredential: UserCredential,
        authRequest: suspend () -> Resource<FirebaseUser>
    ): LiveData<Resource<UserSession>> = liveData (Dispatchers.IO) {
        emit(Resource.Loading())
        when (val result = authRequest.invoke()) {
            is Resource.Success -> {
                sharePreferencesAccess.putSessionData(sessionState, userCredential, result.data )
                emit(Resource.Success(sharePreferencesAccess.getUserSession()))
            } else -> {
            emit(Resource.Success(sharePreferencesAccess.getUserSession()))
            }
        }
    }
}