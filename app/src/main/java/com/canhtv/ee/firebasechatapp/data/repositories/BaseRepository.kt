package com.canhtv.ee.firebasechatapp.data.repositories


import androidx.lifecycle.LiveData
import com.canhtv.ee.firebasechatapp.data.models.User
import com.canhtv.ee.firebasechatapp.utils.Resource
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.*

abstract class BaseRepository {

    fun checkCredential(
        getDataFromRemoteSource: suspend () -> Resource<FirebaseUser>,
        saveDataToDatabase: suspend (FirebaseUser) -> Unit,
        getDataFromLocalSource: () -> LiveData<User>
    ) = {}
}