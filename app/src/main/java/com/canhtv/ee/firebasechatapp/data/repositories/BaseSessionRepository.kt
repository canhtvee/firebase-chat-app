package com.canhtv.ee.firebasechatapp.data.repositories

import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseSessionRepository {
    fun applySession(
        userSession: UserSession,
        sharedPrefAccess: SharePreferencesAccess,
        authRequest: suspend () -> Resource<FirebaseUser>,
    ): Flow<Resource<UserSession>> = flow {
        emit(Resource.Loading())
        when (authRequest.invoke()) {
            is Resource.Success -> {
                sharedPrefAccess.putUserSession(userSession)
                emit(Resource.Success(sharedPrefAccess.getUserSession()))
            } else -> {
            emit(Resource.Success(sharedPrefAccess.getUserSession()))
            }
        }
    }.flowOn(Dispatchers.IO)
}