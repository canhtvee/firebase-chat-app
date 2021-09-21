package com.canhtv.ee.firebasechatapp.data.repositories
import com.canhtv.ee.firebasechatapp.data.local.SharePreferencesAccess
import com.canhtv.ee.firebasechatapp.data.models.UserSession
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.remote.FirebaseAuthServices
import com.canhtv.ee.firebasechatapp.utils.SharedPreferencesKeys
import javax.inject.Inject

class SessionRepository @Inject constructor(
    private val firebaseAuthService: FirebaseAuthServices,
    private val sharedPrefKeys: SharedPreferencesKeys,
    private val sharedPrefAccess: SharePreferencesAccess
) : BaseSessionRepository(){

//    suspend fun applyRegisterSession(userCredential: UserCredential
//    ) = applySession(
//        UserSession(sharedPrefKeys.STATE_SIGN_IN, userCredential),
//        sharedPrefAccess)
//    { firebaseAuthService.createUserWithEmailAndPassword(userCredential) }
//
//    suspend fun applySignInSession(userCredential: UserCredential
//    ) = applySession(
//        UserSession(sharedPrefKeys.STATE_SIGN_IN, userCredential),
//        sharedPrefAccess)
//        { firebaseAuthService.signInWithEmailAndPassword(userCredential) }

}