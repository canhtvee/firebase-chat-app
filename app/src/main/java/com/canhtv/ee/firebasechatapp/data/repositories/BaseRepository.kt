package com.canhtv.ee.firebasechatapp.data.repositories

import com.canhtv.ee.firebasechatapp.data.models.Session
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {
    fun proceedRequest(
        firebaseService: suspend () -> Resource<FirebaseUser>
    ): Flow<Resource<Session>> = flow {

        emit(Resource.Loading())

        when (val result = firebaseService.invoke()) {
            is Resource.Success -> {
                sharePreferencesServices.saveSession(Sh, userCredential, result.data )
                emit(Resource.Success(sharePreferencesServices.getSession(), null))
            }
            is Resource.Error -> {
                emit(Resource.Error(sharePreferencesServices.getSession(), result.message))
            }
        }
    }

}