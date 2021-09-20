package com.canhtv.ee.firebasechatapp.data.remote

import javax.inject.Inject

class FirebaseUsersManager @Inject constructor(
    val firebaseAuthServices: FirebaseAuthServices,
    val firebaseDatabaseServices: FirebaseDatabaseServices
) {

    suspend fun registerNewUser() {

    }

}