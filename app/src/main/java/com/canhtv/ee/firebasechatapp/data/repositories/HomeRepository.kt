package com.canhtv.ee.firebasechatapp.data.repositories

import com.canhtv.ee.firebasechatapp.data.remote.FirebaseDatabaseServices
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val firebaseDatabaseServices: FirebaseDatabaseServices
) {
    @ExperimentalCoroutinesApi
    fun getContacts() = firebaseDatabaseServices.retrieveUserFlow()
}