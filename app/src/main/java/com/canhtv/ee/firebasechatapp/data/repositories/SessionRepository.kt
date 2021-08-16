package com.canhtv.ee.firebasechatapp.data.repositories
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesServices
import com.canhtv.ee.firebasechatapp.data.models.SessionData
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val sharedPreferencesKeys: SharedPreferencesKeys,
    private val sharePreferencesServices: SharePreferencesServices
){
    suspend fun signIn(userCredential: UserCredential) = proceedRequest(
        sessionState = sharedPreferencesKeys.SESSION_STATE_SIGN_IN,
        userCredential = userCredential,
        firebaseService = { firebaseAuthService.signInWithEmailAndPassword(userCredential) }
    )

    suspend fun register(userCredential: UserCredential) = proceedRequest(
        sessionState = sharedPreferencesKeys.SESSION_STATE_SIGN_IN,
        userCredential = userCredential,
        firebaseService = { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }
    )

    suspend fun getSession(): LiveData<Resource<SessionData>> = liveData {
        emit(Resource.Success(sharePreferencesServices.getSession()))
    }

    private fun proceedRequest(
        sessionState: Int,
        userCredential: UserCredential,
        firebaseService: suspend () -> Resource<FirebaseUser>
    ): LiveData<Resource<SessionData>> = liveData (Dispatchers.IO) {

        emit(Resource.Loading())

        when (val result = firebaseService.invoke()) {
            is Resource.Success -> {
                sharePreferencesServices.saveData(sessionState, userCredential, result.data )
                emit(Resource.Success(sharePreferencesServices.getSession()))
            } else -> {
            emit(Resource.Success(sharePreferencesServices.getSession()))
            }
        }
    }
}