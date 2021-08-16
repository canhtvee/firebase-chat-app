package com.canhtv.ee.firebasechatapp.viewmodels

import androidx.lifecycle.*
import com.canhtv.ee.firebasechatapp.data.models.SessionData
import com.canhtv.ee.firebasechatapp.data.models.UserCredential
import com.canhtv.ee.firebasechatapp.data.repositories.SessionRepository
import com.canhtv.ee.firebasechatapp.utils.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
): ViewModel() {

    lateinit var sessionData: LiveData<Resource<SessionData>>

    init {
        viewModelScope.launch {
            sessionData = sessionRepository.getSession()
        }
    }

    fun signInUser(userCredential: UserCredential) {
        viewModelScope.launch { sessionData = sessionRepository.signIn(userCredential) }
    }

    fun registeruser(userCredential: UserCredential) {
        viewModelScope.launch { sessionData = sessionRepository.signIn(userCredential) }
    }
}