package com.canhtv.ee.firebasechatapp.data.repositories
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.models.UserProfile
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseUserManager
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.canhtv.ee.firebasechatapp.utils.Result
import com.canhtv.ee.firebasechatapp.utils.SessionKeys
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SessionManager @Inject constructor(
    private val sessionKeys: SessionKeys,
    private val sharePreferencesAccess: SharePreferencesAccess,
    private val firebaseUserManager: FirebaseUserManager
) : BaseSessionManager() {

    suspend fun applyRegisterSession(userCredential: UserCredential, userProfile: UserProfile,)
    = applySession(
        { firebaseUserManager.registerNewUser(userCredential, userProfile) },
        { sharePreferencesAccess.putSession(it) },
        { sharePreferencesAccess.getSession() },
    )


    suspend fun applySignInSession(userCredential: UserCredential)
    = applySession(
        { firebaseUserManager.signInUser(userCredential) },
        { sharePreferencesAccess.putSession(it) },
        { sharePreferencesAccess.getSession() },
    )

    suspend fun applySignOutSession(): Flow<Resource<UserSession>> = flow {
        emit(Resource.Loading())
        when (val result = firebaseUserManager.signOutUser()) {
            is Result.Success -> {
                sharePreferencesAccess.putInt(sessionKeys.SESSION_STATE, sessionKeys.STATE_SIGN_OUT)
                emit(Resource.Success(sharePreferencesAccess.getSession()))
            }
            is Result.Error -> emit(Resource.Error(result.message))
        }
    }.flowOn(Dispatchers.IO)

}