package com.canhtv.ee.firebasechatapp.data.repositories

import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

abstract class BaseSessionManager {

    protected suspend fun applySession(
        authRequest: suspend () -> Result<UserSession>,
        saveSession: (UserSession) -> Unit,
        getSession: () -> UserSession,
    ) = flow {
        emit(Resource.Loading<UserSession>())
        when (val result = authRequest.invoke()) {
            is Result.Success -> {
                saveSession(result.data)
                emit(Resource.Success(getSession.invoke()))
            }
            is Result.Error -> emit(Resource.Error<UserSession>(result.message))
        }
    }.flowOn(Dispatchers.IO)


}