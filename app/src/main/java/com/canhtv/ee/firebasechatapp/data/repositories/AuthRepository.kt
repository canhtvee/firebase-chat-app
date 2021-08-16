package com.canhtv.ee.firebasechatapp.data.repositories
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesServices
import com.canhtv.ee.firebasechatapp.data.models.Session
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthService
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthService,
    private val sharePreferencesServices: SharePreferencesServices
){
    suspend fun signIn(userCredential: UserCredential): Flow<Resource<Session>> = flow {

    }

    suspend fun register(userCredential: UserCredential): Flow<Resource<Session>> = flow {

    }

    fun proceedRequest(
        sessionState: Int,
        userCredential: UserCredential,
        firebaseService: suspend () -> Resource<FirebaseUser>
    ): Flow<Resource<Session>> = flow {

        emit(Resource.Loading<Session>())
        val result = firebaseService.invoke()
        if () {
            is Resource.Success -> {
                sharePreferencesServices.saveData(sessionState, userCredential, result.data )
                emit(Resource.Success(sharePreferencesServices.getSession()))
            }
        }
    }


}